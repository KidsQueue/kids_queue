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
    @NotBlank
    private String age;
    @NotBlank
    private String gender;
    @NotBlank
    private String residentRegistrationNumber;

    public Parent toEntity() {
        return Parent.builder()
            .loginId(loginId)
            .password(password)
            .name(name)
            .phoneNumber(phoneNumber)
            .age(age)
            .gender(gender)
            .residentRegistrationNumber(residentRegistrationNumber)
            .build();
    }
}
