package com.thanhtu.crud.service.impl;

import com.thanhtu.crud.entity.AccountsEntity;
import com.thanhtu.crud.entity.CustomerEntity;
import com.thanhtu.crud.exception.EnterUserAndPassException;
import com.thanhtu.crud.exception.NotFoundException;
import com.thanhtu.crud.exception.ReviewMailException;
import com.thanhtu.crud.model.mapper.AccountMapper;
import com.thanhtu.crud.model.mapper.CustomerMapper;
import com.thanhtu.crud.model.request.PasswordAccountRequest;
import com.thanhtu.crud.model.request.ProfileRequest;
import com.thanhtu.crud.repository.AccountsRepository;
import com.thanhtu.crud.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AccountService_impl implements AccountsService {
    @Autowired
    private AccountsRepository accountsRepo;
    @Override
    public AccountsEntity findAccountByGmail(String email) {
        return accountsRepo.findAccountsEntitiesByUsername(email);
    }

    @Override
    public void updateProfile(ProfileRequest profileRequest) {
        AccountsEntity account=accountsRepo.findAccountsEntitiesByAccountId(profileRequest.getId());
        if(account==null)
        {
            throw new NotFoundException("Không tài tại tài khoản với id: "+profileRequest.getCustomerId());
        }
        accountsRepo.save(AccountMapper.toUpdateAccount(account,profileRequest));
    }

    @Override
    public void updatePassword(PasswordAccountRequest passwordRequest) {
        AccountsEntity account=accountsRepo.findAccountsEntitiesByAccountId(passwordRequest.getId());
        if(account!=null)
        {
            if(BCrypt.checkpw(passwordRequest.getCurrentPassword(),account.getPasswords()))
            {
                accountsRepo.save(AccountMapper.toUpdatePasswordAccountEntity(account,passwordRequest));
            }
            else{
                throw new EnterUserAndPassException("Nhập sai mật khẩu hiện tại của tài khoản");
            }
        }
        else {
            throw new NotFoundException("Tài khoản không tồn tại");
        }
    }
}
