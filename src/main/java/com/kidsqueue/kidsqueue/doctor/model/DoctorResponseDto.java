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
public class DoctorResponseDto { //의사 생성 완료 시 데이터

    private Long id;
    private Long hospitalId;
    private String name;
    private String profileImageUrl;
    private String description;
    private LocalDateTime createdBy;
    private LocalDateTime updatedBy;
    private Integer isActive;

}
