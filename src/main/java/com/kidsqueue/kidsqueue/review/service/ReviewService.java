package com.kidsqueue.kidsqueue.review.service;

import com.kidsqueue.kidsqueue.common.ApiResponse;
import com.kidsqueue.kidsqueue.common.Pagination;
import com.kidsqueue.kidsqueue.review.db.Review;
import com.kidsqueue.kidsqueue.review.db.ReviewRepository;
import com.kidsqueue.kidsqueue.review.model.ReviewDto;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewConverter reviewConverter;

    public ApiResponse<List<ReviewDto>> findAllAsc(Pageable pageable) {

        // Page<Review> 를 Page<ReviewDto> 로 변환
        Page<ReviewDto> reviewDtoPage = reviewRepository.findAllByIsActiveOrderByIdAsc(1, pageable)
            .map(reviewConverter::toDto);
        return getApiResponse(reviewDtoPage);
    }

    public ApiResponse<List<ReviewDto>> findAllDesc(Pageable pageable) {
        Page<ReviewDto> reviewDtoPage = reviewRepository.findAllByIsActiveOrderByIdDesc(1, pageable)
            .map(reviewConverter::toDto);
        return getApiResponse(reviewDtoPage);
    }

    public ApiResponse<List<ReviewDto>> findAllByName(String name, Pageable pageable) {
        // Page<Review> 를 Page<ReviewDto>로 변환
        Page<ReviewDto> reviewDtoPage = reviewRepository.findAllByIsActiveAndNameNative(1, name, pageable);

        // Page<ReviewDto> 로 ApiResponse<List<ReviewDto>> 얻어와서 리턴
        return getApiResponse(reviewDtoPage);
    }


    private static ApiResponse<List<ReviewDto>> getApiResponse(Page<ReviewDto> reviewDtoPage) {
        List<ReviewDto> reviewDtoList = reviewDtoPage.toList();

        // Page<ReviewDto> 로 pagination 생성
        Pagination pagination = Pagination.builder()
            .page(reviewDtoPage.getNumber())
            .size(reviewDtoPage.getSize())
            .currentElements(reviewDtoPage.getNumberOfElements())
            .totalElements(reviewDtoPage.getTotalElements())
            .totalPage(reviewDtoPage.getTotalPages())
            .build();

        // ApiResponse<List<ReviewDto>> 만들어서 리턴
        return ApiResponse.<List<ReviewDto>>builder()
            .data(reviewDtoList)
            .pagination(pagination)
            .build();
    }

    public ApiResponse<ReviewDto> findReviewById(Long id) {
        Optional<Review> reviewOptional = reviewRepository.findById(id);

        if (reviewOptional.isPresent()) {
            ReviewDto reviewDto = reviewConverter.toDto(reviewOptional.get());
            return ApiResponse.<ReviewDto>builder()
                .data(reviewDto)
                .status(ApiResponse.SUCCESS_STATUS)
                .message("리뷰 정보 조회 성공")
                .build();
        } else {
            return ApiResponse.<ReviewDto>builder()
                .status(ApiResponse.ERROR_STATUS)
                .message("해당 리뷰를 찾을 수 없습니다.")
                .build();
        }
    }

    public ApiResponse<ReviewDto> createReview(ReviewDto reviewDto) {
        Review review = reviewConverter.toEntity(reviewDto);

        Review savedReview = reviewRepository.save(review);

        ReviewDto savedReviewDto = reviewConverter.toDto(savedReview);
        return ApiResponse.<ReviewDto>builder()
            .data(savedReviewDto)
            .status(ApiResponse.SUCCESS_STATUS)
            .message("리뷰 정보 생성 성공")
            .build();
    }
    public ApiResponse<ReviewDto> updateReview(Long id, ReviewDto reviewdto) {
        Optional<Review> reviewOptional = reviewRepository.findById(id);

        if (reviewOptional.isPresent()) {
            // 엔티티 수정
            Review existingReview = reviewOptional.get();
            existingReview.setTitle(reviewdto.getTitle());
            existingReview.setScore(reviewdto.getScore());
            existingReview.setDescription(reviewdto.getDescription());

            Review updatedReview = reviewRepository.save(existingReview);

            ReviewDto updatedReviewDto = reviewConverter.toDto(updatedReview);
            return ApiResponse.<ReviewDto>builder()
                .data(updatedReviewDto)
                .status(ApiResponse.SUCCESS_STATUS)
                .message("리뷰 정보 수정 성공")
                .build();
        } else {
            return ApiResponse.<ReviewDto>builder()
                .status(ApiResponse.ERROR_STATUS)
                .message("해당 리뷰를 찾을 수 없습니다.")
                .build();
        }
    }

    public ApiResponse<String> deleteReview(Long id) {
        Optional<Review> reviewOptional = reviewRepository.findById(id);

        if (reviewOptional.isPresent()) {
            reviewRepository.delete(reviewOptional.get());

            return ApiResponse.<String>builder()
                .status(ApiResponse.SUCCESS_STATUS)
                .message("리뷰 정보 삭제 성공")
                .data("리뷰 정보가 성공적으로 삭제되었습니다.")
                .build();
        } else {
            return ApiResponse.<String>builder()
                .status(ApiResponse.ERROR_STATUS)
                .message("해당 리뷰를 찾을 수 없습니다.")
                .build();
        }
    }


}
