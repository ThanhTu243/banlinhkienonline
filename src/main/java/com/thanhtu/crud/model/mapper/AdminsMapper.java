package com.thanhtu.crud.model.mapper;

import com.thanhtu.crud.entity.AdminsEntity;
import com.thanhtu.crud.model.dto.AdminsDto;
import com.thanhtu.crud.model.request.AdminsRequest;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class AdminsMapper {
    public static AdminsDto toAdminDto(AdminsEntity adminsEntity)
    {
        AdminsDto tmp=new AdminsDto();
        tmp.setAdminId(adminsEntity.getAdminId());
        tmp.setUserAdmin(adminsEntity.getUserAdmin());
        tmp.setFullnameAdmin(adminsEntity.getFullnameAdmin());
        tmp.setGmailAdmin(adminsEntity.getGmailAdmin());
        return tmp;
    }
    public static AdminsEntity toAdminEntity(AdminsRequest adminsRequest)
    {
        AdminsEntity tmp=new AdminsEntity();
        tmp.setUserAdmin(adminsRequest.getUserAdmin());
        tmp.setFullnameAdmin(adminsRequest.getFullnameAdmin());
        tmp.setGmailAdmin(adminsRequest.getGmailAdmin());
        tmp.setIsDelete("NO");
        return tmp;
    }
    public static AdminsEntity toUpdateAdminEntity(AdminsEntity adminsEntity,AdminsRequest adminsRequest)
    {
        adminsEntity.setUserAdmin(adminsRequest.getUserAdmin());
        adminsEntity.setFullnameAdmin(adminsRequest.getFullnameAdmin());
        adminsEntity.setGmailAdmin(adminsRequest.getGmailAdmin());
        return adminsEntity;
    }
}
