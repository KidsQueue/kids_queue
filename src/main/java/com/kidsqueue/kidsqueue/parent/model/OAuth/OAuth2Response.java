package com.kidsqueue.kidsqueue.parent.model.OAuth;

public interface OAuth2Response {

    // OAuth2 제공자 (Ex. naver, google, ...)
    String getProvider();

    //제공자에서 발급해주는 아이디(번호)
    String getProviderId();

    String getEmail();

    String getName();
}