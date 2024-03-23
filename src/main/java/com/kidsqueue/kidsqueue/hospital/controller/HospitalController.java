package com.kidsqueue.kidsqueue.hospital.controller;

import com.kidsqueue.kidsqueue.common.Api;
import com.kidsqueue.kidsqueue.hospital.model.HospitalDto;
import com.kidsqueue.kidsqueue.hospital.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/hospital")
@RequiredArgsConstructor
public class HospitalController {

	private final HospitalService hospitalService;

	//  조건에 맞는 병원 리스트 가져오기
	@GetMapping
	public Api<List<HospitalDto>> get(
		@PageableDefault(page = 0, size = 10) Pageable pageable,
		@RequestParam(required = false) String sortCondition,
		@RequestParam(required = false) String name
	) {

		System.out.println("sortCondition = " + sortCondition);
		System.out.println("name = " + name);

		Api<List<HospitalDto>> api;

		//  등록일 기준 내림차순인 경우
		if ("desc".equals(sortCondition)) {
			api = hospitalService.findAllDesc(pageable);
			api.setMessage("등록일 기준 내림차순으로 병원리스트 가져옴");
		}

		//  병원명으로 검색한 경우
		else if ("name".equals(sortCondition)) {
			api = hospitalService.findAllByName(name, pageable);
			api.setMessage("병원명에 검색조건을 포함하는 병원리스트 정확도 순으로 가져옴");
		}

		//  등록일 기준 오름차순인 경우
		else if ("asc".equals(sortCondition)) {
			api = hospitalService.findAllAsc(pageable);
			api.setMessage("등록일 기준 오름차순으로 병원리스트 가져옴");
		}

		//  아무조건 없는 경우
		else {
			api = hospitalService.findAllAsc(pageable);
			api.setMessage("등록일 기준 오름차순으로 병원리스트 가져옴");
		}

		api.setStatus(Api.SUCCESS_STATUS);

		return api;
	}

	// 특정 병원 조회
	@GetMapping("/{id}")
	public ResponseEntity<Api<HospitalDto>> getHospitalById(@PathVariable Long id) {
		Api<HospitalDto> api = hospitalService.findHospitalById(id);

		if (api.getData() != null) {
			api.setStatus(Api.SUCCESS_STATUS);
			api.setMessage("해당 병원 정보 조회 성공");
			return new ResponseEntity<>(api, HttpStatus.OK);
		} else {
			api.setStatus(Api.ERROR_STATUS);
			api.setMessage("해당 병원을 찾을 수 없습니다.");
			return new ResponseEntity<>(api, HttpStatus.NOT_FOUND);
		}
	}

	// 병원 정보 생성
	@PostMapping
	public ResponseEntity<Api<HospitalDto>> createHospital(
		@RequestBody HospitalDto hospitalDto) {
		Api<HospitalDto> api = hospitalService.createHospital(hospitalDto);

		if (api.getData() != null) {
			api.setStatus(Api.SUCCESS_STATUS);
			api.setMessage("병원 정보 생성 성공");
			return new ResponseEntity<>(api, HttpStatus.CREATED);
		} else {
			api.setStatus(Api.ERROR_STATUS);
			api.setMessage("병원 정보 생성 실패");
			return new ResponseEntity<>(api, HttpStatus.BAD_REQUEST);
		}
	}

	// 병원 정보 수정
	@PatchMapping("/{id}")
	public ResponseEntity<Api<HospitalDto>> updateHospital(@PathVariable Long id,
		@RequestBody HospitalDto hospitalDto) {
		Api<HospitalDto> api = hospitalService.updateHospital(id, hospitalDto);

		if (api.getData() != null) {
			api.setStatus(Api.SUCCESS_STATUS);
			api.setMessage("병원 정보 수정 성공");
			return new ResponseEntity<>(api, HttpStatus.OK);
		} else {
			api.setStatus(Api.ERROR_STATUS);
			api.setMessage("병원 정보 수정 실패");
			return new ResponseEntity<>(api, HttpStatus.BAD_REQUEST);
		}
	}

	// 병원 정보 삭제
	@DeleteMapping("/{id}")
	public ResponseEntity<Api<String>> deleteHospital(@PathVariable Long id) {
		Api<String> api = hospitalService.deleteHospital(id);

		if (api.getData() == null) {
			api.setStatus(Api.SUCCESS_STATUS);
			api.setMessage("병원 정보 삭제 성공");
			return new ResponseEntity<>(api, HttpStatus.NO_CONTENT);
		} else {
			api.setStatus(Api.ERROR_STATUS);
			api.setMessage("병원 정보 삭제 실패");
			return new ResponseEntity<>(api, HttpStatus.BAD_REQUEST);
		}
	}

}