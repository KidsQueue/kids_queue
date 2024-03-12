package com.kidsqueue.kidsqueue.hospital.db;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {

    // select * from hospital where status
    Page<Hospital> findAllByIsActive(Integer isActive, Pageable pageable);
}
