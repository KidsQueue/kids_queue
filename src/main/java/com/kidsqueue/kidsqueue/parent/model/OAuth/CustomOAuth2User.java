package com.kidsqueue.kidsqueue.parent.model.OAuth;

import com.kidsqueue.kidsqueue.parent.model.ParentDto;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class CustomOAuth2User implements OAuth2User {

    private final ParentDto parentDto;

    public CustomOAuth2User(ParentDto parentDto) {

        this.parentDto = parentDto;
    }

    @Override
    public Map<String, Object> getAttributes() {

        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {

            @Override
            public String getAuthority() {

                return parentDto.getRole();
            }
        });

        return collection;
    }

    @Override
    public String getName() {

        return parentDto.getName();
    }

    public String getUsername() {

        return parentDto.getLoginId();
    }
}
