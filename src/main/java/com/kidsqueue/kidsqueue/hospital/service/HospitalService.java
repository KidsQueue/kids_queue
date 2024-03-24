package com.kidsqueue.kidsqueue.hospital.service;

import com.kidsqueue.kidsqueue.common.ApiResponse;
import com.kidsqueue.kidsqueue.common.Pagination;
import com.kidsqueue.kidsqueue.hospital.db.Hospital;
import com.kidsqueue.kidsqueue.hospital.db.HospitalRepository;
import com.kidsqueue.kidsqueue.hospital.db.enums.Status;
import com.kidsqueue.kidsqueue.hospital.model.HospitalDto;
import java.util.Optional;
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

    public ApiResponse<HospitalDto> findHospitalById(Long id) {
        Optional<Hospital> hospitalOptional = hospitalRepository.findById(id);

        if (hospitalOptional.isPresent()) {
            HospitalDto hospitalDto = hospitalConverter.toDto(hospitalOptional.get());
            return ApiResponse.<HospitalDto>builder()
                .data(hospitalDto)
                .status(ApiResponse.SUCCESS_STATUS)
                .message("병원 정보 조회 성공")
                .build();
        } else {
            return ApiResponse.<HospitalDto>builder()
                .status(ApiResponse.ERROR_STATUS)
                .message("해당 병원을 찾을 수 없습니다.")
                .build();
        }
    }
    public ApiResponse<HospitalDto> createHospital(HospitalDto hospitalDto) {
        // DTO를 엔티티로 변환
        Hospital hospital = hospitalConverter.toEntity(hospitalDto);

        // 엔티티 저장
        Hospital savedHospital = hospitalRepository.save(hospital);

        // 저장된 엔티티를 DTO로 변환하여 ApiResponse에 담아 반환
        HospitalDto savedHospitalDto = hospitalConverter.toDto(savedHospital);
        return ApiResponse.<HospitalDto>builder()
            .data(savedHospitalDto)
            .status(ApiResponse.SUCCESS_STATUS)
            .message("병원 정보 생성 성공")
            .build();
    }

    public ApiResponse<HospitalDto> updateHospital(Long id, HospitalDto hospitalDto) {
        Optional<Hospital> hospitalOptional = hospitalRepository.findById(id);

        if (hospitalOptional.isPresent()) {
            // 엔티티 수정
            Hospital existingHospital = hospitalOptional.get();
            existingHospital.setName(hospitalDto.getName());
            existingHospital.setAddress(hospitalDto.getAddress());
            existingHospital.setDescription(hospitalDto.getDescription());
            existingHospital.setPhoneNumber(hospitalDto.getPhoneNumber());
            existingHospital.setStatus(Status.valueOf(hospitalDto.getStatus()));
            existingHospital.setMaxNumOfPeople(hospitalDto.getMaxNumOfPeople());
            existingHospital.setIsActive(hospitalDto.getIsActive());

            // 수정된 엔티티 저장
            Hospital updatedHospital = hospitalRepository.save(existingHospital);

            // 엔티티를 DTO로 변환하여 ApiResponse에 담아 반환
            HospitalDto updatedHospitalDto = hospitalConverter.toDto(updatedHospital);
            return ApiResponse.<HospitalDto>builder()
                .data(updatedHospitalDto)
                .status(ApiResponse.SUCCESS_STATUS)
                .message("병원 정보 수정 성공")
                .build();
        } else {
            return ApiResponse.<HospitalDto>builder()
                .status(ApiResponse.ERROR_STATUS)
                .message("해당 병원을 찾을 수 없습니다.")
                .build();
        }
    }

    public ApiResponse<String> deleteHospital(Long id) {
        Optional<Hospital> hospitalOptional = hospitalRepository.findById(id);

        if (hospitalOptional.isPresent()) {
            // 엔티티 삭제
            hospitalRepository.delete(hospitalOptional.get());

            return ApiResponse.<String>builder()
                .status(ApiResponse.SUCCESS_STATUS)
                .message("병원 정보 삭제 성공")
                .data("병원 정보가 성공적으로 삭제되었습니다.")
                .build();
        } else {
            return ApiResponse.<String>builder()
                .status(ApiResponse.ERROR_STATUS)
                .message("해당 병원을 찾을 수 없습니다.")
                .build();
        }
    }

}

