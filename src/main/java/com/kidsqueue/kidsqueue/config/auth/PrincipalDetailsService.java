package com.kidsqueue.kidsqueue.config.auth;

import com.kidsqueue.kidsqueue.domain.Parent.Parent;
import com.kidsqueue.kidsqueue.domain.Parent.ParentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService {

    private final ParentRepository parentRepository;

    // 패스워드는 스프링 시큐리티에서 알아서 체킹하니까 신경 쓸 필요 없다.
    // 따라서 loginId만 있는지 없는지 확인해주면 된다.
    // 리턴이 잘 되면 내부적으로 UserDetails 타입 세션으로 자동으로 만든다.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Parent parentEntity = parentRepository.findByLoginId(username);
        if (parentEntity == null) {
            return null;
        } else {
            return new PrincipalDetails(parentEntity);
        }
    }

}
