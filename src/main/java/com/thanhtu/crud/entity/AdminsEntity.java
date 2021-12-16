package com.thanhtu.crud.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "admins")
@Data
public class AdminsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Integer admin_id;
    private String userAdmin;
    private String passwordAdmin;
    private String fullnameAdmin;
    private String gmailAdmin;
}
