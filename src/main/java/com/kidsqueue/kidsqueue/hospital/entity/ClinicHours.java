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
import java.time.LocalTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "ClinicHour")
public class ClinicHours {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "hospital_id", nullable = false)
	private Hospital hospital;

	@Column(name = "day_of_week", nullable = false)
	private String dayOfWeek;

	@Column(name = "opening_time", nullable = false)
	private LocalTime openingTime;

	@Column(name = "closing_time", nullable = false)
	private LocalTime closingTime;

	@Column(name = "lunch_start_time")
	private LocalTime lunchStartTime;

	@Column(name = "lunch_end_time")
	private LocalTime lunchEndTime;

	@Column(name = "created_by", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime createdBy;

	@Column(name = "updated_by", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private LocalDateTime updatedBy;

	@Column(name = "is_active", nullable = false, columnDefinition = "TINYINT DEFAULT 1")
	private boolean isActive;


}
