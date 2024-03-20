package com.kidsqueue.kidsqueue.reservation.service;

import com.kidsqueue.kidsqueue.hospital.db.Hospital;
import com.kidsqueue.kidsqueue.hospital.db.HospitalRepository;
import com.kidsqueue.kidsqueue.parent.db.Parent;
import com.kidsqueue.kidsqueue.parent.db.ParentRepository;
import com.kidsqueue.kidsqueue.reservation.entity.Child;
import com.kidsqueue.kidsqueue.reservation.entity.Reservation;
import com.kidsqueue.kidsqueue.reservation.model.ReservationDTO;
import com.kidsqueue.kidsqueue.reservation.repository.ReservationRepository;
import java.sql.Timestamp;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final HospitalRepository hospitalRepository;

    public Reservation saveReservation(Long userId, ReservationDTO reservationDTO) {
        Reservation reservation = Reservation.builder()
            .parent(Parent.builder().id(userId).build())
            .hospital(Hospital.builder().id(reservationDTO.getHospitalId()).build())
            .child(Child.builder().id(reservationDTO.getChildId()).build())
            .time(reservationDTO.getTime())
            .isActive(true)
            .build();
        /*
            예외 : 1. 현재시간보다 이전의 예약은 예약 불가,
                  2. childId가 예약한 예약중 같은 예약시간이 있는 경우,
                  3. 병원의 시간당 최대 예약 확인
        */
        checkEarlierThanCurrentTime(reservationDTO.getTime());
        checkTimeLikeTimeInDB(reservationDTO.getChildId(), reservationDTO.getTime());
        checkMaxNumOfPeople(reservationDTO.getHospitalId(), reservationDTO.getTime());
        return reservationRepository.save(reservation);
    }

    public void checkEarlierThanCurrentTime(Timestamp reservationTime) {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

        if (reservationTime.before(currentTime)) {
            throw new IllegalArgumentException("예약은 현재 시간 이후로만 가능합니다.");
        }
    }

    public void checkTimeLikeTimeInDB(Long childId, Timestamp time) {
        List<Reservation> reservations = reservationRepository.findByChildIdAndIsActive(childId, true);
        for (Reservation reservation : reservations) {
            if (reservation.getTime().equals(time)) {
                throw new IllegalStateException("해당 시간에 같은 예약이 있습니다.");
            }
        }
    }

    public void checkMaxNumOfPeople(Long hospitalId, Timestamp time) {
        hospitalRepository.findById(hospitalId)
            .map(it -> {
                Integer maxNumOfPeople = it.getMaxNumOfPeople();
                List<Reservation> reservations = reservationRepository.findByHospitalIdAndTimeAndIsActive(
                    hospitalId, time, true);
                int numOfReservation = 0;
                for (Reservation reservation : reservations) {
                    numOfReservation++;
                }
                if (maxNumOfPeople <= numOfReservation) {
                    throw new IllegalStateException("해당 시간의 예약이 다찼습니다.");
                }
                return it;
            }).orElseThrow(
                () -> {
                    throw new NotFoundException("존재하지 않는 병원입니다");
                }
            );
    }

    public Reservation findReservation(Long reservationId){
        return reservationRepository.findByIdAndIsActive(reservationId,true).orElseThrow(()-> {
            throw new NotFoundException("존재하지 않는 예약입니다.");
        });
    }
    public List<Reservation> findReservationListOfParent(Long parentId) {
        return reservationRepository.findByParentIdAndIsActive(parentId, true);
    }

    public Reservation updateReservation(Long reservationId, ReservationDTO reservationDTO) {
        return reservationRepository.findById(reservationId)
            .map(it -> {
                checkEarlierThanCurrentTime(reservationDTO.getTime());
                checkTimeLikeTimeInDB(reservationDTO.getChildId(), reservationDTO.getTime());
                checkMaxNumOfPeople(reservationDTO.getHospitalId(), reservationDTO.getTime());

                it.updateReservation(reservationDTO.getTime());
                return reservationRepository.save(it);
            }).orElseThrow(
                () -> {
                    throw new NotFoundException("존재하지 않는 예약입니다");
                }
            );
    }

    public void deleteReservation(Long reservationId) {
        reservationRepository.findById(reservationId)
            .map(it -> {
                it.disabled();
                reservationRepository.save(it);
                return it;
            }).orElseThrow(
                () -> {
                    throw new NotFoundException("존재하지 않는 예약입니다");
                }
            );
    }
}
