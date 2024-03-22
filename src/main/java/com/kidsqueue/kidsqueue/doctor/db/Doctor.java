package com.kidsqueue.kidsqueue.doctor.db;

import com.kidsqueue.kidsqueue.common.BaseEntity;
import com.kidsqueue.kidsqueue.hospital.db.Hospital;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@Table(name = "doctor")
public class Doctor extends BaseEntity {

    private String name;
    private String profileImageUrl;
    private String description;

    @Column(columnDefinition = "tinyInt")
    private Integer isActive;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

}
