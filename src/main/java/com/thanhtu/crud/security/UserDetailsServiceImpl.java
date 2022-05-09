package com.thanhtu.crud.security;

import com.thanhtu.crud.entity.AccountsEntity;
import com.thanhtu.crud.exception.DuplicateRecoredException;
import com.thanhtu.crud.repository.AccountsRepository;
import com.thanhtu.crud.repository.AdminsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountsRepository accountsRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws DuplicateRecoredException {
        AccountsEntity accounts=accountsRepo.findAccountsEntitiesByUsername(username);
        if (accounts == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return UserPrincipal.create(accounts);
    }
}
