package com.kidsqueue.kidsqueue.reservation.service;

import com.kidsqueue.kidsqueue.hospital.db.Hospital;
import com.kidsqueue.kidsqueue.hospital.db.HospitalRepository;
import com.kidsqueue.kidsqueue.parent.db.Parent;
import com.kidsqueue.kidsqueue.parent.db.Parent.ParentBuilder;
import com.kidsqueue.kidsqueue.parent.db.ParentRepository;
import com.kidsqueue.kidsqueue.reservation.entity.Child;
import com.kidsqueue.kidsqueue.reservation.entity.Reservation;
import com.kidsqueue.kidsqueue.reservation.model.ReservationDTO;
import com.kidsqueue.kidsqueue.reservation.repository.ReservationRepository;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@Transactional
class ReservationServiceTest {
    @Autowired ReservationService reservationService;
    @Autowired
    HospitalRepository hospitalRepository;
    @Autowired
    ParentRepository parentRepository;

    @Test
    @DisplayName("예약 생성 성공")
    void createReservation() {
        /*
    given
     */
        LocalDateTime localDateTime = LocalDateTime.of(2024, 8, 1, 10, 30);
        Timestamp timestamp = Timestamp.valueOf(localDateTime);

        Long userId = 1L;
        Parent parent = parentRepository.findById(userId.intValue()).get();
        Long hospitalId = 1L;
        Hospital hospital = hospitalRepository.findById(hospitalId).get();
        Long childId = 1L;
        Child child = Child.builder()
            .id(childId)
            .name("홍길동")
            .age("21")
            .gender("M")
            .residentRegistrationNumber("1010101010101")
            .createdBy(Timestamp.valueOf("2024-10-01 11:30:00"))
            .updatedBy(Timestamp.valueOf("2024-10-01 11:30:00"))
            .isActive(true)
            .build();
        ReservationDTO reservationDTO = ReservationDTO.builder().childId(childId).hospitalId(hospitalId).time(Timestamp.valueOf("2024-10-01T11:30:00")).build();
    /*
    when
     */
        Reservation reservation1 = reservationService.saveReservation(userId,reservationDTO);

    /*
    then
     */
        Assertions.assertEquals(reservation1.getParent(),parent);
        Assertions.assertEquals(reservation1.getChild(),child);
        Assertions.assertEquals(reservation1.getHospital(),hospital);
        Assertions.assertEquals(reservation1.getTime(),Timestamp.valueOf("2024-10-01 11:30:00"));

    }

    @Test
    void selectReservation(){
        LocalDateTime localDateTime = LocalDateTime.of(2024, 8, 1, 10, 30);
        Timestamp timestamp = Timestamp.valueOf(localDateTime);

        Long userId = 1L;
        Parent parent = Parent.builder().id(userId).build();
        Long hospitalId = 1L;
        Hospital hospital = Hospital.builder().id(hospitalId).build();
        Long childId = 1L;
        Child child = Child.builder().id(childId).build();
        ReservationDTO reservationDTO = ReservationDTO.builder().childId(childId).hospitalId(hospitalId).time(Timestamp.valueOf("2024-10-01 11:30:00")).build();
        Reservation reservation = reservationService.saveReservation(userId,reservationDTO);
    /*
    when
     */
        Reservation reservation1 = reservationService.findReservation(reservation.getId());
        /*
    then
     */
        Assertions.assertEquals(reservation,reservation1);

    }
}