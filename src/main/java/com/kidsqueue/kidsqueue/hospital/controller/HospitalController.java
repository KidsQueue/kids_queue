package com.kidsqueue.kidsqueue.hospital.controller;

import com.kidsqueue.kidsqueue.common.ApiResponse;
import com.kidsqueue.kidsqueue.hospital.model.HospitalDto;
import com.kidsqueue.kidsqueue.hospital.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/hospital")
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;

    @GetMapping
    public ApiResponse<List<HospitalDto>> get(
        @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {

        ApiResponse<List<HospitalDto>> apiResponse = hospitalService.findAll(pageable);

        apiResponse.setStatus(ApiResponse.SUCCESS_STATUS);
        apiResponse.setMessage("응답 성공");

        return apiResponse;
    }

    // 특정 병원 조회
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<HospitalDto>> getHospitalById(@PathVariable Long id) {
        ApiResponse<HospitalDto> apiResponse = hospitalService.findHospitalById(id);

        if (apiResponse.getData() != null) {
            apiResponse.setStatus(ApiResponse.SUCCESS_STATUS);
            apiResponse.setMessage("해당 병원 정보 조회 성공");
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } else {
            apiResponse.setStatus(ApiResponse.ERROR_STATUS);
            apiResponse.setMessage("해당 병원을 찾을 수 없습니다.");
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
    }

    // 병원 정보 생성
    @PostMapping
    public ResponseEntity<ApiResponse<HospitalDto>> createHospital(@RequestBody HospitalDto hospitalDto) {
        ApiResponse<HospitalDto> apiResponse = hospitalService.createHospital(hospitalDto);

        if (apiResponse.getData() != null) {
            apiResponse.setStatus(ApiResponse.SUCCESS_STATUS);
            apiResponse.setMessage("병원 정보 생성 성공");
            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
        } else {
            apiResponse.setStatus(ApiResponse.ERROR_STATUS);
            apiResponse.setMessage("병원 정보 생성 실패");
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }

    // 병원 정보 수정
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<HospitalDto>> updateHospital(@PathVariable Long id, @RequestBody HospitalDto hospitalDto) {
        ApiResponse<HospitalDto> apiResponse = hospitalService.updateHospital(id, hospitalDto);

        if (apiResponse.getData() != null) {
            apiResponse.setStatus(ApiResponse.SUCCESS_STATUS);
            apiResponse.setMessage("병원 정보 수정 성공");
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } else {
            apiResponse.setStatus(ApiResponse.ERROR_STATUS);
            apiResponse.setMessage("병원 정보 수정 실패");
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }

    // 병원 정보 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteHospital(@PathVariable Long id) {
        ApiResponse<String> apiResponse = hospitalService.deleteHospital(id);

        if (apiResponse.getData() == null) {
            apiResponse.setStatus(ApiResponse.SUCCESS_STATUS);
            apiResponse.setMessage("병원 정보 삭제 성공");
            return new ResponseEntity<>(apiResponse, HttpStatus.NO_CONTENT);
        } else {
            apiResponse.setStatus(ApiResponse.ERROR_STATUS);
            apiResponse.setMessage("병원 정보 삭제 실패");
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }

}