package com.thanhtu.crud.service.impl;

import com.thanhtu.crud.entity.*;
import com.thanhtu.crud.exception.DuplicateRecoredException;
import com.thanhtu.crud.exception.ReviewMailException;
import com.thanhtu.crud.model.request.RegistrationRequest;
import com.thanhtu.crud.repository.AccountsRepository;
import com.thanhtu.crud.repository.AdminsRepository;
import com.thanhtu.crud.repository.CustomerRepository;
import com.thanhtu.crud.security.JwtProvider;
import com.thanhtu.crud.security.oauth2.OAuth2UserInfo;
import com.thanhtu.crud.service.AuthenticationService;
import com.thanhtu.crud.service.email.MailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthenticationService_impl implements AuthenticationService {

    private final JwtProvider jwtProvider;
    private final MailSender mailSender;
    @Autowired
    private AccountsRepository accountsRepo;
    @Autowired private CustomerRepository customerRepo;
    @Autowired private AdminsRepository adminsRepo;

    @Value("${hostname}")
    private String hostname;

    @Override
    public Map<String, String> login(String username,String role) {
        AccountsEntity account = accountsRepo.findAccountsEntitiesByUsernameAndRoles(username,role);
        String id="";
        String customerId="";
        String imageLink="";
        if(role.equals("ADMIN"))
        {
            id=account.getAccountId().toString();
        }
        else if(role.equals("CUSTOMER")){
            CustomerEntity customer=customerRepo.findCustomerEntityByUserCustomer(username);
            id=account.getAccountId().toString();
            customerId=customer.getCustomerId().toString();
            imageLink=customer.getImageCustomer();
        }

        String userRole = account.getRoles().toString();
        String token = jwtProvider.createToken(username, userRole);

        Map<String, String> response = new HashMap<>();
        response.put("id",id);
        response.put("customerId",customerId);
        response.put("username", username);
        response.put("token", token);
        response.put("userRole", userRole);
        response.put("imageLink",imageLink);
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
        customerRegister.setFirstnameCustomer(request.getFirstname());
        customerRegister.setLastnameCustomer(request.getLastname());
        customerRegister.setAddress(request.getAddress());
        customerRegister.setGmailCustomer(request.getGmail());
        customerRegister.setPhonenumberCustomer(request.getPhonenumber());
        customerRegister.setIsDelete("YES");
        customerRepo.save(customerRegister);

        String subject = "Mã xác thực đăng kí tài khoản";
        String template = "registration-template";
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("fullname", request.getFirstname()+" "+request.getLastname());
        attributes.put("registrationUrl", "https://shoppt-reactapp.vercel.app"+ "/activate/" + accountRegister.getActivationCode());
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
        if(account.getPasswordresetCode()!=null)
        {
            throw new ReviewMailException("Mã đặt lại mật khẩu đã gửi về email");
        }
        account.setPasswordresetCode(UUID.randomUUID().toString());
        accountsRepo.save(account);
        CustomerEntity customer=customerRepo.findCustomerEntityByUserCustomer(account.getUsername());

        String subject = "Đặt lại mật khẩu";
        String template = "password-reset-template";
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("fullname", customer.getFirstnameCustomer()+" "+customer.getLastnameCustomer());
        attributes.put("resetUrl", "https://shoppt-reactapp.vercel.app"+ "/reset/" + account.getPasswordresetCode());
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

    @Override
    public AccountsEntity registerOauth2User(String provider, OAuth2UserInfo oAuth2UserInfo) {
        AccountsEntity account = new AccountsEntity();
        account.setUsername(oAuth2UserInfo.getEmail());
        account.setGmail(oAuth2UserInfo.getEmail());
        account.setFirstname(oAuth2UserInfo.getFirstName());
        account.setLastname(oAuth2UserInfo.getLastName());
        account.setActiveAccount("ACTIVE");
        account.setRoles("CUSTOMER");
        account.setProvider(AuthProvider.valueOf(provider.toUpperCase()).toString());
        accountsRepo.save(account);

        CustomerEntity customerRegister=new CustomerEntity();
        customerRegister.setUserCustomer(oAuth2UserInfo.getEmail());
        customerRegister.setFirstnameCustomer(oAuth2UserInfo.getFirstName());
        customerRegister.setLastnameCustomer(oAuth2UserInfo.getLastName());
        customerRegister.setGmailCustomer(oAuth2UserInfo.getEmail());
        customerRegister.setIsDelete("NO");
        customerRegister.setImageCustomer(oAuth2UserInfo.getImageUrl());
        customerRepo.save(customerRegister);
        return account;
    }

    @Override
    public AccountsEntity updateOauth2User(AccountsEntity accounts, String provider, OAuth2UserInfo oAuth2UserInfo) {
        return accounts;
    }
}
