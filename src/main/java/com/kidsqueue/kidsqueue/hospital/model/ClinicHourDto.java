package com.kidsqueue.kidsqueue.hospital.model;


import com.kidsqueue.kidsqueue.hospital.db.ClinicHour;
import com.kidsqueue.kidsqueue.hospital.db.enums.DayOfWeek;
import java.sql.Time;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClinicHourDto {

	private Long id;
	private Long hospitalId;
	private DayOfWeek dayOfWeek;
	private Time openingTime;
	private Time closingTime;
	private Time lunchStartTime;
	private Time lunchEndTime;
	private Integer isActive;


}