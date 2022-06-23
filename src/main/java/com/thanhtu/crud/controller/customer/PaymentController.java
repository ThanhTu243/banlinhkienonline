package com.thanhtu.crud.controller.customer;

import com.paypal.api.payments.*;
import com.paypal.base.rest.PayPalRESTException;
import com.thanhtu.crud.entity.CustomerEntity;
import com.thanhtu.crud.entity.ProductEntity;
import com.thanhtu.crud.exception.NotFoundException;
import com.thanhtu.crud.model.PaypalDto;
import com.thanhtu.crud.model.dto.OrderDetailViewDto;
import com.thanhtu.crud.model.dto.OrdersDto;
import com.thanhtu.crud.model.dto.ProductOrderDetailDto;
import com.thanhtu.crud.model.dto.ProductToOrder;
import com.thanhtu.crud.model.request.OrderCreateRequest;
import com.thanhtu.crud.service.CustomerService;
import com.thanhtu.crud.service.OrdersDetailService;
import com.thanhtu.crud.service.OrdersService;
import com.thanhtu.crud.service.impl.PaypalService_impl;
import com.thanhtu.crud.utils.Utils;
import com.thanhtu.crud.utils.VnPayUtils;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
//@PreAuthorize("hasAuthority('CUSTOMER')")
@CrossOrigin(origins = "http://localhost:4006/")
@RequestMapping("/payment/")
public class PaymentController {
    public static final String URL_SUCCESS = "payment/paypal/success";
    public static final String URL_CANCEL = "payment/paypal/cancel";
    @Autowired
    CustomerService customerService;
    @Value("${hostname.paypalreturn}")
    private String hostname;

    private Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private PaypalService_impl paypalService;
    @Autowired
    private OrdersService ordersService;
    @Autowired
    OrdersDetailService ordersDetailService;

    @PostMapping("/paypal")
    public ResponseEntity<?> paybyPaypal(HttpServletRequest request, @Valid @RequestBody OrderCreateRequest orderCreateRequest,
                                 BindingResult bindingResult){
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
        }

