package com.kidsqueue.kidsqueue.parent.model;

import com.kidsqueue.kidsqueue.parent.db.Parent;
import java.util.ArrayList;
import java.util.Collection;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
public class PrincipalDetails implements UserDetails {

    private static final long serialVersionUID = 1L;

    private final Parent parent;

    public PrincipalDetails(Parent parent) {
        this.parent = parent;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {

            @Override
            public String getAuthority() {

                return parent.getRole();
            }
        });

        return collection;
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