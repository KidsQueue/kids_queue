package com.kidsqueue.kidsqueue.hospital.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "Doctor")
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "hospital_id", nullable = false)
	private Hospital hospital;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "profile_image_url")
	private String profileImageUrl;

	@Column(name = "description")
	private String description;

	@Column(name = "created_by", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime createdBy;

	@Column(name = "updated_by", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private LocalDateTime updatedBy;

	@Column(name = "is_active", nullable = false, columnDefinition = "TINYINT DEFAULT 1")
	private boolean isActive;


}
