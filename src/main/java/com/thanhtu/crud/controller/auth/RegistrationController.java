package com.thanhtu.crud.controller.auth;

import com.thanhtu.crud.exception.InputFieldException;
import com.thanhtu.crud.exception.NotFoundException;
import com.thanhtu.crud.exception.PasswordException;
import com.thanhtu.crud.model.mapper.AuthenticationMapper;
import com.thanhtu.crud.model.request.RegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4006/")
@RequestMapping("/registration")
public class RegistrationController {
    private final AuthenticationMapper authenticationMapper;
    @PostMapping("")
    public ResponseEntity<?> registration(@Valid @RequestBody RegistrationRequest request, BindingResult bindingResult)
    {
        if(request.getPassword() != null &&!(request.getPassword().equals(request.getRepassword())))
        {
            throw new PasswordException("Mật khẩu không ăn khớp");
        }
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.NOT_ACCEPTABLE);
        }
        authenticationMapper.registerUser(request);
        return ResponseEntity.ok("Vui lòng kiểm tra email để hoàn tất quá trình đăng kí.");
    }
    @GetMapping("/activate/{code}")
    public ResponseEntity<String> activateEmailCode(@PathVariable String code) {
        if (!authenticationMapper.activateUser(code)) {
            throw new NotFoundException("Mã code không hợp lệ");
        } else {
            return ResponseEntity.ok("Tài khoản của bạn đã kích hoạt thành công.");
        }
    }
}
