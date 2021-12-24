package com.thanhtu.crud.service;

import com.thanhtu.crud.model.dto.BestSellingProducts;
import com.thanhtu.crud.model.request.RequestDate;

import java.util.List;
import java.util.Map;

public interface StatisticService {
    Integer revenueStatistics(RequestDate requestDate);

    Map<String, BestSellingProducts> bestSellingProducts(RequestDate requestDate);
}
