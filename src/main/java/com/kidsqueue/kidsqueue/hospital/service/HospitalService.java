package com.kidsqueue.kidsqueue.hospital.service;

import com.kidsqueue.kidsqueue.hospital.repository.ClinicHourRepository;
import com.kidsqueue.kidsqueue.hospital.repository.DoctorRepository;
import com.kidsqueue.kidsqueue.hospital.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HospitalService {

	private HospitalRepository hospitalRepository;
	private ClinicHourRepository clinicHourRepository;
	private DoctorRepository doctorRepository;



}
