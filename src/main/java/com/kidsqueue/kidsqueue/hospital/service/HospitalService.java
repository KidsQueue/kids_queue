package com.kidsqueue.kidsqueue.hospital.service;

import com.kidsqueue.kidsqueue.common.ApiResponse;
import com.kidsqueue.kidsqueue.common.Pagination;
import com.kidsqueue.kidsqueue.hospital.db.HospitalRepository;
import com.kidsqueue.kidsqueue.hospital.model.HospitalDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;
    private final HospitalConverter hospitalConverter;

    public ApiResponse<List<HospitalDto>> findAll(Pageable pageable) {

        Page<HospitalDto> hospitalDtoPage = hospitalRepository.findAllByIsActive(1, pageable)
                .map(hospitalConverter::toDto);

        List<HospitalDto> hospitalDtoList = hospitalDtoPage.toList();

        Pagination pagination = Pagination.builder()
                .page(hospitalDtoPage.getNumber())
                .size(hospitalDtoPage.getSize())
                .currentElements(hospitalDtoPage.getNumberOfElements())
                .totalElements(hospitalDtoPage.getTotalElements())
                .totalPage(hospitalDtoPage.getTotalPages())
                .build();

        ApiResponse<List<HospitalDto>> apiResponse = ApiResponse.<List<HospitalDto>>builder()
                .data(hospitalDtoList)
                .pagination(pagination)
                .build();

        return apiResponse;

    }
}
