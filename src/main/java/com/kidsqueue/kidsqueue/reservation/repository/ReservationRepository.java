package com.kidsqueue.kidsqueue.reservation.repository;

import com.kidsqueue.kidsqueue.reservation.entity.Reservation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Reservation save(Reservation reservation);
    List<Reservation> findByParent_Id(Long parentId);
}
