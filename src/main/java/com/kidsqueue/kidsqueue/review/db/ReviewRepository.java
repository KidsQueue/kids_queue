package com.kidsqueue.kidsqueue.review.db;

import com.kidsqueue.kidsqueue.review.model.ReviewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findAllByIsActiveOrderByIdDesc(Integer isActive, Pageable pageable);
    Page<Review> findAllByIsActiveOrderByIdAsc(Integer isActive, Pageable pageable);

    @Query(value = "select * from review where is_active = :isActive and name like concat('%', :name, '%')",
            countQuery = "SELECT COUNT(*) FROM review",
            nativeQuery = true)
    Page<ReviewDto> findAllByIsActiveAndNameNative(@Param("isActive") Integer isActive, @Param("name") String name,  Pageable pageable);
}
