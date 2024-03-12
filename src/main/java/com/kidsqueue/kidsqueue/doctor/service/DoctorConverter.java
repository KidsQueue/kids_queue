package com.kidsqueue.kidsqueue.doctor.service;

import com.kidsqueue.kidsqueue.doctor.db.Doctor;
import com.kidsqueue.kidsqueue.doctor.model.DoctorDto;
import org.springframework.stereotype.Service;

@Service
public class DoctorConverter {

    public DoctorDto toDto(Doctor doctor) {

        return DoctorDto.builder().id(doctor.getId())
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
