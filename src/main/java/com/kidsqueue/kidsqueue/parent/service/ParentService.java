package com.kidsqueue.kidsqueue.parent.service;

import com.kidsqueue.kidsqueue.common.ApiResponse;
import com.kidsqueue.kidsqueue.parent.db.Parent;
import com.kidsqueue.kidsqueue.parent.db.ParentRepository;
import com.kidsqueue.kidsqueue.parent.model.ParentDto;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ParentService {

    private final ParentRepository parentRepository;
    private final ParentConverter parentConverter;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public ApiResponse<ParentDto> findParentById(Long id) {
        Optional<Parent> parentOptional = parentRepository.findById(id);

        if (parentOptional.isPresent()) {
            ParentDto parentDto = parentConverter.toDto(parentOptional.get());
            return ApiResponse.<ParentDto>builder()
                .data(parentDto)
                .status(ApiResponse.SUCCESS_STATUS)
                .message("회원 정보 조회 성공")
                .build();
        } else {
            return ApiResponse.<ParentDto>builder()
                .status(ApiResponse.ERROR_STATUS)
                .message("해당 회원을 찾을 수 없습니다.")
                .build();
        }
    }

    public ApiResponse<ParentDto> updateParent(Long id, ParentDto parentDto) {
        Optional<Parent> parentOptional = parentRepository.findById(id);

        if (parentOptional.isPresent()) {
            // 엔티티 수정
            Parent parentEntity = parentOptional.get();
            parentEntity.setName(parentDto.getName());
            String rawPassword = parentEntity.getPassword();
            String encPassword = bCryptPasswordEncoder.encode(rawPassword);
            parentEntity.setPassword(encPassword);

            // 수정된 엔티티 저장
            Parent updatedParent = parentRepository.save(parentEntity);

            // 엔티티를 DTO로 변환하여 ApiResponse에 담아 반환
            ParentDto updatedParentDto = parentConverter.toDto(updatedParent);
            return ApiResponse.<ParentDto>builder()
                .data(updatedParentDto)
                .status(ApiResponse.SUCCESS_STATUS)
                .message("회원 정보 수정 성공")
                .build();
        } else {
            return ApiResponse.<ParentDto>builder()
                .status(ApiResponse.ERROR_STATUS)
                .message("해당 회원을 찾을 수 없습니다.")
                .build();
        }
    }

}
