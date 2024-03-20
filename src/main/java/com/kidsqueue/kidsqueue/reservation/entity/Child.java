package com.kidsqueue.kidsqueue.reservation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String age;
    private String gender;
    private String residentRegistrationNumber;
    private String profileImageUrl;
    private Timestamp createdBy;
    private Timestamp updatedBy;
    private Boolean isActive;

    @PrePersist // DB에 INSERT 되기 직전에 실행
    public void createDate() {
        this.createdBy = Timestamp.valueOf(LocalDateTime.now());
        this.updatedBy = Timestamp.valueOf(LocalDateTime.now());
    }

    @PreUpdate // DB에 UPDATE 되기 직전에 실행
    public void updateDate() {
        this.updatedBy = Timestamp.valueOf(LocalDateTime.now());
    }

    public void disabled() {
        isActive = false;
    }
}
