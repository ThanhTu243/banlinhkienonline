package com.thanhtu.crud.service.impl;

import com.thanhtu.crud.entity.OrderDetailEntity;
import com.thanhtu.crud.entity.OrdersEntity;
import com.thanhtu.crud.exception.NotFoundException;
import com.thanhtu.crud.model.dto.BestSellingProductsPage;
import com.thanhtu.crud.model.dto.GeneralStatiscts;
import com.thanhtu.crud.model.dto.ProductDto;
import com.thanhtu.crud.model.mapper.ProductMapper;
import com.thanhtu.crud.model.request.RequestDate;
import com.thanhtu.crud.repository.CustomerRepository;
import com.thanhtu.crud.repository.OrdersDetailRepository;
import com.thanhtu.crud.repository.OrdersRepository;
import com.thanhtu.crud.repository.ProductRepository;
import com.thanhtu.crud.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.util.*;

@Service
public class StatisticService_impl implements StatisticService {
    @Autowired
    OrdersDetailRepository ordersDetailRepo;
    @Autowired
    OrdersRepository ordersRepo;
    @Autowired
    ProductRepository productRepo;
    @Autowired
    CustomerRepository customerRepo;

    @Override
    public Long revenueStatistics(RequestDate requestDate) {
        List<OrdersEntity> list=ordersRepo.findOrdersEntityByCreateDateBetweenAndPaymentStatus(Timestamp.valueOf(requestDate.getFrom()),Timestamp.valueOf(requestDate.getTo()),"Đã thanh toán");
        Long sumRevenue=Long.valueOf(0);
        for(OrdersEntity ordersEn:list)
        {
            sumRevenue+=Long.valueOf(ordersEn.getTotalAmount());
        }
        return sumRevenue;
    }

    @Override
    public BestSellingProductsPage bestSellingProducts(RequestDate requestDate, int page) {
        List<OrdersEntity> list=ordersRepo.findOrdersEntityByCreateDateBetweenAndStatusOrder(Timestamp.valueOf(requestDate.getFrom()),Timestamp.valueOf(requestDate.getTo()),"Đã giao");
        List<OrderDetailEntity> listDetail=new ArrayList<OrderDetailEntity>();
        Map<String,ProductDto> listBestSellingProducts=new HashMap<String,ProductDto>();
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
                ProductDto productUpdate=listBestSellingProducts.get(nameProduct);
                productUpdate.setQuantity(productUpdate.getQuantity()+orderDetail.getQuantity());
                listBestSellingProducts.put(nameProduct,productUpdate);
            }
            else{
                ProductDto newProduct=ProductMapper.toProductDto(orderDetail.getProductEntity());
                newProduct.setQuantity(orderDetail.getQuantity());
                listBestSellingProducts.put(nameProduct,newProduct);
            }
        }
        Set<Map.Entry<String,ProductDto>> entries=listBestSellingProducts.entrySet();
        Comparator<Map.Entry<String, ProductDto>> comparator = new Comparator<Map.Entry<String, ProductDto>>() {
            @Override
            public int compare(Map.Entry<String, ProductDto> o1, Map.Entry<String, ProductDto> o2) {
                int quantity1=o1.getValue().getQuantity();
                int quantity2=o2.getValue().getQuantity();
                return quantity2-quantity1;
            }
        };
        List<Map.Entry<String,ProductDto>> listEntries=new ArrayList<>(entries);
        Collections.sort(listEntries,comparator);
        LinkedHashMap<String, ProductDto> sortedMap = new LinkedHashMap<>(listEntries.size());
