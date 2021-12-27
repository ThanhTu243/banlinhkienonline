package com.thanhtu.crud.model.dto;

import lombok.Data;

import java.util.Map;

@Data
public class BestSellingProductsPage {
    private Integer currentPage;
    private Integer totalPage;
    Map<String,BestSellingProducts> map;
}
