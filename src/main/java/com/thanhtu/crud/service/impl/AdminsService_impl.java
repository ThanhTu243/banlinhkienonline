package com.thanhtu.crud.service.impl;

import com.thanhtu.crud.entity.AccountsEntity;
import com.thanhtu.crud.exception.DuplicateRecoredException;
import com.thanhtu.crud.exception.NotFoundException;
import com.thanhtu.crud.exception.SelfDestructionExeption;
import com.thanhtu.crud.model.dto.AdminsDto;
import com.thanhtu.crud.model.mapper.AccountMapper;
import com.thanhtu.crud.model.mapper.AdminsMapper;
import com.thanhtu.crud.model.request.AdminsRequest;
import com.thanhtu.crud.repository.AccountsRepository;
import com.thanhtu.crud.repository.AdminsRepository;
import com.thanhtu.crud.service.AdminsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class AdminsService_impl implements AdminsService {
    @Autowired
    AdminsRepository adminsRepo;
    @Autowired
    AccountsRepository accountsRepo;
    @Override
    public List<AdminsDto> getListAdmin() {
        List<AccountsEntity> accountsEntityList=adminsRepo.findAccountsEntitiesByRolesAndActiveAccount("ADMIN","NO");
        List<AdminsDto> adminsDtos=new ArrayList<AdminsDto>();
        for(AccountsEntity adminsEntity:accountsEntityList)
        {
            adminsDtos.add(AdminsMapper.toAdminDto(adminsEntity));
        }
        return adminsDtos;
    }

    @Override
    public AdminsDto getAdminById(int id) {
        AccountsEntity adminsEntity=adminsRepo.findAccountsEntitiesByAccountIdAndRolesAndActiveAccount(id,"ADMIN","NO");
        if(adminsEntity==null)
        {
            throw new NotFoundException("Không tìm thấy quản trị viên có id: "+id);
        }
        return AdminsMapper.toAdminDto(adminsEntity);
    }

    @Override
    public AdminsDto createAdmin(AdminsRequest adminsRequest) {
        List<AccountsEntity> accountsEntityList= accountsRepo.findAll();
        for(AccountsEntity accounts: accountsEntityList)
        {
            if(accounts.getUsername().equals(adminsRequest.getUserAdmin()))
            {
                throw new DuplicateRecoredException("Trùng tên tài khoản");
            }
            else if(accounts.getGmail().equals(adminsRequest.getGmailAdmin()))
            {
                throw new DuplicateRecoredException("Trùng Mail rồi");
            }
        }
        AccountsEntity newAdmin=accountsRepo.save(AccountMapper.toAdminAccountEntity(adminsRequest));
        return AdminsMapper.toAdminDto(newAdmin);
    }

    @Override
    public AdminsDto updateAdmin(Integer id, AdminsRequest adminsRequest) {
        AccountsEntity adminsEntity=adminsRepo.findAccountsEntitiesByAccountIdAndRolesAndActiveAccount(id,"ADMIN","NO");
        if(adminsEntity==null)
        {
            throw new NotFoundException("Không tìm thấy quản trị viên có id: "+id);
        }
        AccountsEntity accounts=accountsRepo.findAccountsEntitiesByUsername(adminsEntity.getUsername());
        List<AccountsEntity> list=accountsRepo.findAll();
        if(!adminsEntity.getUsername().equals(adminsRequest.getUserAdmin()))
        {
            for(AccountsEntity account:list)
            {
                if(account.getUsername().equals(adminsRequest.getUserAdmin()))
                {
                    throw new DuplicateRecoredException("Trùng tên tài khoản");
                }
            }
        }
        else if(!adminsEntity.getGmail().equals(adminsRequest.getGmailAdmin()))
        {
            for(AccountsEntity account:list)
            {
                if(account.getGmail().equals(adminsRequest.getGmailAdmin()))
                {
                    throw new DuplicateRecoredException("Trùng Mail rồi");
                }
            }
        }
        AccountsEntity updateAdmin= accountsRepo.save(AccountMapper.toUpdateAccountAdminEntity(accounts,adminsRequest));

        return AdminsMapper.toAdminDto(updateAdmin);
    }

    @Override
    public AdminsDto deleteAdmin(Integer id) {
        AccountsEntity admin=adminsRepo.findAccountsEntitiesByAccountIdAndRolesAndActiveAccount(id,"ADMIN","NO");
        if(admin==null)
        {
            throw new NotFoundException("Không tìm thấy quản trị viên có id: "+id);
        }
        admin.setActiveAccount("NOT ACTIVE");
        accountsRepo.save(admin);
        return null;
    }

    @Override
    public AccountsEntity findAdminsByUsername(String username) {
        return adminsRepo.findAccountsEntitiesByUsernameAndRoles(username,"ADMIN");
    }
}
