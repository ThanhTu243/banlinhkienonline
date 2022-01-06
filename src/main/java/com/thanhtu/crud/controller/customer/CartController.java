package com.thanhtu.crud.controller.customer;

import com.thanhtu.crud.entity.CustomerEntity;
import com.thanhtu.crud.exception.NotFoundException;
import com.thanhtu.crud.model.dto.CartByCustomerDto;
import com.thanhtu.crud.model.dto.CartDto;
import com.thanhtu.crud.model.dto.CategoryDto;
import com.thanhtu.crud.model.mapper.CartMapper;
import com.thanhtu.crud.model.mapper.CustomerMapper;
import com.thanhtu.crud.model.request.CartRequest;
import com.thanhtu.crud.model.request.CartSelectRequest;
import com.thanhtu.crud.model.request.CategoryRequest;
import com.thanhtu.crud.security.UserPrincipal;
import com.thanhtu.crud.service.CartService;
import com.thanhtu.crud.service.CategoryService;
import com.thanhtu.crud.service.CustomerService;
import org.hibernate.id.BulkInsertionCapableIdentifierGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
//@PreAuthorize("hasAuthority('CUSTOMER')")
@CrossOrigin(origins = "http://localhost:4006/")
@RequestMapping("/cart")
public class CartController {
    @Autowired CartService cartService;
    @Autowired
    CustomerService customerService;

    @GetMapping("select")
    public ResponseEntity<?> selectCart(@Valid @RequestBody CartSelectRequest cartSelectRequest, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
        }
        Long sumCart=cartService.selectCart(cartSelectRequest);
        return ResponseEntity.status(HttpStatus.OK).body(sumCart);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<?> getCartByCustomer(@PathVariable int customerId)
    {
        CartByCustomerDto cartByCustomer=cartService.getCartByCustomer(customerId);
        if(cartByCustomer.getCartEntities().size()==0)
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Giỏ hàng trống");
        }
        return ResponseEntity.status(HttpStatus.OK).body(cartByCustomer);
    }

    @PostMapping("")
    public ResponseEntity<?> createCart(@Valid @RequestBody CartRequest cartRequest, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
        }
        cartService.createCart(cartRequest);
        CartByCustomerDto cartByCustomer=cartService.getCartByCustomer(cartRequest.getCustomerId());
        if(cartByCustomer.getCartEntities().size()==0)
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Giỏ hàng trống");
        }
        return new ResponseEntity<>(cartByCustomer,HttpStatus.OK);
    }
    @PutMapping("")
    public ResponseEntity<?> updateCart(@Valid @RequestBody CartRequest cartRequest, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
        }
        cartService.updateCart(cartRequest);
        CartByCustomerDto cartByCustomer=cartService.getCartByCustomer(cartRequest.getCustomerId());
        if(cartByCustomer.getCartEntities().size()==0)
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Giỏ hàng trống");
        }
        return ResponseEntity.status(HttpStatus.OK).body(cartByCustomer);
    }
    @DeleteMapping("")
    public ResponseEntity<?> deleteCart(@Valid @RequestBody CartRequest cartRequest, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
        }
        cartService.deleteCart(cartRequest);
        CartByCustomerDto cartByCustomer=cartService.getCartByCustomer(cartRequest.getCustomerId());
        if(cartByCustomer.getCartEntities().size()==0)
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Giỏ hàng trống");
        }
        return new ResponseEntity<>(cartByCustomer,HttpStatus.OK);
    }

}
