package com.thanhtu.crud.controller.customer;

import com.thanhtu.crud.entity.CustomerEntity;
import com.thanhtu.crud.exception.InputFieldException;
import com.thanhtu.crud.model.dto.ProfileDto;
import com.thanhtu.crud.model.mapper.CustomerMapper;
import com.thanhtu.crud.model.request.PasswordAccountRequest;
import com.thanhtu.crud.model.request.ProfileRequest;
import com.thanhtu.crud.service.AccountsService;
import com.thanhtu.crud.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
//@PreAuthorize("hasAuthority('CUSTOMER')")
@CrossOrigin(origins = "https://shoppt-reactapp.vercel.app")
@RequestMapping("/account")
public class AccountController {
    @Autowired
    CustomerService customerService;

    @Autowired
    AccountsService accountsService;


    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(@RequestParam("customerId") String customerId,@RequestParam("accountId") String accountId) {
        ProfileDto profileDto=customerService.getCustomerById(Integer.valueOf(customerId),Integer.valueOf(accountId));
        return ResponseEntity.ok(profileDto);
    }

    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(@Valid @RequestBody ProfileRequest profileRequest,BindingResult bindingResult)
    {
        CustomerEntity customer= customerService.updateProfile(profileRequest);
        accountsService.updateProfile(profileRequest);
        return ResponseEntity.ok(CustomerMapper.toCustomerDto(customer));
    }
    @PutMapping("/password")
    public ResponseEntity<?> updatePassword(@Valid @RequestBody PasswordAccountRequest passwordRequest)
    {
        if(passwordRequest.getNewPassword().equals(passwordRequest.getReNewPassword()))
        {
            accountsService.updatePassword(passwordRequest);
            return ResponseEntity.status(HttpStatus.OK).body("Đổi mật khẩu thành công");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mật khẩu và Xác nhận mật khẩu không khớp nhau");

    }
}
