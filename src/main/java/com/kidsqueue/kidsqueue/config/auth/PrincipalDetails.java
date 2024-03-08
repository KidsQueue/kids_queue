package com.kidsqueue.kidsqueue.config.auth;

import com.kidsqueue.kidsqueue.domain.Parent.Parent;
import java.util.ArrayList;
import java.util.Collection;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
public class PrincipalDetails implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Parent parent;

    public PrincipalDetails(Parent parent) {
        this.parent = parent;

    }

    @Override
    // 권한을 가져오는 함수
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collector = new ArrayList<>();
        collector.add(() -> {
//          return parent.getRole();
            return null;
        });
        return collector;
    }

    @Override
    public String getPassword() {
        return parent.getPassword();
    }

    @Override
    public String getUsername() {
        return parent.getLoginId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
