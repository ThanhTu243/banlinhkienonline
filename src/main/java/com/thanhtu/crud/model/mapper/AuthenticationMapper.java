package com.thanhtu.crud.model.mapper;

import com.thanhtu.crud.entity.AccountsEntity;
import com.thanhtu.crud.model.dto.AccountsDto;
import com.thanhtu.crud.model.request.RegistrationRequest;
import com.thanhtu.crud.model.request.auth.AuthenticationResponse;
import com.thanhtu.crud.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuthenticationMapper {
    private final AuthenticationService authenticationService;

    public AuthenticationResponse login(String username,String role) {
        Map<String, String> resultMap = authenticationService.login(username,role);
        AuthenticationResponse response = new AuthenticationResponse();
        if(role=="CUSTOMER")
        {
            response.setId(Integer.parseInt(resultMap.get("id")));
            response.setUsername(resultMap.get("username"));
            response.setToken(resultMap.get("token"));
            response.setCustomerId(Integer.parseInt(resultMap.get("customerId")));
            response.setUserRole(resultMap.get("userRole"));
        }
        else {
            response.setId(Integer.parseInt(resultMap.get("id")));
            response.setUsername(resultMap.get("username"));
            response.setToken(resultMap.get("token"));
            response.setUserRole(resultMap.get("userRole"));
        }
        return response;
    }


    public void registerUser(RegistrationRequest request) {
        authenticationService.registerUser(request);
    }

    public boolean activateUser(String code) {
        return authenticationService.activateUser(code);
    }

    public boolean sendPasswordResetCode(String email) {
        return authenticationService.sendPasswordResetCode(email);
    }

    public AccountsEntity findByPasswordResetCode(String code) {
        return authenticationService.findByPasswordResetCode(code);
    }

    public String passwordReset(String email, String password) {
        return authenticationService.passwordReset(email, password);
    }
}
