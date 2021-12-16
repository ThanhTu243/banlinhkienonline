package com.thanhtu.crud.model.request;

import com.thanhtu.crud.entity.CustomerEntity;
import com.thanhtu.crud.model.dto.fk.CustomerFKDto;
import lombok.Data;

@Data
public class OrdersUpdateRequest {
    private String address;
    private String phoneNumber;
}
