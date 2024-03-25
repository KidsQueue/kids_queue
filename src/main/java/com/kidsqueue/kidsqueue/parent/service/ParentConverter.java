package com.kidsqueue.kidsqueue.parent.service;

import com.kidsqueue.kidsqueue.parent.db.Parent;
import com.kidsqueue.kidsqueue.parent.model.ParentDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


@Service
public class ParentConverter {


    public ParentDto toDto(Parent parent) {

        return ParentDto.builder()
            .id(parent.getId())
            .loginId(parent.getLoginId())
            .password(parent.getPassword())
            .nickname(parent.getNickname())
            .name(parent.getName())
            .age(parent.getAge())
            .gender(parent.getGender())
            .phoneNumber(parent.getPhoneNumber())
            .residentRegistrationNumber(parent.getResidentRegistrationNumber())
            .profileImageUrl(parent.getProfileImageUrl())
            .role(parent.getRole())
            .createdBy(parent.getCreatedBy())
            .updatedBy(parent.getUpdatedBy())
            .build();
    }

    public Parent toEntity(ParentDto parentDto) {
        Parent parent = new Parent();
        BeanUtils.copyProperties(parentDto, parent);
        return parent;
    }
}
