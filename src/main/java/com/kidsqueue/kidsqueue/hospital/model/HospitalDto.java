package com.kidsqueue.kidsqueue.hospital.model;

import com.kidsqueue.kidsqueue.doctor.db.Doctor;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HospitalDto {
    private Long id;
    private String name;
    private String adress;
    private String description;
    private String phoneNumber;
    private String status;
    private Integer maxNumOfPeople;
    private LocalDateTime createdBy;
    private LocalDateTime updatedBy;
    private Integer isActive;

    private List<Doctor> doctorList;

}