package com.thanhtu.crud.service;

import com.thanhtu.crud.entity.AccountsEntity;

public interface AccountsService {
    AccountsEntity findAccountByGmail(String email);
}