//        for (Map.Entry<String, BestSellingProducts> entry : listEntries) {
//            sortedMap.put(entry.getKey(), entry.getValue());
//        }
        int totalPage=0;
        if(entries.size()>10)
        {
            double totalPageDouble=(double)entries.size()/10;
            double totalPageInt=entries.size()/10;
            totalPage=totalPageDouble>totalPageInt?(int)totalPageInt+1:(int)totalPageInt;
        }
        else if(entries.size()==0)
        {
            LinkedHashMap<String,ProductDto> mapEmpty=new LinkedHashMap<>(0);
            BestSellingProductsPage bestSellingProductsPage=new BestSellingProductsPage();
            bestSellingProductsPage.setCurrentPage(0);
            bestSellingProductsPage.setTotalPage(0);
            bestSellingProductsPage.setMap(mapEmpty);
            return bestSellingProductsPage;
        }
        else
        {
            totalPage=1;
        }
        if(totalPage<=page)
        {
            throw new NotFoundException("Không có trang "+(page+1));
        }
        if(page==totalPage-1 && entries.size()%10!=0)
        {
            for(int j=page*10;j<entries.size();j++)
            {
                sortedMap.put(listEntries.get(j).getKey(),listEntries.get(j).getValue());
            }
        }
        else{
            for(int i=page*10;i<page*10+10;i++)
            {
                sortedMap.put(listEntries.get(i).getKey(),listEntries.get(i).getValue());
            }
        }

        BestSellingProductsPage bestSellingProductsPage=new BestSellingProductsPage();
        bestSellingProductsPage.setCurrentPage(page+1);
        bestSellingProductsPage.setTotalPage(totalPage);
        bestSellingProductsPage.setMap(sortedMap);
        return bestSellingProductsPage;
    }

    @Override
    public BestSellingProductsPage top10BestSellingProducts() {
        List<OrdersEntity> list=ordersRepo.findOrdersEntityByPaymentStatus("Đã thanh toán");
        List<OrderDetailEntity> listDetail=new ArrayList<OrderDetailEntity>();
        Map<String, ProductDto> listBestSellingProducts=new HashMap<String,ProductDto>();
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
                ProductDto productUpdate=listBestSellingProducts.get(nameProduct);
                productUpdate.setQuantity(productUpdate.getQuantity()+orderDetail.getQuantity());
                listBestSellingProducts.put(nameProduct,productUpdate);
            }
            else{
                ProductDto newProduct= ProductMapper.toProductDto(orderDetail.getProductEntity());
                newProduct.setQuantity(orderDetail.getQuantity());
                listBestSellingProducts.put(nameProduct,newProduct);
            }
        }
        Set<Map.Entry<String,ProductDto>> entries=listBestSellingProducts.entrySet();
        Comparator<Map.Entry<String, ProductDto>> comparator = new Comparator<Map.Entry<String, ProductDto>>() {
            @Override
            public int compare(Map.Entry<String, ProductDto> o1, Map.Entry<String, ProductDto> o2) {
                int quantity1=o1.getValue().getQuantity();
                int quantity2=o2.getValue().getQuantity();
                return quantity2-quantity1;
            }
        };
        List<Map.Entry<String,ProductDto>> listEntries=new ArrayList<>(entries);
        Collections.sort(listEntries,comparator);
        LinkedHashMap<String, ProductDto> sortedMap = new LinkedHashMap<>(listEntries.size());
//        for (Map.Entry<String, BestSellingProducts> entry : listEntries) {
//            sortedMap.put(entry.getKey(), entry.getValue());
//        }
        int lengthView=0;
        if(listEntries.size()<10)
        {
            lengthView=listEntries.size();
        }
        else{
            lengthView=10;
        }
        for(int i=0;i<lengthView;i++)
        {
            sortedMap.put(listEntries.get(i).getKey(),listEntries.get(i).getValue());
        }

        BestSellingProductsPage bestSellingProductsPage=new BestSellingProductsPage();
        bestSellingProductsPage.setCurrentPage(1);
        bestSellingProductsPage.setTotalPage(1);
        bestSellingProductsPage.setMap(sortedMap);
        return bestSellingProductsPage;
    }

    @Override
    public GeneralStatiscts generalStatistict() {
        GeneralStatiscts generalStatiscts=new GeneralStatiscts();
        generalStatiscts.setNumberOfCustomer(customerRepo.countAllByIsDelete("NO"));
        generalStatiscts.setNewOrders(ordersRepo.countAllByStatusOrder("Chưa duyệt"));
        generalStatiscts.setTotalProduct(productRepo.countAllByIsDelete("NO"));
        LocalDateTime dateTimeNow=LocalDateTime.now();
        String monthString="";
        Month month=dateTimeNow.getMonth();
        if(dateTimeNow.getMonth().getValue()<10)
        {
            monthString="0"+dateTimeNow.getMonthValue();
        }
        int numberOfMonth=month.length(Year.now().isLeap());
        String from=dateTimeNow.getYear()+"-"+monthString+"-01 00:00:00";
        String to=dateTimeNow.getYear()+"-"+monthString+"-"+ numberOfMonth+" 00:00:00";
        List<OrdersEntity> listOrders=ordersRepo.findOrdersEntityByCreateDateBetweenAndPaymentStatus(Timestamp.valueOf(from),Timestamp.valueOf(to),"Đã thanh toán");
        long sum=Long.valueOf(0);
        for(OrdersEntity orders:listOrders)
        {
            sum+=orders.getTotalAmount();
        }
        generalStatiscts.setRevenue(sum);
        return generalStatiscts;
    }
}
