package com.thanhtu.crud.controller.admin;

import com.thanhtu.crud.model.dto.BestSellingProducts;
import com.thanhtu.crud.model.dto.BestSellingProductsPage;
import com.thanhtu.crud.model.request.RequestDate;
import com.thanhtu.crud.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4004/")
@RequestMapping("admin/statistic")
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
    public ResponseEntity<?> bestSellingProducts(@RequestBody RequestDate requestDate,
                                                 @RequestParam(value = "page",required = false) Integer page)
    {
        if(page==null)
        {
            page=0;
        }
        else{
            page=page-1;
        }
        BestSellingProductsPage list=statisticService.bestSellingProducts(requestDate,page);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
}
