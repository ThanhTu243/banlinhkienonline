package com.thanhtu.crud.service;

import java.util.Map;

public interface AuthenticationService {
    Map<String, String> login(String username);
}
