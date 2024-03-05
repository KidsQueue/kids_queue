package com.kidsqueue.kidsqueue.domain.Parent;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String loginId;

    @Column(nullable = false)
    private String password;
    private String nickname;
    private String name;
    private String age;
    private String gender;
    private String phoneNumber;
    private String residentRegistrationNumber;
    private String profileImageUrl;

    private LocalDateTime createBy;
    private LocalDateTime updateBy;

    @PrePersist // DB에 INSERT 되기 직전에 실행
    public void createDate() {
        this.createBy = LocalDateTime.now();
    }

    @PreUpdate // DB에 UPDATE 되기 직전에 실행
    public void updateDate() {
        this.updateBy = LocalDateTime.now();
    }
}

