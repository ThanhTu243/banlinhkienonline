package com.thanhtu.crud.model.dto.fk;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerFKDto {
    private Integer customerId;
    private String userCustomer;
    private String passwordCustomer;
    private String fullnameCustomer;
    private String gmailCustomer;
    private String phoneNumberCustomer;
}
