package com.thanhtu.crud.service.impl;

import com.thanhtu.crud.entity.AdminsEntity;
import com.thanhtu.crud.exception.DuplicateRecoredException;
import com.thanhtu.crud.exception.NotFoundException;
import com.thanhtu.crud.model.dto.AdminsDto;
import com.thanhtu.crud.model.mapper.AdminsMapper;
import com.thanhtu.crud.model.request.AdminsRequest;
import com.thanhtu.crud.repository.AdminsRepository;
import com.thanhtu.crud.service.AdminsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AdminsService_imp implements AdminsService {
    @Autowired
    AdminsRepository adminsRepo;
    @Override
    public List<AdminsDto> getListAdmin() {
        List<AdminsEntity> adminsEntityList=adminsRepo.findAll();
        List<AdminsDto> adminsDtos=new ArrayList<AdminsDto>();
        for(AdminsEntity adminsEntity:adminsEntityList)
        {
            adminsDtos.add(AdminsMapper.toAdminDto(adminsEntity));
        }
        return adminsDtos;
    }

    @Override
    public AdminsDto getAdminById(int id) {
        AdminsEntity adminsEntity=adminsRepo.findById(id).orElseThrow(()-> new NotFoundException("không tồn tại tài khoản quản trị viên với id: "+id));
        return AdminsMapper.toAdminDto(adminsEntity);
    }

    @Override
    public AdminsDto createAdmin(AdminsRequest adminsRequest) {
        List<AdminsEntity> list=adminsRepo.findAll();
        for(AdminsEntity adminsEntity:list)
        {
            if(adminsEntity.getUserAdmin().equals(adminsRequest.getUserAdmin()))
            {
                throw new DuplicateRecoredException("Trùng tên tài khoản");
            }
            else if(adminsEntity.getGmailAdmin().equals(adminsRequest.getGmailAdmin()))
            {
                throw new DuplicateRecoredException("Trùng Mail rồi");
            }
        }
        AdminsEntity adminsEntity=adminsRepo.save(AdminsMapper.toAdminEntity(adminsRequest));
        return AdminsMapper.toAdminDto(adminsEntity);
    }

    @Override
    public AdminsDto updateAdmin(Integer id, AdminsRequest adminsRequest) {
        AdminsEntity adminsEntity=adminsRepo.findById(id).orElseThrow(()-> new NotFoundException("Không tồn tại tài khoản quản trị viên với id: "+id));
        List<AdminsEntity> list=adminsRepo.findAll();
        if(!adminsEntity.getUserAdmin().equals(adminsRequest.getUserAdmin()))
        {
            for(AdminsEntity admin:list)
            {
                if(admin.getUserAdmin().equals(adminsRequest.getUserAdmin()))
                {
                    throw new DuplicateRecoredException("Trùng tên tài khoản");
                }
            }
        }
        else if(!adminsEntity.getGmailAdmin().equals(adminsRequest.getGmailAdmin()))
        {
            for(AdminsEntity admin:list)
            {
                if(admin.getGmailAdmin().equals(adminsRequest.getGmailAdmin()))
                {
                    throw new DuplicateRecoredException("Trùng Mail rồi");
                }
            }
        }

        AdminsEntity admins=adminsRepo.save(AdminsMapper.toUpdateAdminEntity(adminsEntity,adminsRequest));
        return AdminsMapper.toAdminDto(admins);
    }

    @Override
    public AdminsDto deleteAdmin(Integer id) {
        AdminsEntity admins=adminsRepo.findById(id).orElseThrow(()-> new NotFoundException("Không tồn tại tài khoản quản trị viên với id: "+id));
        adminsRepo.deleteById(id);
        return null;
    }

    @Override
    public AdminsEntity findAdminsByUsername(String username) {
        return adminsRepo.findAdminsEntitiesByUserAdmin(username);
    }

}
