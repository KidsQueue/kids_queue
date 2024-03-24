package com.kidsqueue.kidsqueue.doctor.service;

import com.kidsqueue.kidsqueue.doctor.model.DoctorResponseDto;
import com.kidsqueue.kidsqueue.hospital.db.Hospital;
import java.util.Optional;
import com.kidsqueue.kidsqueue.doctor.db.Doctor;
import com.kidsqueue.kidsqueue.doctor.model.DoctorRequestDto;
import com.kidsqueue.kidsqueue.hospital.db.HospitalRepository;
import org.springframework.stereotype.Service;

@Service
public class DoctorConverter {

    private final HospitalRepository hospitalRepository;

    public DoctorConverter(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    /**
     * DoctorRequestDto를 엔티티 데이터로 변환
     *
     * @param request DoctorRequestDto
     * @return Doctor 엔티티
     * @throws IllegalArgumentException 병원을 찾을 수 없는 경우
     */
    public Doctor toEntity(DoctorRequestDto request) {
        // 병원 ID를 사용하여 병원 엔티티를 조회
        Hospital hospital = hospitalRepository.findById(request.getHospitalId())
            .orElseThrow(() -> new IllegalArgumentException("Hospital not found"));

        return Doctor.builder()
            .hospital(hospital)
            .name(request.getName())
            .profileImageUrl(request.getProfileImageUrl())
            .description(request.getDescription())
            .isActive(request.getIsActive())
            .build();
    }

    /**
     * Doctor 엔티티를 DoctorResponseDto로 변환
     *
     * @param doctor Doctor 엔티티
     * @return DoctorResponseDto
     * @throws IllegalArgumentException doctor가 NULL인 경우
     */
    public DoctorResponseDto toDto(Doctor doctor) {
        return Optional.ofNullable(doctor)
            .map(it -> DoctorResponseDto.builder()
                .id(doctor.getId())
                .hospitalId(doctor.getHospital() != null ? doctor.getHospital().getId() : null)
                .name(doctor.getName())
                .profileImageUrl(doctor.getProfileImageUrl())
                .description(doctor.getDescription())
                .createdBy(doctor.getCreatedBy())
                .updatedBy(doctor.getUpdatedBy())
                .isActive(doctor.getIsActive())
                .build())
            .orElseThrow(() -> new IllegalArgumentException("Doctor is null"));
    }
}