        OrdersDto order=ordersService.createOrdersOnline(orderCreateRequest,"PAYPAL");
        String cancelUrl = hostname+"/" + URL_CANCEL;
        String successUrl = hostname+ "/" + URL_SUCCESS +"/"+order.getOrderId();
        OrderDetailViewDto orderDetailViewDto=ordersDetailService.detailOrders(order.getOrderId());
        List<ProductOrderDetailDto> listProductOrders=orderDetailViewDto.getList();
        ItemList itemList = new ItemList();
        List<Item> items = new ArrayList<>();
        double totalCost=0;
        for(ProductOrderDetailDto productOrderDetailDto:listProductOrders)
        {
            Item item = new Item();
            item.setCurrency("USD");
            item.setName(productOrderDetailDto.getNameProduct());
            double price=productOrderDetailDto.getPriceAfterDiscount()/23000;
            item.setPrice(String.format("%.2f",price));
            item.setTax("0");
            item.setQuantity(productOrderDetailDto.getQuantity().toString());
            double priceUSD=price* productOrderDetailDto.getQuantity();
            items.add(item);
            totalCost+=priceUSD;
        }
        itemList.setItems(items);
        try {
            Payment payment = paypalService.createPayment(
                    order.getOrderId(),
                    itemList,
                    totalCost,
                    "USD",
                    "paypal",
                    "sale",
                    "payment description",
                    cancelUrl,
                    successUrl);
            for(Links links : payment.getLinks()){
                if(links.getRel().equals("approval_url")){
                    PaypalDto paypalDto=new PaypalDto();
                    paypalDto.setCustomerId(order.getCustomerFKDto().getCustomerId());
                    paypalDto.setOrderId(order.getOrderId());
                    paypalDto.setLink(links.getHref());
                    return ResponseEntity.ok(paypalDto);
                }
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @SneakyThrows
    @PostMapping("/vnpay")
    public ResponseEntity<?> payByVNPay(HttpServletRequest request,@Valid @RequestBody OrderCreateRequest orderCreateRequest,
                                        BindingResult bindingResult) {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
        }

        OrdersDto order=ordersService.createOrdersOnline(orderCreateRequest,"VNPAY");
        CustomerEntity customer=customerService.getCustomerById(orderCreateRequest.getCustomerId());
//        String vnp_OrderInfo = paymentRequest.getDecription();
//        String orderType = req.getParameter("ordertype");
//        String vnp_TxnRef = PaymentUtils.getRandomNumber(8);
//        String vnp_IpAddr = PaymentUtils.getIpAddress(req);
//        String vnp_TmnCode = PaymentUtils.vnp_TmnCode;

        int amount = orderCreateRequest.getTotal().intValue()*100;
        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", VnPayUtils.vnp_versionVNPay);
        vnp_Params.put("vnp_Command", VnPayUtils.vnp_command);
        vnp_Params.put("vnp_TmnCode", VnPayUtils.vnp_tmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", VnPayUtils.vnp_currCode);
//        String bank_code = orderCreateRequest.getBankCode();
        String bank_code="NCB";
        if (bank_code != null && !bank_code.isEmpty()) {
            vnp_Params.put("vnp_BankCode", bank_code);
        }
        vnp_Params.put("vnp_TxnRef", VnPayUtils.getRandomNumber(8));
        vnp_Params.put("vnp_OrderInfo", "payment");
        vnp_Params.put("vnp_OrderType", VnPayUtils.vnp_orderType);

        String locate = request.getParameter("language");
        if (locate != null && !locate.isEmpty()) {
            vnp_Params.put("vnp_Locale", locate);
        } else {
            vnp_Params.put("vnp_Locale", VnPayUtils.location);
        }
        VnPayUtils.vnp_Returnurl+="/"+order.getOrderId();
        vnp_Params.put("vnp_ReturnUrl", VnPayUtils.vnp_Returnurl);
        vnp_Params.put("vnp_IpAddr", VnPayUtils.getIpAddress(request));
        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());

        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        //Add Params of 2.0.1 Version
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);
        //Billing
        vnp_Params.put("vnp_Bill_Mobile", orderCreateRequest.getPhoneNumber());
        vnp_Params.put("vnp_Bill_Email", customer.getGmailCustomer());

        String fullName=customer.getFirstnameCustomer()+customer.getLastnameCustomer();
        /*vnp_Params.put("vnp_Bill_FirstName",customer.getFirstnameCustomer());
        vnp_Params.put("vnp_Bill_LastName", customer.getLastnameCustomer());*/
        vnp_Params.put("vnp_Bill_FirstName","Nguyen");
        vnp_Params.put("vnp_Bill_LastName", "Thanh");


        vnp_Params.put("vnp_Bill_Address", "HCM");
        vnp_Params.put("vnp_Bill_City", "HCM");
        vnp_Params.put("vnp_Bill_Country", "Viet Nam");
//        if (req.getParameter("txt_bill_state") != null && !req.getParameter("txt_bill_state").isEmpty()) {
//            vnp_Params.put("vnp_Bill_State", req.getParameter("txt_bill_state"));
//        }
        // Invoice
        vnp_Params.put("vnp_Inv_Phone", customer.getPhonenumberCustomer());
        vnp_Params.put("vnp_Inv_Email", customer.getGmailCustomer());
        vnp_Params.put("vnp_Inv_Customer", request.getParameter("txt_inv_customer"));
        vnp_Params.put("vnp_Inv_Address", customer.getAddress());
        vnp_Params.put("vnp_Inv_Company", "Company");
        vnp_Params.put("vnp_Inv_Taxcode", "vnp_Inv_Taxcode");
        vnp_Params.put("vnp_Inv_Type", "vnp_Inv_Type");
        //Build data to hash and querystring
        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        System.out.println("vnp_hash:" + queryUrl);

        String vnp_SecureHash = VnPayUtils.hmacSHA512(VnPayUtils.vnp_HashSecret, hashData.toString());
        System.out.println("vnp_hash:" + vnp_SecureHash);
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = VnPayUtils.vnp_PayUrl + "?" + queryUrl;
        return ResponseEntity.status(HttpStatus.OK).body(paymentUrl);
//        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(paymentUrl)).build();

    }
    @GetMapping("/vnpay/{orderId}")
    public ResponseEntity<?> resultVnPay(@RequestParam("vnp_ResponseCode") String responseCode,@PathVariable int orderId)
    {
        if(responseCode.equals("00"))
        {
            ordersService.confirmPaymentAndSendMail(orderId);
            return ResponseEntity.status(HttpStatus.OK).body("Thanh toán thành công");
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Thanh toán thất bại");
        }
    }
    @GetMapping("/paypal/cancel")
    public ResponseEntity<?> cancelPaypal(){
        throw new NotFoundException("Thanh toán thất bại");
    }
    @GetMapping("/paypal/success/{orderId}")
    public ResponseEntity<?> successPaypal(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId,@PathVariable int orderId){
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if(payment.getState().equals("approved")){
                ordersService.confirmPaymentAndSendMail(orderId);
                return ResponseEntity.status(HttpStatus.OK).body("Thanh toán thành công");
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
        }
        return null;
    }


}
