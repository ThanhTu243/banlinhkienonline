package com.thanhtu.crud.model.mapper;

import com.thanhtu.crud.entity.AccountsEntity;
import com.thanhtu.crud.model.dto.AccountsDto;
import com.thanhtu.crud.model.request.AdminsRequest;
import com.thanhtu.crud.model.request.PasswordAccountRequest;
import com.thanhtu.crud.model.request.ProfileRequest;
import com.thanhtu.crud.model.request.ShipperRequest;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class AccountMapper {

    public static AccountsEntity toUpdateAccount(AccountsEntity accounts, ProfileRequest request)
    {
        accounts.setPhonenumber(request.getPhoneNumber());
        accounts.setGmail(request.getEmail());
        accounts.setLastname(request.getLastName());
        accounts.setFirstname(request.getFirstName());
        accounts.setAddress(request.getAddress());
        return accounts;
    }
    public static AccountsDto toAccountsDto(AccountsEntity account)
    {
        AccountsDto tmp=new AccountsDto();
        tmp.setId(account.getAccountId());
        tmp.setUsername(account.getUsername());
        tmp.setGmail(account.getGmail());
        tmp.setProvider(account.getProvider());
        return tmp;
    }
    public static AccountsEntity toAdminAccountEntity(AdminsRequest adminsRequest)
    {
        AccountsEntity tmp=new AccountsEntity();
        tmp.setUsername(adminsRequest.getUserAdmin());
        tmp.setPasswords(BCrypt.hashpw(adminsRequest.getPasswordAdmin(),BCrypt.gensalt(12)));
        tmp.setGmail(adminsRequest.getGmailAdmin());
        tmp.setActiveAccount("ACTIVE");
        tmp.setPasswordresetCode(null);
        tmp.setActivationCode(null);
        tmp.setProvider("LOCAL");
        tmp.setRoles("ADMIN");
        return tmp;
    }
    public static AccountsEntity toUpdateAccountAdminEntity(AccountsEntity accounts,AdminsRequest adminsRequest)
    {
        accounts.setUsername(adminsRequest.getUserAdmin());
        accounts.setPasswords(BCrypt.hashpw(adminsRequest.getPasswordAdmin(),BCrypt.gensalt(12)));
        accounts.setGmail(adminsRequest.getGmailAdmin());
        return accounts;
    }

    public static AccountsEntity toUpdatePasswordAccountEntity(AccountsEntity accounts, PasswordAccountRequest request)
    {
        accounts.setPasswords(BCrypt.hashpw(request.getNewPassword(),BCrypt.gensalt(12)));
        return accounts;
    }
    public static AccountsEntity toShipperAccountEntity(ShipperRequest shipperRequest)
    {
        AccountsEntity tmp=new AccountsEntity();
        tmp.setUsername(shipperRequest.getUserShipper());
        tmp.setPasswords(BCrypt.hashpw(shipperRequest.getPasswordShipper(),BCrypt.gensalt(12)));
        tmp.setGmail(shipperRequest.getGmailShipper());
        tmp.setActiveAccount("ACTIVE");
        tmp.setPasswordresetCode(null);
        tmp.setActivationCode(null);
        tmp.setProvider("LOCAL");
        tmp.setRoles("SHIPPER");
        return tmp;
    }
    public static AccountsEntity toUpdateAccountShipperEntity(AccountsEntity accounts,ShipperRequest shipperRequest)
    {
        accounts.setUsername(shipperRequest.getUserShipper());
        accounts.setPasswords(BCrypt.hashpw(shipperRequest.getPasswordShipper(),BCrypt.gensalt(12)));
        accounts.setGmail(shipperRequest.getGmailShipper());
        return accounts;
    }
}
