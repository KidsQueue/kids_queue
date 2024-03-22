package com.kidsqueue.kidsqueue.doctor.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorRequestDto { // 의사 생성 시 필요 데이터

	private Long hospitalId; // 연결될 의사 아이디
	private String name; // 의사 이름
	private String profileImageUrl; //의사 사진
	private String description; //의사 설명
	private Integer isActive; //의사 활동 여부(1,0)

}
