package com.thanhtu.crud.model.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdminsDto {
    private Integer adminId;
    private String userAdmin;
    private String firstnameAdmin;
    private String lastnameAdmin;
    private String gmailAdmin;
}
