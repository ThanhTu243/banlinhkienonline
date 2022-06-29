package com.thanhtu.crud.service;

import com.thanhtu.crud.entity.AccountsEntity;
import com.thanhtu.crud.model.dto.AccountsDto;
import com.thanhtu.crud.model.request.RegistrationRequest;
import com.thanhtu.crud.security.oauth2.OAuth2UserInfo;

import java.util.Map;

public interface AuthenticationService {
    Map<String, String> login(String username,String role);

    void registerUser(RegistrationRequest request);

    boolean activateUser(String code);

    boolean sendPasswordResetCode(String email);

    AccountsEntity findByPasswordResetCode(String code);

    String passwordReset(String email, String password);
    AccountsEntity registerOauth2User(String provider, OAuth2UserInfo oAuth2UserInfo);

    AccountsEntity updateOauth2User(AccountsEntity accounts, String provider, OAuth2UserInfo oAuth2UserInfo);
}
