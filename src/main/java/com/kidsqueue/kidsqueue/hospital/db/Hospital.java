package com.kidsqueue.kidsqueue.hospital.db;

import com.kidsqueue.kidsqueue.doctor.db.Doctor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "hospital")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String description;
    private String phoneNumber;
    private String status;
    @Column(columnDefinition = "tinyInt")
    private Integer maxNumOfPeople;
    private LocalDateTime createdBy;
    private LocalDateTime updatedBy;
    @Column(columnDefinition = "tinyInt")
    private Integer isActive;

    @OneToMany(mappedBy = "hospital")
    private List<Doctor> doctorList;

}
