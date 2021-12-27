package com.thanhtu.crud.controller.customer;

import com.paypal.api.payments.*;
import com.paypal.base.rest.PayPalRESTException;
import com.thanhtu.crud.exception.NotFoundException;
import com.thanhtu.crud.model.dto.OrdersDto;
import com.thanhtu.crud.model.dto.ProductToOrder;
import com.thanhtu.crud.model.request.OrderCreateRequest;
import com.thanhtu.crud.service.OrdersService;
import com.thanhtu.crud.service.impl.PaypalService_impl;
import com.thanhtu.crud.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/payment/")
public class PaymentController {
    public static final String URL_PAYPAL_SUCCESS = "payment/success";
    public static final String URL_PAYPAL_CANCEL = "payment/cancel";
    private Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private PaypalService_impl paypalService;
    @Autowired
    private OrdersService ordersService;

    @PostMapping("/paypal")
    public ResponseEntity<?> pay(HttpServletRequest request, @Valid @RequestBody OrderCreateRequest orderCreateRequest,
                                 BindingResult bindingResult){
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
        }
        String cancelUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_CANCEL;
        String successUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_SUCCESS;
        OrdersDto order=ordersService.createOrdersPaypal(orderCreateRequest);
        try {
            Payment payment = paypalService.createPayment(
                    (double)order.getTotalAmount()/23000,
                    "USD",
                    "paypal",
                    "sale",
                    "payment description",
                    cancelUrl,
                    successUrl);
            for(Links links : payment.getLinks()){
                if(links.getRel().equals("approval_url")){
                    return ResponseEntity.ok(links.getHref());
                }
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
        }
        return null;
    }
    @GetMapping("/cancel")
    public ResponseEntity<?> cancelPay(){
        throw new NotFoundException("Thanh toán thất bại");
    }
    @GetMapping("/success")
    public ResponseEntity<?> successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId){
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if(payment.getState().equals("approved")){
                return ResponseEntity.status(HttpStatus.OK).body("Thanh toán thành công");
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
