package com.thanhtu.crud.controller.admin;

import com.thanhtu.crud.entity.ProductEntity;
import com.thanhtu.crud.model.dto.ProductDto;
import com.thanhtu.crud.model.mapper.ProductMapper;
import com.thanhtu.crud.model.request.product.ProductByCategoryRequest;
import com.thanhtu.crud.model.request.product.ProductByNameRequest;
import com.thanhtu.crud.model.request.product.ProductBySupplierRequest;
import com.thanhtu.crud.model.request.product.ProductRequest;
import com.thanhtu.crud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductManagementController {

    @Autowired
    ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<Object> getListProduct(@RequestParam(value = "page",required = false) Optional<Integer> page)
    {
        if(page.isPresent())
        {
            int pageNumber= page.get();
            page=Optional.of(pageNumber-1);

        }
        else{
            page=Optional.of(0);
        }
        Pageable pageable= PageRequest.of(page.get(),2);
        Page<ProductEntity> list=productService.getListProduct(pageable);
        int totalPages=list.getTotalPages();
        int currentPage=list.getNumber()+1;
        List<ProductEntity> listPro=list.toList();
        return ResponseEntity.status(HttpStatus.OK).body(ProductMapper.toProductPageDto(listPro,totalPages,currentPage));
    }
    @GetMapping("")
    public ResponseEntity<?> getListProductByName(@RequestParam(value = "page",required = false) Optional<Integer> page,
                                                  @Valid @RequestBody ProductByNameRequest productByNameRequest,
                                                  BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.NOT_ACCEPTABLE);
        }
        if(page.isPresent())
        {
            int pageNumber= page.get();
            page=Optional.of(pageNumber-1);

        }
        else{
            page=Optional.of(0);
        }
        Pageable pageable= PageRequest.of(page.get(),10);

        Page<ProductEntity> list=productService.getListProductByName(productByNameRequest,pageable);
        int totalPages=list.getTotalPages();
        int currentPage=list.getNumber()+1;
        List<ProductEntity> listPro=list.toList();
        return ResponseEntity.status(HttpStatus.OK).body(ProductMapper.toProductPageDto(listPro,totalPages,currentPage));
    }

    @GetMapping("/category")
    public ResponseEntity<?> getListProductByCategory(@RequestParam(value = "page",required = false) Optional<Integer> page,
                                                  @Valid @RequestBody ProductByCategoryRequest productByCategoryRequest,
                                                  BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.NOT_ACCEPTABLE);
        }
        if(page.isPresent())
        {
            int pageNumber= page.get();
            page=Optional.of(pageNumber-1);

        }
        else{
            page=Optional.of(0);
        }
        Pageable pageable= PageRequest.of(page.get(),10);

        Page<ProductEntity> list=productService.getListProductByCategory(productByCategoryRequest,pageable);
        int totalPages=list.getTotalPages();
        int currentPage=list.getNumber()+1;
        List<ProductEntity> listPro=list.toList();
        return ResponseEntity.status(HttpStatus.OK).body(ProductMapper.toProductPageDto(listPro,totalPages,currentPage));
    }

    @GetMapping("/supplier")
    public ResponseEntity<?> getListProductBySupplier(@RequestParam(value = "page",required = false) Optional<Integer> page,
                                                      @Valid @RequestBody ProductBySupplierRequest productBySupplierRequest,
                                                      BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.NOT_ACCEPTABLE);
        }
        if(page.isPresent())
        {
            int pageNumber= page.get();
            page=Optional.of(pageNumber-1);

        }
        else{
            page=Optional.of(0);
        }
        Pageable pageable= PageRequest.of(page.get(),2);

        Page<ProductEntity> list=productService.getListProductBySupplier(productBySupplierRequest,pageable);
        int totalPages=list.getTotalPages();
        int currentPage=list.getNumber()+1;
        List<ProductEntity> listPro=list.toList();
        return ResponseEntity.status(HttpStatus.OK).body(ProductMapper.toProductPageDto(listPro,totalPages,currentPage));
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

    @PutMapping("/delete/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") Integer id)
    {
        ProductDto productDto=productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
