package com.kidsqueue.kidsqueue.hospital.controller;

import com.kidsqueue.kidsqueue.common.ApiResponse;
import com.kidsqueue.kidsqueue.hospital.model.HospitalDto;
import com.kidsqueue.kidsqueue.hospital.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
