package com.kidsqueue.kidsqueue.hospital.db;


import com.kidsqueue.kidsqueue.hospital.db.enums.DayOfWeek;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.sql.Time;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "clinichour")
public class ClinicHour {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "hospital_id")
	private Hospital hospital;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private DayOfWeek dayOfWeek;

	@Column(nullable = false)
	private Time openingTime;

	@Column(nullable = false)
	private Time closingTime;

	private Time lunchStartTime;

	private Time lunchEndTime;

	@Column(nullable = false)
	private LocalDateTime createdBy;

	@Column(nullable = false)
	private LocalDateTime updatedBy;

	@Column(columnDefinition = "tinyInt")
	private Integer isActive;

}
