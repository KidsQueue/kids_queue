package com.kidsqueue.kidsqueue.doctor.db;

import com.kidsqueue.kidsqueue.hospital.db.Hospital;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "doctor")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String profileImageUrl;
    private String description;
    private LocalDateTime createdBy;
    private LocalDateTime updatedBy;
    @Column(columnDefinition = "tinyInt")
    private Integer isActive;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;
}
