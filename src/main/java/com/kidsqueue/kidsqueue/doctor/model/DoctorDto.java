package com.kidsqueue.kidsqueue.doctor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorDto {

    private Long id;
    private String name;
    private String profileImageUrl;
    private String description;
    private LocalDateTime createdBy;
    private LocalDateTime updatedBy;
    private Integer isActive;

    private Long hospitalId;

}
