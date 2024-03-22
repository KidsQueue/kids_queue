package com.kidsqueue.kidsqueue.doctor.service;

import com.kidsqueue.kidsqueue.doctor.db.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorService {

	private final DoctorRepository doctorRepository;
	private final DoctorConverter doctorConverter;



}
