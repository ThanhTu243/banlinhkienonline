package com.thanhtu.crud.service;

import com.thanhtu.crud.entity.AccountsEntity;
import com.thanhtu.crud.model.request.PasswordAccountRequest;
import com.thanhtu.crud.model.request.ProfileRequest;

public interface AccountsService {
    AccountsEntity findAccountByGmail(String email);

    void updateProfile(ProfileRequest profileRequest);

    void updatePassword(PasswordAccountRequest passwordRequest);
}
