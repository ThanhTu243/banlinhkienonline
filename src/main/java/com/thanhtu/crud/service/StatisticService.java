package com.thanhtu.crud.service;

import com.thanhtu.crud.model.dto.BestSellingProducts;
import com.thanhtu.crud.model.dto.BestSellingProductsPage;
import com.thanhtu.crud.model.dto.GeneralStatiscts;
import com.thanhtu.crud.model.request.RequestDate;

import java.util.List;
import java.util.Map;

public interface StatisticService {
    Long revenueStatistics(RequestDate requestDate);

    BestSellingProductsPage bestSellingProducts(RequestDate requestDate, int page);

    BestSellingProductsPage top10BestSellingProducts();

    GeneralStatiscts generalStatistict();
}
