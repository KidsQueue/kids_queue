package com.kidsqueue.kidsqueue.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.com.kidsqueue.kidsqueue.domain.Parent.Parent;
@Data
public class SignupDto {

    @NotBlank
    private String login_id;
    @NotBlank
    private String password;

    public Parent toEntity() {
        return Parent.builder()
                .login_id(login_id)
                .password(password)
                .build();
    }
}
