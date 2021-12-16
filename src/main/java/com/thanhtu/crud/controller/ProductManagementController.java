package com.thanhtu.crud.controller;

import com.thanhtu.crud.model.dto.ProductDto;
import com.thanhtu.crud.model.request.ProductRequest;
import com.thanhtu.crud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductManagementController {

    @Autowired
    ProductService productService;

    @GetMapping("")
    public ResponseEntity<Object> getListProduct()
    {
        List<ProductDto> listProduct=productService.getListProduct();
        return ResponseEntity.status(HttpStatus.OK).body(listProduct);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable int id) {
        ProductDto productById = productService.getProductById(id);
        return new ResponseEntity<>(productById,HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductRequest productRequest, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.NOT_ACCEPTABLE);
        }
        ProductDto productDto= productService.createProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") Integer id, @Valid @RequestBody ProductRequest productRequest,
                                                    BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.NOT_ACCEPTABLE);
        }
        ProductDto productDto=productService.updateProduct(id,productRequest);
        return ResponseEntity.status(HttpStatus.OK).body(productDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") Integer id)
    {
        ProductDto productDto=productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
