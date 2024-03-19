package com.kidsqueue.kidsqueue.review.controller;

import com.kidsqueue.kidsqueue.common.ApiResponse;
import com.kidsqueue.kidsqueue.review.db.Review;
import com.kidsqueue.kidsqueue.review.model.ReviewDto;
import com.kidsqueue.kidsqueue.review.service.ReviewService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    public ApiResponse<List<ReviewDto>> get(
        @PageableDefault(page = 0, size = 10) Pageable pageable,
        @RequestParam(required = false) String sortCondition,
        @RequestParam(required = false) String name
    ) {
        System.out.println("sortCondition = " + sortCondition);
        System.out.println("name = " + name);

        ApiResponse<List<ReviewDto>> apiResponse;

        if ("desc".equals(sortCondition)) {
            apiResponse = reviewService.findAllDesc(pageable);
            apiResponse.setMessage("등록일 기준 내림차순으로 리뷰 리스트 가져옴");
        } else if ("name".equals(sortCondition)) {
            apiResponse = reviewService.findAllByName(name, pageable);
            apiResponse.setMessage("리뷰명에 검색조건을 포함하는 리뷰 리스트 정확도 순으로");
        } else if ("asc".equals(sortCondition)) {
            apiResponse = reviewService.findAllAsc(pageable);
            apiResponse.setMessage("등록일 기준 오름차순으로 리뷰 리스트 가져옴");
        } else {
            apiResponse = reviewService.findAllAsc(pageable);
            apiResponse.setMessage("등록일 기준 오름차순으로 리뷰 리스트 가져옴");
        }

        apiResponse.setStatus(ApiResponse.SUCCESS_STATUS);
        return apiResponse;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ReviewDto>> getReviewById(@PathVariable Long id) {
        ApiResponse<ReviewDto> apiResponse = reviewService.findReviewById(id);

        if (apiResponse.getData() != null) {
            apiResponse.setStatus(ApiResponse.SUCCESS_STATUS);
            apiResponse.setMessage("해당 리뷰 정보 조회 성공");
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } else {
            apiResponse.setStatus(ApiResponse.ERROR_STATUS);
            apiResponse.setMessage("해당 리뷰를 찾을 수 없습니다.");
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ReviewDto>> createReview(@RequestBody ReviewDto reviewDto) {
        ApiResponse<ReviewDto> apiResponse = reviewService.createReview(reviewDto);

        if (apiResponse.getData() != null) {
            apiResponse.setStatus(ApiResponse.SUCCESS_STATUS);
            apiResponse.setMessage("리뷰 정보 생성 성공");
            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
        } else {
            apiResponse.setStatus(ApiResponse.ERROR_STATUS);
            apiResponse.setMessage("리뷰 정보 생성 실패");
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<ReviewDto>> updateReview(@PathVariable Long id,
        @RequestBody ReviewDto reviewDto) {
        ApiResponse<ReviewDto> apiResponse = reviewService.updateReview(id, reviewDto);

        if (apiResponse.getData() != null) {
            apiResponse.setStatus(ApiResponse.SUCCESS_STATUS);
            apiResponse.setMessage("리뷰 정보 수정 성공");
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } else {
            apiResponse.setStatus(ApiResponse.ERROR_STATUS);
            apiResponse.setMessage("리뷰 정보 수정 실패");
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteReview(@PathVariable Long id) {
        ApiResponse<String> apiResponse = reviewService.deleteReview(id);

        if (apiResponse.getData() == null) {
            apiResponse.setStatus(ApiResponse.SUCCESS_STATUS);
            apiResponse.setMessage("리뷰 정보 삭제 성공");
            return new ResponseEntity<>(apiResponse, HttpStatus.NO_CONTENT);
        } else {
            apiResponse.setStatus(ApiResponse.ERROR_STATUS);
            apiResponse.setMessage("리뷰 정보 삭제 실패");
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
