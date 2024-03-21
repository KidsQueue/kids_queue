package com.kidsqueue.kidsqueue.parent.controller;

import com.kidsqueue.kidsqueue.common.ApiResponse;
import com.kidsqueue.kidsqueue.parent.model.ParentDto;
import com.kidsqueue.kidsqueue.parent.service.ParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class ParentController {

    private final ParentService parentService;

    @GetMapping("/info/{user_id}")
    public ResponseEntity<ApiResponse<ParentDto>> getHospitalById(@PathVariable Long id) {
        ApiResponse<ParentDto> apiResponse = parentService.findParentById(id);

        if (apiResponse.getData() != null) {
            apiResponse.setStatus(ApiResponse.SUCCESS_STATUS);
            apiResponse.setMessage("해당 회원 정보 조회 성공");
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } else {
            apiResponse.setStatus(ApiResponse.ERROR_STATUS);
            apiResponse.setMessage("해당 회원을 찾을 수 없습니다.");
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/info/{user_id}")
    public ResponseEntity<ApiResponse<ParentDto>> updateParent(@PathVariable Long id,
        @RequestBody ParentDto parentDto) {
        ApiResponse<ParentDto> apiResponse = parentService.updateParent(id, parentDto);

        if (apiResponse.getData() != null) {
            apiResponse.setStatus(ApiResponse.SUCCESS_STATUS);
            apiResponse.setMessage("회원 정보 수정 성공");
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } else {
            apiResponse.setStatus(ApiResponse.ERROR_STATUS);
            apiResponse.setMessage("회원 정보 수정 실패");
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
