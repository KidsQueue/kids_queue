package com.kidsqueue.kidsqueue.reservation.config;

import com.kidsqueue.kidsqueue.reservation.repository.ReservationRepository;
import com.kidsqueue.kidsqueue.reservation.service.ReservationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReservationConfig {
    private final ReservationRepository reservationRepository;

    public ReservationConfig(ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }

    @Bean
    public ReservationService reservationService(){
        return new ReservationService(reservationRepository);
    }
}
