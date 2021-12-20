package com.thanhtu.crud.service;

import com.thanhtu.crud.entity.AccountsEntity;
import com.thanhtu.crud.model.dto.AccountsDto;
import com.thanhtu.crud.model.request.RegistrationRequest;

import java.util.Map;

public interface AuthenticationService {
    Map<String, String> login(String username);

    void registerUser(RegistrationRequest request);

    boolean activateUser(String code);

    boolean sendPasswordResetCode(String email);

    AccountsEntity findByPasswordResetCode(String code);

    String passwordReset(String email, String password);
}
