package com.kidsqueue.kidsqueue.hospital.db;

import com.kidsqueue.kidsqueue.doctor.db.Doctor;
import com.kidsqueue.kidsqueue.favor.entity.FavorEntity;
import com.kidsqueue.kidsqueue.hospital.db.enums.Status;
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

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'closed'")
	private Status status;

	@Column(columnDefinition = "tinyInt")
	private Integer maxNumOfPeople;
	private LocalDateTime createdBy;
	private LocalDateTime updatedBy;
	@Column(columnDefinition = "tinyInt")
	private Integer isActive;

	@OneToMany(mappedBy = "hospital")
	private List<Doctor> doctorList;

	@OneToMany(mappedBy = "hospital")
	private List<ClinicHour> clinicHour;

	@OneToMany(mappedBy = "hospital")
	private List<FavorEntity> favorEntityList;

	@PrePersist // DB에 INSERT 되기 직전에 실행
	public void createDate() {
		this.createdBy = LocalDateTime.now();
	}

	@PreUpdate // DB에 UPDATE 되기 직전에 실행
	public void updateDate() {
		this.updatedBy = LocalDateTime.now();
	}


}
