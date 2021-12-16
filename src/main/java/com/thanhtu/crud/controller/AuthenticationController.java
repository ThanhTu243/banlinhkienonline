package com.thanhtu.crud.controller;

import com.thanhtu.crud.exception.EnterUserAndPassException;
import com.thanhtu.crud.model.mapper.AuthenticationMapper;
import com.thanhtu.crud.model.request.auth.admin.AuthenticationAdminRequest;
import com.thanhtu.crud.model.request.auth.admin.AuthenticationAdminResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final AuthenticationMapper authenticationMapper;

    @PostMapping("/admin/login")
    public ResponseEntity<AuthenticationAdminResponse> loginAdmin(@Valid @RequestBody AuthenticationAdminRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserAdmin(), request.getPassword()));
            return ResponseEntity.ok(authenticationMapper.login(request.getUserAdmin()));
        } catch (AuthenticationException e) {
            throw new EnterUserAndPassException("Nhập sai mật khẩu hoặc tên tài khoản");
        }
    }
}
