package com.kidsqueue.kidsqueue.doctor.service;

import com.kidsqueue.kidsqueue.doctor.db.Doctor;
import com.kidsqueue.kidsqueue.doctor.db.DoctorRepository;
import com.kidsqueue.kidsqueue.doctor.model.DoctorRequestDto;
import com.kidsqueue.kidsqueue.doctor.model.DoctorResponseDto;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorService {

	private final DoctorRepository doctorRepository;
	private final DoctorConverter doctorConverter;

	/*의사를 생성하고 저장*/
	public DoctorResponseDto createDoctor(DoctorRequestDto requestDto) {
		Doctor doctor = doctorConverter.toEntity(requestDto);
		Doctor savedDoctor = doctorRepository.save(doctor);
		return doctorConverter.toDto(savedDoctor);
	}

	/*특정 의사 삭제*/
	public void deleteDoctorById(Long id) {
		doctorRepository.deleteById(id);
	}

	/*특정 의사 수정*/
	public DoctorResponseDto updateDoctorById(Long id, DoctorRequestDto requestDto) {
		Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
		if (optionalDoctor.isPresent()) {
			Doctor doctor = optionalDoctor.get();
			doctor.setName(requestDto.getName());
			doctor.setProfileImageUrl(requestDto.getProfileImageUrl());
			doctor.setDescription(requestDto.getDescription());
			doctor.setIsActive(requestDto.getIsActive());
			Doctor updatedDoctor = doctorRepository.save(doctor);
			return doctorConverter.toDto(updatedDoctor); // 이때 updatedBy 필드 업데이트 됨
		} else {
			throw new IllegalArgumentException("Doctor not found with id: " + id); //의사 존재하지 않는 경우 예외 발생
		}
	}

	/*특정 의사 검색*/
	public DoctorResponseDto getDoctorById(Long id) {
		Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
		if (optionalDoctor.isPresent()) {
			Doctor doctor = optionalDoctor.get();
			return doctorConverter.toDto(doctor);
		} else {
			throw new IllegalArgumentException("Doctor not found with id: " + id);
		}
	}

	/*모든 의사 조회*/
	public List<DoctorResponseDto> getAllDoctors() {
		List<Doctor> doctors = doctorRepository.findAll();
		return doctors.stream()
			.map(doctorConverter::toDto)
			.collect(Collectors.toList());
	}
}

