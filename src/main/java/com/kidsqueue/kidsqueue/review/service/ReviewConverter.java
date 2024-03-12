package com.kidsqueue.kidsqueue.review.service;

import com.kidsqueue.kidsqueue.review.db.Review;
import com.kidsqueue.kidsqueue.review.model.ReviewDto;
import org.springframework.stereotype.Service;

@Service
public class ReviewConverter {
    public ReviewDto toDto(Review review) {
        return ReviewDto.builder()
            .id(review.getId())
            .title(review.getTitle())
            .score(review.getScore())
            .description(review.getDescription())
            .createdBy(review.getCreatedBy())
            .updatedBy(review.getUpdatedBy())
            .isActive(review.getIsActive())
            .hospitalId(review.getId())
            .parentId(review.getId())
            .build();
    }
}
