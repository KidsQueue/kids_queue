package com.kidsqueue.kidsqueue.parent.service;

import com.kidsqueue.kidsqueue.parent.model.OAuth.CustomOAuth2User;
import com.kidsqueue.kidsqueue.parent.model.OAuth.GoogleResponse;
import com.kidsqueue.kidsqueue.parent.model.OAuth.NaverResponse;
import com.kidsqueue.kidsqueue.parent.model.OAuth.OAuth2Response;
import com.kidsqueue.kidsqueue.parent.model.ParentDto;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        System.out.println(oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;
        if (registrationId.equals("naver")) {

            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        } else if (registrationId.equals("google")) {

            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        } else {

            return null;
        }

        String oAuthLoginId = oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();

        ParentDto parentDto = new ParentDto();
        parentDto.setLoginId(oAuthLoginId);
        parentDto.setName(oAuth2Response.getName());
        parentDto.setRole("ROLE_USER");

        return new CustomOAuth2User(parentDto);
    }
}
