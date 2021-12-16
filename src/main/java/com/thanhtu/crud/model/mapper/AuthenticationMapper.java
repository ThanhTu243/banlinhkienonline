package com.thanhtu.crud.model.mapper;

import com.thanhtu.crud.model.request.auth.admin.AuthenticationAdminResponse;
import com.thanhtu.crud.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuthenticationMapper {
    private final AuthenticationService authenticationService;

    public AuthenticationAdminResponse login(String username) {
        Map<String, String> resultMap = authenticationService.login(username);
        AuthenticationAdminResponse response = new AuthenticationAdminResponse();
        response.setUserAdmin(resultMap.get("username"));
        response.setToken(resultMap.get("token"));
        response.setRole(resultMap.get("role"));
        return response;
    }

}
