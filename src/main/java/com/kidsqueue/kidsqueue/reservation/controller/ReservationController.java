package com.kidsqueue.kidsqueue.reservation.controller;


import com.kidsqueue.kidsqueue.reservation.entity.Reservation;
import com.kidsqueue.kidsqueue.reservation.model.ApiResponse;
import com.kidsqueue.kidsqueue.reservation.model.ReservationDTO;
import com.kidsqueue.kidsqueue.reservation.service.ReservationService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/user/{userId}/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("")
    public ApiResponse<String> createReservation(
        @PathVariable Long userId,
        @Valid @RequestBody ReservationDTO reservationDTO
    ) {
        /*
          userId, jwt  인증
        */
        Reservation reservation = reservationService.saveReservation(userId, reservationDTO);
        ApiResponse<String> response = ApiResponse.<String>builder()
            .resultCode(String.valueOf(HttpStatus.CREATED.value()))
            .resulMessage(HttpStatus.CREATED.getReasonPhrase())
            .data("예약성공")
            .build();
        return response;
    }
    @GetMapping("")
    public ApiResponse<List<Reservation>> getReservationListOfParentId(@PathVariable Long userId){
        List<Reservation> reservationList = reservationService.findReservationListOfParentId(userId);
        ApiResponse<List<Reservation>> response = ApiResponse.<List<Reservation>>builder()
            .resultCode(String.valueOf(HttpStatus.OK.value()))
            .resulMessage(HttpStatus.OK.getReasonPhrase())
            .data(reservationList)
            .build();
        return response;
    }



}
