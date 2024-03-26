package com.kidsqueue.kidsqueue.parent.controller;

import com.kidsqueue.kidsqueue.common.Api;
import com.kidsqueue.kidsqueue.parent.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/findId")
    public ResponseEntity<Api<String>> sendUserIdByEmail(@RequestParam String name,
        @RequestParam String email) {
        emailService.sendUserIdByEmail(name, email);
        return ResponseEntity.ok(Api.ok(null, "아이디를 이메일로 전송했습니다."));
    }
}
