package com.thanhtu.crud.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "admins")
@Data
public class AdminsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Integer adminId;
    private String userAdmin;
    private String fullnameAdmin;
    private String gmailAdmin;
    private String isDelete;


}
