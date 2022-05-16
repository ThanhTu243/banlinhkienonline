package com.thanhtu.crud.repository;

import com.thanhtu.crud.entity.AccountsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminsRepository extends JpaRepository<AccountsEntity,Integer> {
    AccountsEntity findAccountsEntitiesByUsernameAndRoles(String username,String roles);
    List<AccountsEntity> findAccountsEntitiesByRolesAndActiveAccount(String roles,String status);
    AccountsEntity findAccountsEntitiesByAccountIdAndRolesAndActiveAccount (Integer id,String roles,String status);
    List<AccountsEntity> findAccountsEntitiesByUsernameOrGmailAndRolesAndActiveAccount(String username,String emai,String roles,String status);
}
