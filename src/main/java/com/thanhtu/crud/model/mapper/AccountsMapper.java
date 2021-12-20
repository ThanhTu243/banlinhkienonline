package com.thanhtu.crud.model.mapper;

import com.thanhtu.crud.entity.AccountsEntity;
import com.thanhtu.crud.model.dto.AccountsDto;

public class AccountsMapper {
    public static AccountsDto toAccountsDto(AccountsEntity account)
    {
        AccountsDto tmp=new AccountsDto();
        tmp.setId(account.getAccount_id());
        tmp.setUsername(account.getUsername());
        tmp.setGmail(account.getGmail());
        tmp.setProvider(account.getProvider());
        return tmp;
    }
}
