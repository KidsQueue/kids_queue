package com.kidsqueue.kidsqueue.Controller;


import com.kidsqueue.kidsqueue.Service.AuthService;
import com.kidsqueue.kidsqueue.domain.Parent.Parent;
import com.kidsqueue.kidsqueue.dto.auth.SignupDto;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/signup")
    public String signup() {
        return null;
    }

    @GetMapping("/login")
    public String login() {
        return null;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid SignupDto signupDto,
        BindingResult bindingResult) { // @Valid 를 통해 유효성 검사를 시행하고, 그 결과를 bindingResult에 담아 성공 여부 확인
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("유효성 검사 실패: " + errorMap.toString());
        } else {
            Parent parent = signupDto.toEntity();
            Parent parentEntity = authService.signUp(parent);

            return ResponseEntity.ok("회원가입 성공");
        }
    }
}
