package com.kidsqueue.kidsqueue.favor.dto;


import com.kidsqueue.kidsqueue.hospital.model.HospitalDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavorResponse {

	private Long id;
	private Long parent_id;
	private HospitalDto hospitalDto;

}
