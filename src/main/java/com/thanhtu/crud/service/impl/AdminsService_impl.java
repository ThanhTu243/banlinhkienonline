package com.thanhtu.crud.service.impl;

import com.thanhtu.crud.entity.AccountsEntity;
import com.thanhtu.crud.entity.AdminsEntity;
import com.thanhtu.crud.exception.DuplicateRecoredException;
import com.thanhtu.crud.exception.NotFoundException;
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
        List<AdminsEntity> adminsEntityList=adminsRepo.findAdminsEntitiesByIsDelete("NO");
        List<AdminsDto> adminsDtos=new ArrayList<AdminsDto>();
        for(AdminsEntity adminsEntity:adminsEntityList)
        {
            adminsDtos.add(AdminsMapper.toAdminDto(adminsEntity));
        }
        return adminsDtos;
    }

    @Override
    public AdminsDto getAdminById(int id) {
        AdminsEntity adminsEntity=adminsRepo.findAdminsEntitiesByAdminIdAndIsDelete(id,"NO");
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
        AdminsEntity adminsEntity=adminsRepo.save(AdminsMapper.toAdminEntity(adminsRequest));
        accountsRepo.save(AccountMapper.toAdminAccountEntity(adminsRequest));
        return AdminsMapper.toAdminDto(adminsEntity);
    }

    @Override
    public AdminsDto updateAdmin(Integer id, AdminsRequest adminsRequest) {
        AdminsEntity adminsEntity=adminsRepo.findAdminsEntitiesByAdminIdAndIsDelete(id,"NO");
        if(adminsEntity==null)
        {
            throw new NotFoundException("Không tài tại tài khoản với id: "+id);
        }
        AccountsEntity accounts=accountsRepo.findAccountsEntitiesByUsername(adminsEntity.getUserAdmin());
        List<AccountsEntity> list=accountsRepo.findAll();
        if(!adminsEntity.getUserAdmin().equals(adminsRequest.getUserAdmin()))
        {
            for(AccountsEntity account:list)
            {
                if(account.getUsername().equals(adminsRequest.getUserAdmin()))
                {
                    throw new DuplicateRecoredException("Trùng tên tài khoản");
                }
            }
        }
        else if(!adminsEntity.getGmailAdmin().equals(adminsRequest.getGmailAdmin()))
        {
            for(AccountsEntity account:list)
            {
                if(account.getGmail().equals(adminsRequest.getGmailAdmin()))
                {
                    throw new DuplicateRecoredException("Trùng Mail rồi");
                }
            }
        }
        AdminsEntity admins=adminsRepo.save(AdminsMapper.toUpdateAdminEntity(adminsEntity,adminsRequest));
        accountsRepo.save(AccountMapper.toUpdateAccountAdminEntity(accounts,adminsRequest));

        return AdminsMapper.toAdminDto(admins);
    }

    @Override
    public AdminsDto deleteAdmin(Integer id) {
        AdminsEntity adminsEntity=adminsRepo.findAdminsEntitiesByAdminIdAndIsDelete(id,"NO");
        if(adminsEntity==null)
        {
            throw new NotFoundException("Không tài tại tài khoản với id: "+id);
        }
        AccountsEntity account=accountsRepo.findAccountsEntitiesByUsername(adminsEntity.getUserAdmin());
        adminsEntity.setIsDelete("YES");
        account.setActiveAccount("NOT ACTIVE");
        adminsRepo.save(adminsEntity);
        accountsRepo.save(account);
        return null;
    }

    @Override
    public AdminsEntity findAdminsByUsername(String username) {
        return adminsRepo.findAdminsEntitiesByUserAdmin(username);
    }
}
