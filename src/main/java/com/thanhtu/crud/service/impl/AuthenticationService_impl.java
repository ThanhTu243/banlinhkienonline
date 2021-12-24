package com.thanhtu.crud.service.impl;

import com.thanhtu.crud.entity.*;
import com.thanhtu.crud.exception.DuplicateRecoredException;
import com.thanhtu.crud.exception.ReviewMailException;
import com.thanhtu.crud.model.request.RegistrationRequest;
import com.thanhtu.crud.repository.AccountsRepository;
import com.thanhtu.crud.repository.CustomerRepository;
import com.thanhtu.crud.security.JwtProvider;
import com.thanhtu.crud.service.AuthenticationService;
import com.thanhtu.crud.service.email.MailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService_impl implements AuthenticationService {

    private final JwtProvider jwtProvider;
    private final MailSender mailSender;
    @Autowired
    private AccountsRepository accountsRepo;
    @Autowired private CustomerRepository customerRepo;

    @Value("${hostname}")
    private String hostname;

    @Override
    public Map<String, String> login(String username) {
        AccountsEntity account = accountsRepo.findAccountsEntitiesByUsername(username);
        String userRole = account.getRoles().toString();
        String token = jwtProvider.createToken(username, userRole);

        Map<String, String> response = new HashMap<>();
        response.put("username", username);
        response.put("token", token);
        response.put("userRole", userRole);
        return response;
    }

    @Override
    public void registerUser(RegistrationRequest request) {
        List<AccountsEntity> list=accountsRepo.findAll();
        for(AccountsEntity accountsEntity:list)
        {
            if(accountsEntity.getUsername().equals(request.getGmail()) || accountsEntity.getGmail().equals(request.getGmail()))
            {
                throw new DuplicateRecoredException("Tài khoản đã tồn tại rồi");
            }
        }
        AccountsEntity accountRegister = new AccountsEntity();
        accountRegister.setUsername(request.getGmail());
        accountRegister.setPasswords(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt(12)));
        accountRegister.setGmail(request.getGmail());
        accountRegister.setActivationCode(UUID.randomUUID().toString());
        accountRegister.setActiveAccount("NOT ACTIVE");
        accountRegister.setProvider("LOCAL");
        accountRegister.setRoles("CUSTOMER");
        accountRegister.setPasswordresetCode(null);
        accountsRepo.save(accountRegister);

        CustomerEntity customerRegister=new CustomerEntity();
        customerRegister.setUserCustomer(request.getGmail());
        customerRegister.setFullnameCustomer(request.getFullname());
        customerRegister.setAddress(request.getAddress());
        customerRegister.setGmailCustomer(request.getGmail());
        customerRegister.setPhonenumberCustomer(request.getPhonenumber());
        customerRegister.setIsDelete("YES");
        customerRepo.save(customerRegister);

        String subject = "Mã xác thực đăng kí tài khoản";
        String template = "registration-template";
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("fullname", request.getFullname());
        attributes.put("registrationUrl", "http://" + hostname + "/activate/" + accountRegister.getActivationCode());
        mailSender.sendMessageHtml(request.getGmail(), subject, template, attributes);
    }

    @Override
    public boolean activateUser(String code)
    {
        AccountsEntity account=accountsRepo.findAccountsEntitiesByActivationCode(code);
        if(account==null)
        {
            return false;
        }
        account.setActivationCode(null);
        account.setActiveAccount("ACTIVE");
        accountsRepo.save(account);
        CustomerEntity customer=customerRepo.findCustomerEntityByUserCustomer(account.getUsername());
        customer.setIsDelete("NO");
        customerRepo.save(customer);
        return true;
    }


    @Override
    public boolean sendPasswordResetCode(String email) {
        AccountsEntity account=accountsRepo.findAccountsEntitiesByGmail(email);
        if (account == null) return false;
        if(!account.getPasswordresetCode().equals(null))
        {
            throw new ReviewMailException("Mã đặt lại mật khẩu đã gửi về email");
        }
        account.setPasswordresetCode(UUID.randomUUID().toString());
        accountsRepo.save(account);
        CustomerEntity customer=customerRepo.findCustomerEntityByUserCustomer(account.getUsername());

        String subject = "Đặt lại mật khẩu";
        String template = "password-reset-template";
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("fullname", customer.getFullnameCustomer());
        attributes.put("resetUrl", "http://" + hostname + "/reset/" + account.getPasswordresetCode());
        mailSender.sendMessageHtml(account.getGmail(), subject, template, attributes);
        return true;
    }

    @Override
    public AccountsEntity findByPasswordResetCode(String code) {
        AccountsEntity account=accountsRepo.findAccountsEntitiesByPasswordresetCode(code);
        return account;
    }

    @Override
    public String passwordReset(String email, String password) {
        AccountsEntity account=accountsRepo.findAccountsEntitiesByGmail(email);
        account.setPasswords(BCrypt.hashpw(password,BCrypt.gensalt(12)));
        account.setPasswordresetCode(null);
        accountsRepo.save(account);
        return "Mật khẩu đã thay đổi thành công";
    }
}
