package com.kidsqueue.kidsqueue.hospital.controller;

import com.kidsqueue.kidsqueue.common.ApiResponse;
import com.kidsqueue.kidsqueue.hospital.model.HospitalDto;
import com.kidsqueue.kidsqueue.hospital.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
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

}
