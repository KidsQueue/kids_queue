package com.kidsqueue.kidsqueue.review.db;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Entity(name = "review")
@AllArgsConstructor
@Builder
@Getter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(columnDefinition = "tinyInt")
    private Integer score;
    private String description;
    private LocalDateTime createdBy;
    private LocalDateTime updatedBy;
    private Boolean isActive;

    @OneToMany(mappedBy = "parent")
    private Long parentId;
    @OneToMany(mappedBy = "hospital")
    private Long hospitalId;
}
