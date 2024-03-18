package com.kidsqueue.kidsqueue.review.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ReviewDto {
    private final Long id;
    private final Long parentId;
    private final Long hospitalId;
    private String title;
    private Integer score;
    private String description;
    private LocalDateTime createdBy;
    private LocalDateTime updatedBy;
    private Integer isActive;
}
