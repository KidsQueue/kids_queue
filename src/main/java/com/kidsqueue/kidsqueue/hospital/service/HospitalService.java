package com.kidsqueue.kidsqueue.hospital.service;

import com.kidsqueue.kidsqueue.common.ApiResponse;
import com.kidsqueue.kidsqueue.common.Pagination;
import com.kidsqueue.kidsqueue.hospital.db.Hospital;
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

    public ApiResponse<List<HospitalDto>> findAllAsc(Pageable pageable) {

        //  Page<Hospital> 을 Page<HospitalDto> 로 변환
        Page<HospitalDto> hospitalDtoPage = hospitalRepository.findAllByIsActiveOrderByIdAsc(1, pageable)
                .map(hospitalConverter::toDto);

        //  Page<HospitalDto> 로 ApiResponse<List<HospitalDto>> 얻어와서 리턴
        return getApiResponse(hospitalDtoPage);


    }

    public ApiResponse<List<HospitalDto>> findAllDesc(Pageable pageable) {

        //  Page<Hospital> 을 Page<HospitalDto> 로 변환
        Page<HospitalDto> hospitalDtoPage = hospitalRepository.findAllByIsActiveOrderByIdDesc(1, pageable)
                .map(hospitalConverter::toDto);

        //  Page<HospitalDto> 로 ApiResponse<List<HospitalDto>> 얻어와서 리턴
        return getApiResponse(hospitalDtoPage);

    }

    public ApiResponse<List<HospitalDto>> findAllByName(String name, Pageable pageable) {

        //  Page<Hospital> 을 Page<HospitalDto> 로 변환
        Page<HospitalDto> hospitalDtoPage = hospitalRepository.findAllByIsActiveAndNameNative(1, name, pageable)
                .map(hospitalConverter::toDto);

        //  Page<HospitalDto> 로 ApiResponse<List<HospitalDto>> 얻어와서 리턴
        return getApiResponse(hospitalDtoPage);



    }

    //  Page<HospitalDto> 로 ApiResponse<List<HospitalDto>> 만드는 메서드
    private static ApiResponse<List<HospitalDto>> getApiResponse(Page<HospitalDto> hospitalDtoPage) {

        //  Page<HospitalDto> 에서 List<HospitalDto> 가져오기
        List<HospitalDto> hospitalDtoList = hospitalDtoPage.toList();

        //  Page<HospitalDto> 로 Pagination 생성
        Pagination pagination = Pagination.builder()
                .page(hospitalDtoPage.getNumber())
                .size(hospitalDtoPage.getSize())
                .currentElements(hospitalDtoPage.getNumberOfElements())
                .totalElements(hospitalDtoPage.getTotalElements())
                .totalPage(hospitalDtoPage.getTotalPages())
                .build();

        //  ApiResponse<List<HospitalDto>> 만들어서 리턴
        return  ApiResponse.<List<HospitalDto>>builder()
                .data(hospitalDtoList)
                .pagination(pagination)
                .build();
    }

}
