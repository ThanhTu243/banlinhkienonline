package com.thanhtu.crud.service.impl;

import com.thanhtu.crud.entity.AccountsEntity;
import com.thanhtu.crud.exception.EnterUserAndPassException;
import com.thanhtu.crud.exception.ReviewMailException;
import com.thanhtu.crud.repository.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CustomAuthenticationCustomerProviderService implements AuthenticationProvider {
    @Autowired
    private AccountsRepository accountsRepo;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authenticationToken=null;
        String username=authentication.getName();
        String passwrod=authentication.getCredentials().toString();
        AccountsEntity account=accountsRepo.findAccountsEntitiesByUsernameAndRoles(username,"CUSTOMER");
        if(account!=null)
        {
            if(username.equals(account.getUsername()) && BCrypt.checkpw(passwrod,account.getPasswords()))
            {
                if(account.getActiveAccount().equals("NOT ACTIVE"))
                {
                    throw new ReviewMailException("Tài khoản chưa kích hoạt. Vui lòng kiểm tra lại email để hoàn tất đăng kí");
                }
                Collection<GrantedAuthority> grantedAuthorities=getGrantedAuthority(account);
                authenticationToken=new UsernamePasswordAuthenticationToken(new User(username,passwrod,grantedAuthorities),passwrod,grantedAuthorities);
            }
            else{
                throw new EnterUserAndPassException("Nhập sai mật khẩu hoặc tên tài khoản");
            }
        }
        else {
            throw new UsernameNotFoundException("Username " +username+" not found");
        }
        return authenticationToken;
    }

    private Collection<GrantedAuthority> getGrantedAuthority(AccountsEntity account) {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if(account.getRoles().equals("CUSTOMER")) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
        }
        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
