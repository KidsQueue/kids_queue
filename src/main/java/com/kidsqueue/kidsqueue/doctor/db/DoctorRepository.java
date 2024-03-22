package com.kidsqueue.kidsqueue.doctor.db;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	List<Doctor> findAllByHospitalId(Long hospitalId); //특정 병원의 모든 의사 조회

}
