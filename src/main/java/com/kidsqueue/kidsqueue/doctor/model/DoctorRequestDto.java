package com.kidsqueue.kidsqueue.doctor.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorRequestDto { // 의사 생성 시 필요 데이터

	@NotNull
	private Long hospitalId; // 연결될 병원 아이디

	@NotBlank
	private String name; // 의사 이름

	@NotBlank
	private String profileImageUrl; //의사 사진

	@NotBlank
	private String description; //의사 설명

	@NotNull
	private Integer isActive; //의사 활동 여부(1,0)

}
