package com.kidsqueue.kidsqueue.parent.service;

import com.kidsqueue.kidsqueue.common.exception.ApiException;
import com.kidsqueue.kidsqueue.parent.db.Parent;
import com.kidsqueue.kidsqueue.parent.db.ParentRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final ParentRepository parentRepository;
    private final JavaMailSender emailSender;

    @Autowired
    public EmailService(ParentRepository parentRepository, JavaMailSender emailSender) {
        this.parentRepository = parentRepository;
        this.emailSender = emailSender;
    }

    public void sendUserIdByEmail(String name, String email) {
        Optional<Parent> parentOptional = Optional.ofNullable(
            parentRepository.findByNameAndEmail(name, email));

        if (parentOptional.isPresent()) {
            Parent parent = parentOptional.get();
            String loginId = parent.getLoginId();

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("[KidsQueue] 아이디 찾기 결과 안내");
            message.setText("요청하신 회원님의 아이디는 " + loginId + "입니다.");

            try {
                emailSender.send(message);
            } catch (Exception e) {
                throw new ApiException("이메일을 보내는 중 오류가 발생했습니다.", HttpStatus.NOT_FOUND);
            }
        } else {
            throw new ApiException("일치하는 사용자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
        }
    }
}

