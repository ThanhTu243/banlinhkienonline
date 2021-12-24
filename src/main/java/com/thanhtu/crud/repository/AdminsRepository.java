package com.thanhtu.crud.repository;

import com.thanhtu.crud.entity.AdminsEntity;
import com.thanhtu.crud.entity.CategoryEntity;
import org.hibernate.type.AdaptedImmutableType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminsRepository extends JpaRepository<AdminsEntity,Integer>{
    AdminsEntity findAdminsEntitiesByUserAdmin(String username);
    List<AdminsEntity> findAdminsEntitiesByIsDelete(String status);
    AdminsEntity findAdminsEntitiesByAdminIdAndIsDelete(Integer id,String status);
    List<AdminsEntity> findAdminsEntitiesByUserAdminOrGmailAdminAndIsDelete(String username,String email,String status);
}
