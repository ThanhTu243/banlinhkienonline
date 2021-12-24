package com.thanhtu.crud.service.impl;

import com.sun.org.glassfish.external.statistics.Statistic;
import com.thanhtu.crud.entity.OrderDetailEntity;
import com.thanhtu.crud.entity.OrdersEntity;
import com.thanhtu.crud.entity.ProductEntity;
import com.thanhtu.crud.model.dto.BestSellingProducts;
import com.thanhtu.crud.model.dto.ProductDto;
import com.thanhtu.crud.model.request.RequestDate;
import com.thanhtu.crud.repository.OrdersDetailRepository;
import com.thanhtu.crud.repository.OrdersRepository;
import com.thanhtu.crud.repository.ProductRepository;
import com.thanhtu.crud.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class StatisticService_impl implements StatisticService {
    @Autowired
    OrdersDetailRepository ordersDetailRepo;
    @Autowired
    OrdersRepository ordersRepo;
    @Autowired
    ProductRepository productRepo;

    @Override
    public Integer revenueStatistics(RequestDate requestDate) {
        List<OrdersEntity> list=ordersRepo.findOrdersEntityByCreateDateBetweenAndStatusOrder(Timestamp.valueOf(requestDate.getFrom()),Timestamp.valueOf(requestDate.getTo()),"Đã giao");
        int sumRevenue=0;
        for(OrdersEntity ordersEn:list)
        {
            sumRevenue+=ordersEn.getTotalAmount();
        }
        return sumRevenue;
    }

    @Override
    public Map<String, BestSellingProducts> bestSellingProducts(RequestDate requestDate) {
        List<OrdersEntity> list=ordersRepo.findOrdersEntityByCreateDateBetweenAndStatusOrder(Timestamp.valueOf(requestDate.getFrom()),Timestamp.valueOf(requestDate.getTo()),"Đã giao");
        List<OrderDetailEntity> listDetail=new ArrayList<OrderDetailEntity>();
        Map<String,BestSellingProducts> listBestSellingProducts=new HashMap<String,BestSellingProducts>();
        for(OrdersEntity order:list)
        {
            List<OrderDetailEntity> listDetailUnit= ordersDetailRepo.findOrderDetailEntityByOrdersEntity(order);
            for(OrderDetailEntity orderDetail:listDetailUnit)
            {
                listDetail.add(orderDetail);
            }
        }
        for(OrderDetailEntity orderDetail:listDetail)
        {
            String nameProduct=orderDetail.getProductEntity().getProductName();
            if(listBestSellingProducts.containsKey(nameProduct))
            {
                BestSellingProducts productUpdate=listBestSellingProducts.get(nameProduct);
                productUpdate.setQuantity(productUpdate.getQuantity()+orderDetail.getQuantity());
                listBestSellingProducts.put(nameProduct,productUpdate);
            }
            else{
                BestSellingProducts newProduct=new BestSellingProducts();
                newProduct.setProductName(nameProduct);
                newProduct.setQuantity(orderDetail.getQuantity());
                listBestSellingProducts.put(nameProduct,newProduct);
            }
        }
        Set<Map.Entry<String,BestSellingProducts>> entries=listBestSellingProducts.entrySet();
        Comparator<Map.Entry<String, BestSellingProducts>> comparator = new Comparator<Map.Entry<String, BestSellingProducts>>() {
            @Override
            public int compare(Map.Entry<String, BestSellingProducts> o1, Map.Entry<String, BestSellingProducts> o2) {
                int quantity1=o1.getValue().getQuantity();
                int quantity2=o2.getValue().getQuantity();
                return quantity2-quantity1;
            }
        };
        List<Map.Entry<String,BestSellingProducts>> listEntries=new ArrayList<>(entries);
        Collections.sort(listEntries,comparator);
        LinkedHashMap<String, BestSellingProducts> sortedMap = new LinkedHashMap<>(listEntries.size());
        for (Map.Entry<String, BestSellingProducts> entry : listEntries) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
}
