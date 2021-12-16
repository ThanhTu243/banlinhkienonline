package com.thanhtu.crud.service.impl;

import com.thanhtu.crud.entity.CategoryEntity;
import com.thanhtu.crud.entity.ProductEntity;
import com.thanhtu.crud.entity.SupplierEntity;
import com.thanhtu.crud.exception.DuplicateRecoredException;
import com.thanhtu.crud.exception.NotFoundException;
import com.thanhtu.crud.model.dto.CategoryDto;
import com.thanhtu.crud.model.dto.ProductDto;
import com.thanhtu.crud.model.mapper.CategoryMapper;
import com.thanhtu.crud.model.mapper.ProductMapper;
import com.thanhtu.crud.model.request.CategoryRequest;
import com.thanhtu.crud.model.request.ProductRequest;
import com.thanhtu.crud.repository.CategoryRepository;
import com.thanhtu.crud.repository.ProductRepository;
import com.thanhtu.crud.repository.SupplierRepository;
import com.thanhtu.crud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService_imp implements ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    SupplierRepository supplierRepository;

    @Override
    public List<ProductDto> getListProduct() {
        List<ProductEntity> productEntityList=productRepository.findAll();
        List<ProductDto> productDtoList=new ArrayList<ProductDto>();
        for(ProductEntity productEntity:productEntityList)
        {
            productDtoList.add(ProductMapper.toProductDto(productEntity));
        }
        return productDtoList;
    }

    @Override
    public ProductDto getProductById(int id) {
        ProductEntity productEntity=productRepository.findById(id).orElseThrow(()-> new NotFoundException("Sản phẫm không tồn tại với id: "+id));
        return ProductMapper.toProductDto(productEntity);
    }

    @Override
    public ProductDto createProduct(ProductRequest productRequest) {
        List<ProductEntity> list=productRepository.findAll();
        for(ProductEntity productEntity:list)
        {
            if(productEntity.getProductName().equals(productRequest.getProductName()))
            {
                throw new DuplicateRecoredException("Trùng tên sản phẫm rồi");
            }
        }
        CategoryEntity categoryEntity=categoryRepository.getById(productRequest.getCategoryId());
        SupplierEntity supplierEntity=supplierRepository.getById(productRequest.getSupplierId());
        ProductEntity product=ProductMapper.toProductEntity(productRequest,categoryEntity,supplierEntity);
        ProductEntity productEntity=productRepository.save(product);
        return ProductMapper.toProductDto(productEntity);
    }

    @Override
    public ProductDto updateProduct(Integer id, ProductRequest productRequest) {
        ProductEntity productEntity=productRepository.findById(id).orElseThrow(()-> new NotFoundException("Sản phẫm không tồn tại với id: "+id));
        if(!productEntity.getProductName().equals(productRequest.getProductName()))
        {
            List<ProductEntity> list=productRepository.findAll();
            for(ProductEntity productEntity1:list)
            {
                if(productEntity1.getProductName().equals(productRequest.getProductName()))
                {
                    throw new DuplicateRecoredException("Trùng tên sản phẫm rồi");
                }
            }
        }
        CategoryEntity categoryEntity=categoryRepository.getById(productRequest.getCategoryId());
        SupplierEntity supplierEntity=supplierRepository.getById(productRequest.getSupplierId());
        ProductEntity productEntity1=productRepository.save(ProductMapper.toUpdateProduct(productEntity,productRequest,categoryEntity,supplierEntity));
        return ProductMapper.toProductDto(productEntity1);
    }


    @Override
    public ProductDto deleteProduct(Integer id) {
        productRepository.deleteById(id);
        return null;
    }
}
