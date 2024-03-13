package com.kidsqueue.kidsqueue.hospital.db;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {

    Page<Hospital> findAllByIsActiveOrderByIdDesc(Integer isActive, Pageable pageable);
    Page<Hospital> findAllByIsActiveOrderByIdAsc(Integer isActive, Pageable pageable);

    @Query(value = "select * from hospital where is_active = :isActive and name like concat('%', :name, '%')",
            countQuery = "SELECT COUNT(*) FROM hospital",
            nativeQuery = true)
    Page<Hospital> findAllByIsActiveAndNameNative(@Param("isActive") Integer isActive, @Param("name") String name, Pageable pageable);

}
