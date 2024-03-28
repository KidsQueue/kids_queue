package com.kidsqueue.kidsqueue.parent.service;

import com.kidsqueue.kidsqueue.common.exception.ApiException;
import com.kidsqueue.kidsqueue.parent.db.Parent;
import com.kidsqueue.kidsqueue.parent.db.ParentRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final ParentRepository parentRepository;
    private final JavaMailSender emailSender;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public EmailService(ParentRepository parentRepository, JavaMailSender emailSender,
        BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.parentRepository = parentRepository;
        this.emailSender = emailSender;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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

    public void sendTempPassword(String email, String tempPassword) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("[KidsQueue] 임시 비밀번호 발급");
        message.setText("요청하신 회원님의 임시 비밀번호는 " + tempPassword + "입니다. 비밀번호를 가급적 빠르게 변경해주시길 바랍니다.");

        try {
            emailSender.send(message);
        } catch (Exception e) {
            throw new ApiException("이메일을 보내는 중 오류가 발생했습니다.", HttpStatus.NOT_FOUND);
        }
    }

    public void resetPassword(String loginId, String email) {

        Optional<Parent> parentOptional = Optional.ofNullable(
            parentRepository.findByLoginIdAndEmail(loginId, email));

        if (parentOptional.isPresent()) {
            Parent parent = parentOptional.get();

            if (parent == null) {
                throw new ApiException("일치하는 사용자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
            }

            String tempPassword = generateTempPassword();

            // 사용자의 비밀번호를 임시 비밀번호로 변경
            parent.setPassword(encodePassword(tempPassword));
            parentRepository.save(parent);

            // 이메일로 임시 비밀번호 전송
            sendTempPassword(email, tempPassword);
        }
    }

    private String generateTempPassword() {

        char[] charSet = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C',
            'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z'};

        String str = "";

        // 10자리 임시 비밀번호 생성
        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }

    private String encodePassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }
}

