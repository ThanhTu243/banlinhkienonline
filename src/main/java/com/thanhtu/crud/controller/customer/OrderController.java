package com.thanhtu.crud.controller.customer;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.thanhtu.crud.entity.OrdersEntity;
import com.thanhtu.crud.model.dto.OrdersDto;
import com.thanhtu.crud.model.mapper.OrdersMapper;
import com.thanhtu.crud.model.request.OrderCreateRequest;
import com.thanhtu.crud.model.request.OrdersStatusRequest;
import com.thanhtu.crud.service.OrdersService;
import com.thanhtu.crud.service.impl.PaypalService_impl;
import com.thanhtu.crud.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
//@PreAuthorize("hasAuthority('CUSTOMER')")
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrdersService ordersService;
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
}
