package com.kidsqueue.kidsqueue.parent.service;

import com.kidsqueue.kidsqueue.common.Api;
import com.kidsqueue.kidsqueue.common.exception.ApiException;
import com.kidsqueue.kidsqueue.parent.db.Parent;
import com.kidsqueue.kidsqueue.parent.db.ParentRepository;
import com.kidsqueue.kidsqueue.parent.model.ParentDto;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ParentService {

    private final ParentRepository parentRepository;
    private final ParentConverter parentConverter;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Parent findById(Long id) {

        // id와 일치하는 병원 가져오기
        Optional<Parent> optionalParent = parentRepository.findById(id);

        // 1d와 일치하는 병원 없으면 ApiException 예외 발생 -> ApiExceptionHandler 가 처리 후 응답 해줌
        if (optionalParent.isEmpty()) {
            throw new ApiException("해당 id와 일치하는 병원이 없습니다.", HttpStatus.NOT_FOUND);
        }

        // 일치하는 병우너 있으면 리턴
        return optionalParent.get();
    }

    public Api<ParentDto> findParentById(Long id) {
        Optional<Parent> parentOptional = parentRepository.findById(id);

        if (parentOptional.isPresent()) {
            ParentDto parentDto = parentConverter.toDto(parentOptional.get());
            return Api.<ParentDto>builder()
                .data(parentDto)
                .status(Api.SUCCESS_STATUS)
                .message("회원 정보 조회 성공")
                .build();
        } else {
            return Api.<ParentDto>builder()
                .status(Api.ERROR_STATUS)
                .message("해당 회원을 찾을 수 없습니다.")
                .build();
        }
    }

    public Api<ParentDto> updateParent(Long id, ParentDto parentDto) {
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
            return Api.<ParentDto>builder()
                .data(updatedParentDto)
                .status(Api.SUCCESS_STATUS)
                .message("회원 정보 수정 성공")
                .build();
        } else {
            return Api.<ParentDto>builder()
                .status(Api.ERROR_STATUS)
                .message("해당 회원을 찾을 수 없습니다.")
                .build();
        }
    }

}
