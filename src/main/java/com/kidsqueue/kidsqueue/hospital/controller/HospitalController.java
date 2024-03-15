package com.kidsqueue.kidsqueue.hospital.controller;

import com.kidsqueue.kidsqueue.common.ApiResponse;
import com.kidsqueue.kidsqueue.hospital.model.HospitalDto;
import com.kidsqueue.kidsqueue.hospital.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
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
    public ApiResponse<List<HospitalDto>> get(
            @PageableDefault(page = 0, size = 10) Pageable pageable,
            @RequestParam(required = false) String sortCondition,
            @RequestParam(required = false) String name
    ) {

        System.out.println("sortCondition = " + sortCondition);
        System.out.println("name = " + name);

        ApiResponse<List<HospitalDto>> apiResponse;

        //  등록일 기준 내림차순인 경우
        if ("desc".equals(sortCondition)) {
            apiResponse = hospitalService.findAllDesc(pageable);
            apiResponse.setMessage("등록일 기준 내림차순으로 병원리스트 가져옴");
        }

        //  병원명으로 검색한 경우
        else if ("name".equals(sortCondition)) {
            apiResponse = hospitalService.findAllByName(name, pageable);
            apiResponse.setMessage("병원명에 검색조건을 포함하는 병원리스트 정확도 순으로 가져옴");
        }

        //  등록일 기준 오름차순인 경우
        else if ("asc".equals(sortCondition)) {
            apiResponse = hospitalService.findAllAsc(pageable);
            apiResponse.setMessage("등록일 기준 오름차순으로 병원리스트 가져옴");
        }

        //  아무조건 없는 경우
        else {
            apiResponse = hospitalService.findAllAsc(pageable);
            apiResponse.setMessage("등록일 기준 오름차순으로 병원리스트 가져옴");
        }

        apiResponse.setStatus(ApiResponse.SUCCESS_STATUS);

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