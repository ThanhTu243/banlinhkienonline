package com.thanhtu.crud.service;

import com.thanhtu.crud.entity.AdminsEntity;
import com.thanhtu.crud.model.dto.AdminsDto;
import com.thanhtu.crud.model.request.AdminsRequest;

import java.util.List;

public interface AdminsService {
    List<AdminsDto> getListAdmin();

    AdminsDto getAdminById(int id);

    AdminsDto createAdmin(AdminsRequest adminsRequest);

    AdminsDto updateAdmin(Integer id, AdminsRequest adminsRequest);

    AdminsDto deleteAdmin(Integer id);

    AdminsEntity findAdminsByUsername(String username);
}
