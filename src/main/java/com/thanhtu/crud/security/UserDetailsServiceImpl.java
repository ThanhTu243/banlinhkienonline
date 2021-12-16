package com.thanhtu.crud.security;

import com.thanhtu.crud.entity.AdminsEntity;
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

    private final AdminsRepository adminsRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AdminsEntity adminsEntity = adminsRepo.findAdminsEntitiesByUserAdmin(username);

        if (adminsEntity == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return UserPrincipal.create(adminsEntity);
    }
}
