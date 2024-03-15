package com.kidsqueue.kidsqueue.reservation.entity;

import com.kidsqueue.kidsqueue.hospital.db.Hospital;
import com.kidsqueue.kidsqueue.reservation.repository.ReservationRepository;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class ReservationTest {

    @Autowired
    ReservationRepository reservationRepository;

    @Test
    void createReservation() {
        /*
    given
     */
        LocalDateTime localDateTime = LocalDateTime.of(2022, 10, 1, 10, 30);
        Timestamp timestamp = Timestamp.valueOf(localDateTime);

        Long userId = 1L;
        Long hospitalId = 30L;
        Hospital hospital = Hospital.builder().id(hospitalId).build();
        Long childId = 20L;
        Child child = Child.builder().id(childId).build();

        Reservation reservation = Reservation.builder()
            .id(userId)
            .hospital(hospital)
            .child(child)
            .time(timestamp)
            .isActive(true)
            .build();

        Assertions.assertThat(reservation.getHospital().getId()).isEqualTo(hospitalId);
        Assertions.assertThat(reservation.getChild().getId()).isEqualTo(childId);
        Assertions.assertThat(reservation.getTime()).isEqualTo(timestamp);
    }
}