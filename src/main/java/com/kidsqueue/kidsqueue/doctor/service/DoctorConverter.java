package com.kidsqueue.kidsqueue.doctor.service;

import com.kidsqueue.kidsqueue.doctor.db.Doctor;
import com.kidsqueue.kidsqueue.doctor.model.DoctorResponseDto;
import org.springframework.stereotype.Service;

@Service
public class DoctorConverter {

    public DoctorResponseDto toDto(Doctor doctor) {

        return DoctorResponseDto.builder()
                .id(doctor.getId())
                .name(doctor.getName())
                .profileImageUrl(doctor.getProfileImageUrl())
                .description(doctor.getDescription())
                .createdBy(doctor.getCreatedBy())
                .updatedBy(doctor.getUpdatedBy())
                .isActive(doctor.getIsActive())
                .hospitalId(doctor.getHospital().getId())
                .build();
    }

}
