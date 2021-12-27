package com.thanhtu.crud.controller.customer;

import com.thanhtu.crud.entity.ProductEntity;
import com.thanhtu.crud.model.dto.ProductDto;
import com.thanhtu.crud.model.mapper.ProductMapper;
import com.thanhtu.crud.model.request.product.ProductByCategoryRequest;
import com.thanhtu.crud.service.CategoryService;
import com.thanhtu.crud.service.ProductService;
import com.thanhtu.crud.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("view/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    SupplierService supplierService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable int id) {
        ProductDto productById = productService.getProductById(id);
        return new ResponseEntity<>(productById,HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<Object> getListProduct(@RequestParam(value = "page",required = false) Optional<Integer> page,
                                                 @RequestParam(value = "keyword",required = false) String keyword,
                                                 @RequestParam(value="supplier",required = false) String supplier,
                                                 @RequestParam(value = "category",required = false) String category)
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
        int totalPages=0;
        int currentPage=0;
        List<ProductEntity> listPro=new ArrayList<ProductEntity>();
        if(category!=null && supplier==null && keyword ==null)
        {
            Page<ProductEntity> productByCategoryList=productService.getListProductByCategoryName(category,pageable);
            totalPages=productByCategoryList.getTotalPages();
            currentPage=productByCategoryList.getNumber()+1;
            listPro=productByCategoryList.toList();
        }
        else if(category ==null && supplier!=null && keyword==null)
        {
            Page<ProductEntity> productBySupplierList=productService.getListProductBySupplierName(supplier,pageable);
            totalPages=productBySupplierList.getTotalPages();
            currentPage=productBySupplierList.getNumber()+1;
            listPro=productBySupplierList.toList();
        }
        else if(category ==null && supplier==null && keyword != null)
        {
            Page<ProductEntity> productByKeyWordList=productService.getListProductByKeyWord1(keyword,pageable);
            totalPages=productByKeyWordList.getTotalPages();
            currentPage=productByKeyWordList.getNumber()+1;
            listPro=productByKeyWordList.toList();
        }
        else if(category !=null && supplier==null && keyword != null)
        {
            Page<ProductEntity> productByKeyWordAndCategoryList=productService.getListProductByKeyWordAndCategory(keyword,category,pageable);
            totalPages=productByKeyWordAndCategoryList.getTotalPages();
            currentPage=productByKeyWordAndCategoryList.getNumber()+1;
            listPro=productByKeyWordAndCategoryList.toList();
        }
        else if(category ==null && supplier!=null && keyword != null)
        {
            Page<ProductEntity> productByKeyWordAndSupplierList=productService.getListProductByKeyWordAndSupplier(keyword,supplier,pageable);
            totalPages=productByKeyWordAndSupplierList.getTotalPages();
            currentPage=productByKeyWordAndSupplierList.getNumber()+1;
            listPro=productByKeyWordAndSupplierList.toList();
        }
        else if(category !=null && supplier!=null && keyword == null)
        {
            Page<ProductEntity> productByCategoryAndSupplierList=productService.getListProductByCategoryAndSupplier(category,supplier,pageable);
            totalPages=productByCategoryAndSupplierList.getTotalPages();
            currentPage=productByCategoryAndSupplierList.getNumber()+1;
            listPro=productByCategoryAndSupplierList.toList();
        }
        else if(category ==null && supplier ==null && keyword == null)
        {
            Page<ProductEntity> productAllList=productService.getListProduct(pageable);
            totalPages=productAllList.getTotalPages();
            currentPage=productAllList.getNumber()+1;
            listPro=productAllList.toList();
        }
        else if(category !=null && supplier !=null && keyword != null)
        {
            Page<ProductEntity> productByCategoryAndSupplierAndKeyword=productService.getListProductByCategoryAndSupplierAndKeyword(category,supplier,keyword,pageable);
            totalPages=productByCategoryAndSupplierAndKeyword.getTotalPages();
            currentPage=productByCategoryAndSupplierAndKeyword.getNumber()+1;
            listPro=productByCategoryAndSupplierAndKeyword.toList();
        }


        return ResponseEntity.status(HttpStatus.OK).body(ProductMapper.toProductPageDto(listPro,totalPages,currentPage));
    }
}
