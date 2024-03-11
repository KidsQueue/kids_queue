package com.kidsqueue.kidsqueue.parent.model;

import com.kidsqueue.kidsqueue.parent.db.Parent;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignupDto {

    @NotBlank
    private String loginId;
    @NotBlank
    private String password;
    @NotBlank
    private String name;
    @NotBlank
    private String phoneNumber;

    public Parent toEntity() {
        return Parent.builder()
            .loginId(loginId)
            .password(password)
            .name(name)
            .phoneNumber(phoneNumber)
            .build();
    }
}
