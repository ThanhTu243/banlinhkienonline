package com.thanhtu.crud.service.impl;

import com.thanhtu.crud.entity.AdminsEntity;
import com.thanhtu.crud.repository.AdminsRepository;
import com.thanhtu.crud.security.JwtProvider;
import com.thanhtu.crud.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService_impl implements AuthenticationService {

    private final JwtProvider jwtProvider;
    private final AdminsRepository adminsRepo;

    @Override
    public Map<String, String> login(String username) {
        AdminsEntity adminsEntity = adminsRepo.findAdminsEntitiesByUserAdmin(username);
        String role = "ADMIN";
        String token = jwtProvider.createToken(username, role);

        Map<String, String> response = new HashMap<>();
        response.put("username", username);
        response.put("token", token);
        response.put("userRole", role);
        return response;
    }
}
