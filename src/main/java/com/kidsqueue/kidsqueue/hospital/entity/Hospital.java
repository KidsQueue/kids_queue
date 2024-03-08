package com.kidsqueue.kidsqueue.hospital.entity;


import com.kidsqueue.kidsqueue.hospital.entity.enums.HospitalStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
@NoArgsConstructor
@Entity
public class Hospital {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String address;
	@Column(nullable = false)
	private String description;
	@Column(nullable = false)
	private String phoneNumber;

	// ENUM 매핑
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private HospitalStatus status;

	@Column(nullable = false, columnDefinition = "TINYINT default 5")
	private int maxNumOfPeople;

	@Column(nullable = false)
	@CreatedDate
	private LocalDateTime createBy;

	@Column(nullable = false)
	@LastModifiedDate
	private LocalDateTime updateBy;

	@Column(nullable = false,columnDefinition = "TINYINT default 1")
	private boolean isActive;

	@PrePersist // DB에 INSERT 되기 직전에 실행
	public void createDate() {
		this.createBy = LocalDateTime.now();
	}
	@PreUpdate // DB에 UPDATE 되기 직전에 실행
	public void updateDate() {
		this.updateBy = LocalDateTime.now();
	}

}
