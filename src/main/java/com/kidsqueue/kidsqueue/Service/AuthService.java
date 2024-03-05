package com.kidsqueue.kidsqueue.Service;

import com.kidsqueue.kidsqueue.domain.Parent.Parent;
import com.kidsqueue.kidsqueue.domain.Parent.ParentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final ParentRepository parentRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public Parent signUp(Parent parent) {

        String rawPassword = parent.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        parent.setPassword(encPassword);
        Parent parentEntity = null;
        parentEntity = parentRepository.save(parent);
        return parentEntity;
    }
}
