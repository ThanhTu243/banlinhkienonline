package com.thanhtu.crud.repository;

import com.thanhtu.crud.entity.AccountsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends JpaRepository<AccountsEntity,Integer>{
    AccountsEntity findAccountsEntitiesByUsername(String username);
    AccountsEntity findAccountsEntitiesByActivationCode(String code);
    AccountsEntity findAccountsEntitiesByGmail(String email);
    AccountsEntity findAccountsEntitiesByPasswordresetCode(String code);
}
