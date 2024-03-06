package com.kidsqueue.kidsqueue.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    BCryptPasswordEncoder encode() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
        HandlerMappingIntrospector introspector)
        throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(new MvcRequestMatcher(introspector, "/auth/**"))
                .permitAll() // 이 주소는 모두에게 허용
                .anyRequest().authenticated()) // 그 외 나머지 주소는 인증된 사용자에게만 허용
            .formLogin((formLogin) -> formLogin
                .loginPage("/auth/login") // 로그인 페이지 주소
                .defaultSuccessUrl("/index")); // 로그인 시 랜딩 페이지로 리다이렉트
        http
            .logout((logout) -> logout.logoutSuccessUrl("/auth/login") // 로그아웃 시 향하는 주소
                .invalidateHttpSession(true));
        http.csrf((csrf) -> csrf.disable());

        return http.build();
    }
}
