package com.kidsqueue.kidsqueue.hospital.service;

import com.kidsqueue.kidsqueue.hospital.db.Hospital;
import com.kidsqueue.kidsqueue.hospital.db.enums.Status;
import com.kidsqueue.kidsqueue.hospital.model.HospitalDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class HospitalConverter {

    public HospitalDto toDto(Hospital hospital) {

        return HospitalDto.builder()
            .id(hospital.getId())
            .name(hospital.getName())
            .address(hospital.getAddress())
            .description(hospital.getDescription())
            .phoneNumber(hospital.getPhoneNumber())
            .status(hospital.getStatus().name())
            .maxNumOfPeople(hospital.getMaxNumOfPeople())
            .createdBy(hospital.getCreatedBy())
            .updatedBy(hospital.getUpdatedBy())
            .isActive(hospital.getIsActive())
            .build();

    }

    public Hospital toEntity(HospitalDto hospitalDto) {
        Hospital hospital = new Hospital();
        BeanUtils.copyProperties(hospitalDto, hospital);
        hospital.setStatus(Status.valueOf(hospitalDto.getStatus()));
        return hospital;
    }
}
