package com.thanhtu.crud.controller.admin;

import com.thanhtu.crud.model.dto.BestSellingProducts;
import com.thanhtu.crud.model.request.RequestDate;
import com.thanhtu.crud.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/statistic")
public class StatisticController {
    @Autowired
    StatisticService statisticService;

    @GetMapping("/revenue")
    public ResponseEntity<?> revenueStatistics(@RequestBody RequestDate requestDate)
    {
        int sumRevenue=statisticService.revenueStatistics(requestDate);
        return ResponseEntity.status(HttpStatus.OK).body(sumRevenue);
    }
    @GetMapping("/bestsellingproducts")
    public ResponseEntity<?> bestSellingProducts(@RequestBody RequestDate requestDate)
    {
        Map<String, BestSellingProducts> list=statisticService.bestSellingProducts(requestDate);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
}
