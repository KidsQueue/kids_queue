package com.kidsqueue.kidsqueue.hospital.service;

import com.kidsqueue.kidsqueue.hospital.db.Hospital;
import com.kidsqueue.kidsqueue.hospital.model.HospitalDto;
import org.springframework.stereotype.Service;

@Service
public class HospitalConverter {

    public HospitalDto toDto(Hospital hospital) {

        return HospitalDto.builder()
                .id(hospital.getId())
                .name(hospital.getName())
                .adress(hospital.getAddress())
                .description(hospital.getDescription())
                .phoneNumber(hospital.getPhoneNumber())
                .status(hospital.getStatus())
                .maxNumOfPeople(hospital.getMaxNumOfPeople())
                .createdBy(hospital.getCreatedBy())
                .updatedBy(hospital.getUpdatedBy())
                .isActive(hospital.getIsActive())
                .build();

    }

}
