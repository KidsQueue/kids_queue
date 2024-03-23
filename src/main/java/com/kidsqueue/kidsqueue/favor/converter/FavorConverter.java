package com.kidsqueue.kidsqueue.favor.converter;

import com.kidsqueue.kidsqueue.favor.dto.FavorDto;
import com.kidsqueue.kidsqueue.favor.dto.FavorResponse;
import com.kidsqueue.kidsqueue.favor.entity.FavorEntity;
import com.kidsqueue.kidsqueue.hospital.db.Hospital;
import com.kidsqueue.kidsqueue.hospital.service.HospitalConverter;
import com.kidsqueue.kidsqueue.parent.db.Parent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FavorConverter {

	private final HospitalConverter hospitalConverter;

	public FavorResponse toFavorResponse(FavorEntity favorEntity) {
		return FavorResponse.builder()
			.id(favorEntity.getId())
			.parent_id(favorEntity.getParent().getId())
			.hospitalDto(hospitalConverter.toDto(favorEntity.getHospital()))
			.build();
	}

	public FavorEntity toFavorEntity(Parent parent, Hospital hospital) {
		return FavorEntity.builder()
			.parent(parent)
			.hospital(hospital)
			.build();
	}

	public FavorDto toFavorDto(FavorEntity favorEntity) {
		return FavorDto.builder()
			.id(favorEntity.getId())
			.parentId(favorEntity.getParent().getId())
			.hospitalId(favorEntity.getHospital().getId())
			.isActive(favorEntity.getIsActive())
			.createdBy(favorEntity.getCreatedBy())
			.updatedBy(favorEntity.getUpdatedBy())
			.build();
	}



}
