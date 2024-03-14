package com.kidsqueue.kidsqueue.reservation.repository;

import com.kidsqueue.kidsqueue.reservation.entity.Reservation;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Reservation save(Reservation reservation);
    List<Reservation> findByParentId(Long parentId);
    List<Reservation> findByChildIdAndIsActive(Long childId, Boolean isActive);
    List<Reservation> findByHospitalIdAndTime(Long hospitalId, Timestamp time);
}
