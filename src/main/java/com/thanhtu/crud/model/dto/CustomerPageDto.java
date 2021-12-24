package com.thanhtu.crud.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class CustomerPageDto {
    private Integer currentPage;
    private Integer totalPage;
    List<CustomerDto> listCustomer;
}
