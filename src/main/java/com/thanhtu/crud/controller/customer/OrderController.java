package com.thanhtu.crud.controller.customer;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.thanhtu.crud.entity.OrdersEntity;
import com.thanhtu.crud.model.dto.*;
import com.thanhtu.crud.model.mapper.OrdersMapper;
import com.thanhtu.crud.model.request.OrderCreateRequest;
import com.thanhtu.crud.model.request.OrdersStatusRequest;
import com.thanhtu.crud.service.OrdersDetailService;
import com.thanhtu.crud.service.OrdersService;
import com.thanhtu.crud.service.impl.PaypalService_impl;
import com.thanhtu.crud.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
//@PreAuthorize("hasAuthority('CUSTOMER')")
@CrossOrigin(origins = "http://localhost:4006/")
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrdersService ordersService;
    @Autowired
    OrdersDetailService ordersDetailService;
    @Autowired
    PaypalService_impl paypalService;


    @PostMapping("")
    public ResponseEntity<?> createOrders(@Valid @RequestBody OrderCreateRequest orderCreateRequest, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
        }
        OrdersDto ordersDto=ordersService.createOrders(orderCreateRequest);
        return ResponseEntity.ok(ordersDto);
    }
    @PostMapping("/{id}")
    public ResponseEntity<?> getOrderDetailByCustomerIdAndStatus(@Valid @RequestBody OrdersStatusRequest ordersStatusRequest, BindingResult bindingResult,@PathVariable int id)
    {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
        }
        List<OrderDetailView> ordersDto=ordersService.getOrderDetailByCustomerIdAndStatus(id,ordersStatusRequest);
        if(ordersDto.size()==0)
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Trống");
        }
        return ResponseEntity.ok(ordersDto);
    }

    @GetMapping("/statistic/{customerId}")
    public ResponseEntity<?> getStasticOrder(@PathVariable int customerId)
    {

        OrderStatistic orderStatistic=ordersService.getStasticOrder(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(orderStatistic);
    }

    @PostMapping("/delivered/{id}")
    public ResponseEntity<?> getOrderDetailByCustomerToReview(@Valid @RequestBody OrdersStatusRequest ordersStatusRequest,BindingResult bindingResult,@PathVariable int id)
    {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
        }
        List<ProductToReview> listProductToReview=ordersService.getOrderDetailByCustomerToReview(id,ordersStatusRequest.getStatusOrder());
        if(listProductToReview.size()==0)
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Trống");
        }
        return ResponseEntity.ok(listProductToReview);
    }
    @PutMapping("/cancel/{id}")
    public ResponseEntity<?> cancelOrderDetail(@PathVariable("id") Integer id)
    {
        ordersService.cancelOrder(id);
        return ResponseEntity.status(HttpStatus.OK).body("Hủy đơn hàng thành công");
    }
}
