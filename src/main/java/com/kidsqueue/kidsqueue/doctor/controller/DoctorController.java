package com.kidsqueue.kidsqueue.doctor.controller;

import com.kidsqueue.kidsqueue.common.Api;
import com.kidsqueue.kidsqueue.doctor.model.DoctorRequestDto;
import com.kidsqueue.kidsqueue.doctor.model.DoctorResponseDto;
import com.kidsqueue.kidsqueue.doctor.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hospitals/{hospital_id}/doctors")
public class DoctorController {

	private final DoctorService doctorService;

	@PostMapping
	public ResponseEntity<Api<DoctorResponseDto>> createDoctor(
		@RequestBody DoctorRequestDto requestDto) {
		DoctorResponseDto createdDoctor = doctorService.createDoctor(requestDto);
		Api<DoctorResponseDto> response = Api.<DoctorResponseDto>builder()
			.status(Api.SUCCESS_STATUS)
			.message("의사 정보가 성공적으로 생성되었습니다.")
			.data(createdDoctor)
			.build();
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}


	@DeleteMapping("/{doctor_id}")
	public ResponseEntity<Api<Void>> deleteDoctorById(
		@PathVariable("hospital_id") Long hospitalId,
		@PathVariable("doctor_id") Long doctorId) {
		doctorService.deleteDoctorById(doctorId);
		Api<Void> response = Api.<Void>builder()
			.status(Api.SUCCESS_STATUS)
			.message("의사 정보가 성공적으로 삭제되었습니다.")
			.build();
		return ResponseEntity.ok(response);
	}

	@PutMapping("/{doctor_id}")
	public ResponseEntity<Api<DoctorResponseDto>> updateDoctorById(
		@PathVariable("hospital_id") Long hospitalId,
		@PathVariable("doctor_id") Long doctorId,
		@RequestBody DoctorRequestDto requestDto) {
		DoctorResponseDto updatedDoctor = doctorService.updateDoctorById(doctorId, requestDto);
		Api<DoctorResponseDto> response = Api.<DoctorResponseDto>builder()
			.status(Api.SUCCESS_STATUS)
			.message("의사 정보가 성공적으로 수정되었습니다.")
			.data(updatedDoctor)
			.build();
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{doctor_id}")
	public ResponseEntity<Api<DoctorResponseDto>> getDoctorById(
		@PathVariable("hospital_id") Long hospitalId,
		@PathVariable("doctor_id") Long doctorId) {
		DoctorResponseDto doctor = doctorService.getDoctorById(doctorId);
		Api<DoctorResponseDto> response = Api.<DoctorResponseDto>builder()
			.status(Api.SUCCESS_STATUS)
			.message("의사 정보가 성공적으로 검색되었습니다.")
			.data(doctor)
			.build();
		return ResponseEntity.ok(response);
	}

	@GetMapping
	public ResponseEntity<Api<List<DoctorResponseDto>>> getAllDoctorsByHospitalId(
		@PathVariable("hospital_id") Long hospitalId) {
		List<DoctorResponseDto> doctors = doctorService.getAllDoctorsByHospitalId(hospitalId);
		Api<List<DoctorResponseDto>> response = Api.<List<DoctorResponseDto>>builder()
			.status(Api.SUCCESS_STATUS)
			.message("해당 병원의 모든 의사가 성공적으로 검색되었습니다.")
			.data(doctors)
			.build();
		return ResponseEntity.ok(response);
	}

}
