package com.kidsqueue.kidsqueue.reservation.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kidsqueue.kidsqueue.domain.Parent.Parent;
import com.kidsqueue.kidsqueue.reservation.entity.Child;
import com.kidsqueue.kidsqueue.reservation.entity.Hospital;
import jakarta.validation.constraints.NotBlank;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ReservationDTO {
    @NotBlank
    private Long hospitalId;
    @NotBlank
    private Long childId;
    @NotBlank
    private Timestamp time;
}
