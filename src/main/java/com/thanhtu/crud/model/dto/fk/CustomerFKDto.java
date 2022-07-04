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
    private String firstName;
    private String lastName;
    private String gmailCustomer;
    private String phoneNumberCustomer;
    private String image;
    private String address;
}
