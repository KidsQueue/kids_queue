package com.kidsqueue.kidsqueue.favor.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FavorDto {

	private Long id;
	private Long parentId;
	private Long hospitalId;
	private Integer isActive;
	private LocalDateTime createdBy;
	private LocalDateTime updatedBy;


}
