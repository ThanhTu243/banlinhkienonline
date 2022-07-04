package com.thanhtu.crud.controller.customer;

import com.thanhtu.crud.entity.AccountsEntity;
import com.thanhtu.crud.exception.EnterUserAndPassException;
import com.thanhtu.crud.exception.NotFoundException;
import com.thanhtu.crud.exception.PasswordException;
import com.thanhtu.crud.model.mapper.AuthenticationMapper;
import com.thanhtu.crud.model.request.PasswordResetRequest;
import com.thanhtu.crud.model.request.auth.AuthenticationRequest;
import com.thanhtu.crud.security.UserPrincipal;
import com.thanhtu.crud.service.impl.CustomAuthenticationAdminProviderService;
import com.thanhtu.crud.service.impl.CustomAuthenticationCustomerProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4006/")
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    @Autowired private CustomAuthenticationCustomerProviderService customAuthenticationCustomerProviderService;
    @Autowired private AuthenticationManager authenticationManager;
    private final AuthenticationMapper authenticationMapper;

    @GetMapping("/oauth/google")
    public ResponseEntity<?> loginGoogle(@RequestParam("id") String id,@RequestParam("customerId") String customerId,@RequestParam("provider") String provider) {
        if(provider.equals("LOCAL"))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tài khoản đã tồn tại");
        return ResponseEntity.ok("Đăng nhập thành công");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthenticationRequest request, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
        }
        try {
            customAuthenticationCustomerProviderService.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            return ResponseEntity.ok(authenticationMapper.login(request.getUsername(),"CUSTOMER"));
        } catch (AuthenticationException e) {
            throw new EnterUserAndPassException("Nhập sai mật khẩu hoặc tên tài khoản");
        }
    }
    @PostMapping("/forgot")
    public ResponseEntity<String> forgotPassword(@RequestBody PasswordResetRequest passwordReset) {
        boolean forgotPassword = authenticationMapper.sendPasswordResetCode(passwordReset.getEmail());
        if (!forgotPassword) {
            throw new NotFoundException("Không tìm được Email");
        }
        return ResponseEntity.ok("Mã đặt lại mật mật khẩu đã được gửi về Email của bạn");
    }

    @GetMapping("/reset/{code}")
    public ResponseEntity<?> getPasswordResetCode(@PathVariable String code) {
        AccountsEntity accounts = authenticationMapper.findByPasswordResetCode(code);
        if (accounts == null) {
            throw new NotFoundException("Mã đặt lại mật khẩu không hợp lệ");
        }
        return ResponseEntity.ok("Mã đặt lại ăn khớp");
    }

    @PostMapping("/reset")
    public ResponseEntity<?> passwordReset(@Valid @RequestBody PasswordResetRequest passwordReset,BindingResult bindingResult) {
        if(passwordReset.getPassword() != null &&!(passwordReset.getPassword().equals(passwordReset.getRepassword())))
        {
            throw new PasswordException("Mật khẩu không ăn khớp");
        }
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(authenticationMapper.passwordReset(passwordReset.getEmail(), passwordReset.getPassword()));
    }

    @PutMapping("/edit/password")
    public ResponseEntity<?> updateUserPassword(@AuthenticationPrincipal UserPrincipal user,
                                                     @Valid @RequestBody PasswordResetRequest passwordReset,
                                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.NOT_ACCEPTABLE);
        }
        if(passwordReset.getPassword() != null &&!(passwordReset.getPassword().equals(passwordReset.getRepassword())))
        {
            throw new PasswordException("Mật khẩu không ăn khớp");
        }
        return ResponseEntity.ok(authenticationMapper.passwordReset(user.getEmail(), passwordReset.getPassword()));
    }


}
