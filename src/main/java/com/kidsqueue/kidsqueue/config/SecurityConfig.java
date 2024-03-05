package com.kidsqueue.kidsqueue.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    BCryptPasswordEncoder encode() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/", "/user/**", "/image/**", "/subscribe/**", "/comment/**", "/api/**").authenticated() // 해당 URL로 가는 요청은 인증이 필요
                .anyRequest().permitAll() // 그외 모든 요청은 허용
                .and()
                .formLogin()
                .loginPage("/auth/signin") // 사용자가 인증이 필요한 URL로 get 요청 시 /auth/signin으로 리다이렉트.
                .loginProcessingUrl("/auth/signin") // POST(로그인)로 요청시 리다이렉트
                .defaultSuccessUrl("/"); // 정상적으로 처리가 되었으면 /로 이동

        return http.build();
    }




}
