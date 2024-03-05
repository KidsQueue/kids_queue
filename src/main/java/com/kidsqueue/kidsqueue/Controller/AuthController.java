package com.kidsqueue.kidsqueue.Controller;

import java.com.kidsqueue.kidsqueue.domain.Parent.Parent;
import com.kidsqueue.kidsqueue.Service.AuthService;
import com.kidsqueue.kidsqueue.dto.auth.SignupDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/signup")
    public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) { // 들어올 때 @Valid 를 걸어서 validation check를 한다는 것

//        if(bindingResult.hasErrors()) {
//            Map<String, String> errorMap = new HashMap<>();
//
//            for(FieldError error: bindingResult.getFieldErrors()) {
//                errorMap.put(error.getField(), error.getDefaultMessage());
//            }
//            throw new CustomValidationException("유효성 검사 실패", errorMap);
//        } else {
            Parent user = signupDto.toEntity();
            Parent parentEntity = authService.회원가입(user);
            return "auth/signin";
//        }
    }
}
