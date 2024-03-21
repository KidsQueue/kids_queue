package com.kidsqueue.kidsqueue.parent.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParentDto {

    private Long id;
    private String loginId;
    private String password;
    private String nickname;
    private String name;
    private String age;
    private String gender;
    private String phoneNumber;
    private String residentRegistrationNumber;
    private String profileImageUrl;
    private String role;

    private LocalDateTime createdBy;
    private LocalDateTime updatedBy;


}
