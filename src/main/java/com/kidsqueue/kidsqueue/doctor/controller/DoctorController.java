package com.kidsqueue.kidsqueue.doctor.controller;

import com.kidsqueue.kidsqueue.common.ApiResponse;
import com.kidsqueue.kidsqueue.common.HospitalNotFoundException;
import com.kidsqueue.kidsqueue.doctor.db.Doctor;
import com.kidsqueue.kidsqueue.doctor.db.DoctorRepository;
import com.kidsqueue.kidsqueue.doctor.model.DoctorRequestDto;
import com.kidsqueue.kidsqueue.doctor.model.DoctorResponseDto;
import com.kidsqueue.kidsqueue.doctor.service.DoctorService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
	public ResponseEntity<ApiResponse<DoctorResponseDto>> createDoctor(
		@RequestBody DoctorRequestDto requestDto) {
		DoctorResponseDto createdDoctor = doctorService.createDoctor(requestDto);
		ApiResponse<DoctorResponseDto> response = ApiResponse.<DoctorResponseDto>builder()
			.status(ApiResponse.SUCCESS_STATUS)
			.message("의사 정보가 성공적으로 생성되었습니다.")
			.data(createdDoctor)
			.build();
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}


	@DeleteMapping("/{doctor_id}")
	public ResponseEntity<ApiResponse<Void>> deleteDoctorById(
		@PathVariable("hospital_id") Long hospitalId,
		@PathVariable("doctor_id") Long doctorId) {
		doctorService.deleteDoctorById(doctorId);
		ApiResponse<Void> response = ApiResponse.<Void>builder()
			.status(ApiResponse.SUCCESS_STATUS)
			.message("의사 정보가 성공적으로 삭제되었습니다.")
			.build();
		return ResponseEntity.ok(response);
	}

	@PutMapping("/{doctor_id}")
	public ResponseEntity<ApiResponse<DoctorResponseDto>> updateDoctorById(
		@PathVariable("hospital_id") Long hospitalId,
		@PathVariable("doctor_id") Long doctorId,
		@RequestBody DoctorRequestDto requestDto) {
		DoctorResponseDto updatedDoctor = doctorService.updateDoctorById(doctorId, requestDto);
		ApiResponse<DoctorResponseDto> response = ApiResponse.<DoctorResponseDto>builder()
			.status(ApiResponse.SUCCESS_STATUS)
			.message("의사 정보가 성공적으로 수정되었습니다.")
			.data(updatedDoctor)
			.build();
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{doctor_id}")
	public ResponseEntity<ApiResponse<DoctorResponseDto>> getDoctorById(
		@PathVariable("hospital_id") Long hospitalId,
		@PathVariable("doctor_id") Long doctorId) {
		DoctorResponseDto doctor = doctorService.getDoctorById(doctorId);
		ApiResponse<DoctorResponseDto> response = ApiResponse.<DoctorResponseDto>builder()
			.status(ApiResponse.SUCCESS_STATUS)
			.message("의사 정보가 성공적으로 검색되었습니다.")
			.data(doctor)
			.build();
		return ResponseEntity.ok(response);
	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<DoctorResponseDto>>> getAllDoctorsByHospitalId(
		@PathVariable("hospital_id") Long hospitalId) {
		List<DoctorResponseDto> doctors = doctorService.getAllDoctorsByHospitalId(hospitalId);
		ApiResponse<List<DoctorResponseDto>> response = ApiResponse.<List<DoctorResponseDto>>builder()
			.status(ApiResponse.SUCCESS_STATUS)
			.message("해당 병원의 모든 의사가 성공적으로 검색되었습니다.")
			.data(doctors)
			.build();
		return ResponseEntity.ok(response);
	}

}
