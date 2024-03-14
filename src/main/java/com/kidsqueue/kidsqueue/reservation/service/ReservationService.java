package com.kidsqueue.kidsqueue.reservation.service;

import com.kidsqueue.kidsqueue.hospital.db.Hospital;
import com.kidsqueue.kidsqueue.parent.db.Parent;
import com.kidsqueue.kidsqueue.reservation.entity.Child;
import com.kidsqueue.kidsqueue.reservation.entity.Reservation;
import com.kidsqueue.kidsqueue.reservation.model.ReservationDTO;
import com.kidsqueue.kidsqueue.reservation.repository.ReservationRepository;
import java.sql.Timestamp;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    public Reservation saveReservation(Long userId, ReservationDTO reservationDTO) {
        // ReservationDTO에서 Parent 객체를 불러오든 userId를 통해서 db에서 Parent 객체들 불러오든 해야함, 아니면 그냥 Id만 넣고 저장
        Parent parent = Parent.builder().id(userId).build();  //임시
        Hospital hospital = Hospital.builder().id(reservationDTO.getHospitalId()).build();
        Child child = Child.builder().id(reservationDTO.getChildId()).build();
        Reservation reservation = Reservation.builder()
            .parent(parent)
            .hospital(hospital)
            .child(child)
            .time(reservationDTO.getTime())
            .is_active(true)
            .build();
        /*
            예외 : 현재시간보다 이전의 예약은 예약 불가, childId가 예약한 예약중 같은 예약시간이 있는 경우
        */
        checkEarlierThanCurrentTime(reservationDTO.getTime());
        checkTimeLikeTimeInDB(reservationDTO.getChildId(), reservation.getTime());

        /*
            병원의 시간당 최대 예약 수 확인 후 예약하려고 하는 시간의 데이터 수를 확인 최대 예약 수 이상이라면 예약 불가
        */

        return reservationRepository.save(reservation);
    }
    public void checkEarlierThanCurrentTime(Timestamp reservationTime){
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

        if (reservationTime.before(currentTime)) {
            throw new IllegalArgumentException("예약은 현재 시간 이후로만 가능합니다.");
        }
    }
    public void checkTimeLikeTimeInDB(Long childId, Timestamp time){
        List<Reservation> reservations = reservationRepository.findByChild_IdAndIsActive(childId, true);

        for (Reservation reservation : reservations) {
            if (reservation.getTime().equals(time)) {
                throw new IllegalStateException("해당 시간에 같은 예약이 있습니다.");
            }
        }
    }

    public List<Reservation> findReservationListOfParentId(Long parentId){
        return reservationRepository.findByParent_Id(parentId);
    }
}
