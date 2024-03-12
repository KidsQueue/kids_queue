package com.kidsqueue.kidsqueue.reservation.service;

import com.kidsqueue.kidsqueue.domain.Parent.Parent;
import com.kidsqueue.kidsqueue.reservation.entity.Child;
import com.kidsqueue.kidsqueue.reservation.entity.Hospital;
import com.kidsqueue.kidsqueue.reservation.entity.Reservation;
import com.kidsqueue.kidsqueue.reservation.model.ReservationDTO;
import com.kidsqueue.kidsqueue.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public Reservation saveReservation(Long userId,ReservationDTO reservationDTO){
        // ReservationDTO에서 Parent 객체를 불러오든 userId를 통해서 db에서 Parent 객체들 불러오든 해야함
        Parent parent = Parent.builder().id(userId).build();  //임시
        Hospital hospital = Hospital.builder().id(reservationDTO.getHospitalId()).build();
        Child child =  Child.builder().id(reservationDTO.getChildId()).build();


        Reservation reservation = Reservation.builder()
            .Parent(parent)
            .hospital(hospital)
            .child(child)
            .time(reservationDTO.getTime())
            .is_active(true)
            .build();
        return reservationRepository.save(reservation);

    }
}
