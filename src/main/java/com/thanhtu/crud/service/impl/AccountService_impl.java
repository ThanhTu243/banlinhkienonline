package com.thanhtu.crud.service.impl;

import com.thanhtu.crud.entity.AccountsEntity;
import com.thanhtu.crud.repository.AccountsRepository;
import com.thanhtu.crud.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService_impl implements AccountsService {
    @Autowired
    private AccountsRepository accountsRepo;
    @Override
    public AccountsEntity findAccountByGmail(String email) {
        return accountsRepo.findAccountsEntitiesByUsername(email);
    }
}
