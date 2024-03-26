package com.kidsqueue.kidsqueue.parent.db;

import com.kidsqueue.kidsqueue.favor.entity.FavorEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.util.List;
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
    private Long id;

    @Column(unique = true, length = 32)
    private String loginId;
    @Column(nullable = false)
    private String password;
    @Column(unique = true, length = 16)
    private String nickname;
    @Column(length = 5)
    private String name;
    @Column(length = 3)
    private String age;
    @Column(length = 1)
    private String gender;
    @Column(length = 13)
    private String phoneNumber;
    @Column(length = 13)
    private String residentRegistrationNumber;
    private String profileImageUrl;
    private String email;

    private String role;


    @OneToMany(mappedBy = "parent")
    private List<FavorEntity> favorEntityList;

    private LocalDateTime createdBy;
    private LocalDateTime updatedBy;
    @Column(columnDefinition = "tinyInt")
    private Integer IsActive;

    @PrePersist // DB에 INSERT 되기 직전에 실행
    public void createDate() {
        this.createdBy = LocalDateTime.now();
    }

    @PreUpdate // DB에 UPDATE 되기 직전에 실행
    public void updateDate() {
        this.updatedBy = LocalDateTime.now();
    }
}

