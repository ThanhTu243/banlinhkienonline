package com.thanhtu.crud.controller;

import com.thanhtu.crud.model.dto.CartDto;
import com.thanhtu.crud.model.dto.CategoryDto;
import com.thanhtu.crud.model.request.CartRequest;
import com.thanhtu.crud.model.request.CategoryRequest;
import com.thanhtu.crud.service.CartService;
import com.thanhtu.crud.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired CartService cartService;

    @PostMapping("")
    public ResponseEntity<?> createCart(@Valid @RequestBody CartRequest cartRequest, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
        }
        CartDto cartDto= cartService.createCart(cartRequest);
        return new ResponseEntity<>(cartDto,HttpStatus.CREATED);
    }
}
