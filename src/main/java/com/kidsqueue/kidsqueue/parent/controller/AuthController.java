package com.kidsqueue.kidsqueue.parent.controller;

import com.kidsqueue.kidsqueue.common.Api;
import com.kidsqueue.kidsqueue.parent.db.Parent;
import com.kidsqueue.kidsqueue.parent.model.SignupDto;
import com.kidsqueue.kidsqueue.parent.service.AuthService;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Api<String>> signup(@Valid SignupDto signupDto,
        BindingResult bindingResult) {
        Api<String> response;

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }

            response = Api.<String>builder()
                .status(Api.FAIL_STATUS)
                .message("유효성 검사 실패")
                .data(errorMap.toString())
                .build();

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } else {
            Parent parent = signupDto.toEntity();
            Parent parentEntity = authService.signUp(parent);

            response = Api.<String>builder()
                .status(Api.SUCCESS_STATUS)
                .message("회원가입 성공")
                .data("ID: " + parentEntity.getId())
                .build();

            return ResponseEntity.ok(response);
        }
    }
}