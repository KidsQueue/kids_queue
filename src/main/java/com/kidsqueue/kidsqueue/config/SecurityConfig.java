package com.kidsqueue.kidsqueue.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    BCryptPasswordEncoder encode() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests((auth) -> auth
                .requestMatchers("/api/index", "/api/auth/**").permitAll()
                .anyRequest().authenticated()
            );

        http
            .formLogin((auth) -> auth
                .loginPage("/api/auth/login")
                .loginProcessingUrl("/api/auth/login")
                .permitAll()
                .defaultSuccessUrl("/api/index")
            );

        http
            .csrf((auth) -> auth
                .disable());

        return http.build();

    }
}
