package com.thanhtu.crud.controller.customer;

import com.thanhtu.crud.model.dto.CartByCustomerDto;
import com.thanhtu.crud.model.dto.DeliveryAddressDto;
import com.thanhtu.crud.model.request.CartRequest;
import com.thanhtu.crud.model.request.CartSelectRequest;
import com.thanhtu.crud.model.request.DeliveryAddressRequest;
import com.thanhtu.crud.service.DeliveryAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
//@PreAuthorize("hasAuthority('CUSTOMER')")
//@CrossOrigin(origins = "https://shoppt-reactapp.vercel.app/")
@RequestMapping("/address")
public class DeliveryAddressController {
    @Autowired
    DeliveryAddressService deliveryAddressService;

    @GetMapping("/{customerId}")
    public ResponseEntity<?> getDeliveryAddressByCustomer(@PathVariable int customerId)
    {
        List<DeliveryAddressDto> deliveryAddressDtoList=deliveryAddressService.getDeliveryAddressByCustomer(customerId);
        if(deliveryAddressDtoList.size()==0)
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Danh sách địa chỉ giao hàng trống");
        }
        return ResponseEntity.status(HttpStatus.OK).body(deliveryAddressDtoList);
    }

    @PostMapping("")
    public ResponseEntity<?> createDeliveryAddress(@Valid @RequestBody DeliveryAddressRequest deliveryAddressRequest, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
        }
        deliveryAddressService.createDeliveryAddress(deliveryAddressRequest);
        List<DeliveryAddressDto> deliveryAddressDtoList=deliveryAddressService.getDeliveryAddressByCustomer(deliveryAddressRequest.getCustomerId());
        if(deliveryAddressDtoList.size()==0)
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Danh sách địa chỉ giao hàng trống");
        }
        return ResponseEntity.status(HttpStatus.OK).body(deliveryAddressDtoList);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDeliveryAddress(@PathVariable("id") String id,@Valid @RequestBody DeliveryAddressRequest deliveryAddressRequest, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
        }
        deliveryAddressService.updateDeliveryAddress(deliveryAddressRequest,Integer.valueOf(id));
        List<DeliveryAddressDto> deliveryAddressDtoList=deliveryAddressService.getDeliveryAddressByCustomer(deliveryAddressRequest.getCustomerId());
        if(deliveryAddressDtoList.size()==0)
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Danh sách địa chỉ giao hàng trống");
        }
        return ResponseEntity.status(HttpStatus.OK).body(deliveryAddressDtoList);
    }
    @PutMapping("/delete/{customerId}")
    public ResponseEntity<?> deleteDeliveryAddressById(@RequestParam("deliveryId") Integer deliveryId,@PathVariable String customerId)
    {
        deliveryAddressService.deleteDeliveryAddressById(Integer.valueOf(customerId),deliveryId);
        List<DeliveryAddressDto> deliveryAddressDtoList=deliveryAddressService.getDeliveryAddressByCustomer(Integer.valueOf(customerId));
        if(deliveryAddressDtoList.size()==0)
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Danh sách địa chỉ giao hàng trống");
        }
        return ResponseEntity.status(HttpStatus.OK).body(deliveryAddressDtoList);
    }
}
