package com.kidsqueue.kidsqueue.hospital.db;

import com.kidsqueue.kidsqueue.common.BaseEntity;
import com.kidsqueue.kidsqueue.doctor.db.Doctor;
import com.kidsqueue.kidsqueue.hospital.db.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import lombok.experimental.SuperBuilder;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@Table(name = "store_menu")
public class Hospital extends BaseEntity {

    private String name;
    private String address;
    private String description;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'closed'" )
    private Status status;

    @Column(columnDefinition = "tinyInt")
    private Integer maxNumOfPeople;

    @Column(columnDefinition = "tinyInt")
    private Integer isActive;

    @OneToMany(mappedBy = "hospital")
    private List<Doctor> doctorList;

    @OneToMany(mappedBy = "hospital")
    private List<ClinicHour> clinicHour;


}
