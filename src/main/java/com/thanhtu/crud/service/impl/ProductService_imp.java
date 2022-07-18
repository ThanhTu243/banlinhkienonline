package com.thanhtu.crud.service.impl;

import com.thanhtu.crud.entity.*;
import com.thanhtu.crud.exception.DuplicateRecoredException;
import com.thanhtu.crud.exception.NotFoundException;
import com.thanhtu.crud.model.dto.ProductDto;
import com.thanhtu.crud.model.mapper.ProductMapper;
import com.thanhtu.crud.model.request.ChangeIsDeleteRequest;
import com.thanhtu.crud.model.request.product.*;
import com.thanhtu.crud.repository.*;
import com.thanhtu.crud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService_imp implements ProductService {

    @Autowired
    ProductRepository productRepo;
    @Autowired
    CategoryRepository categoryRepo;
    @Autowired
    SupplierRepository supplierRepo;
    @Autowired
    ImageRepository imageRepo;

    @Autowired ProductService productService;


    @Override
    public Page<ProductEntity> getListProduct(Pageable pageable) {
        return productRepo.findProductEntityByIsDelete("NO",pageable);
    }
    @Override
    public Page<ProductEntity> getListProductAdmin(Pageable pageable) {
        return productRepo.findAll(pageable);
    }

    @Override
    public ProductDto getProductById(int id) {
        ProductEntity productEntity=productRepo.findProductEntityByProductId(id);
        return ProductMapper.toProductDto(productEntity);
    }

    @Override
    public ProductDto getProductByIdAdmin(int id) {
        ProductEntity productEntity=productRepo.findProductEntityByProductId(id);
        return ProductMapper.toProductDto(productEntity);
    }

    @Override
    public ProductDto createProduct(ProductRequest productRequest) {
        List<ProductEntity> list=productRepo.findAll();
        for(ProductEntity productEntity:list)
        {
            if(productEntity.getProductName().equals(productRequest.getProductName()))
            {
                throw new DuplicateRecoredException("Trùng tên sản phẫm rồi");
            }
        }
        CategoryEntity categoryEntity=categoryRepo.getById(productRequest.getCategoryId());
        SupplierEntity supplierEntity=supplierRepo.getById(productRequest.getSupplierId());
        //save ProductEntity
        ProductEntity newProduct=productRepo.save(ProductMapper.toProductEntity(productRequest,categoryEntity,supplierEntity));
        int productId= newProduct.getProductId();
        //--save imageEntity
        List<ImageEntity> imageEntities=new ArrayList<>();
        for(ProductImageRequest product:productRequest.getProductImage())
        {
            ImageEntity newImage=new ImageEntity();
            newImage.setImage(product.getName());
            newImage.setIsDelete("NO");
            newImage.setProductEntity(newProduct);
            imageEntities.add(imageRepo.save(newImage));
        }
        return ProductMapper.toProductDto(newProduct,imageEntities);
    }

    @Override
    public ProductDto updateProduct(Integer id, ProductRequest productRequest) {
        ProductEntity productEntity=productRepo.findProductEntityByProductIdAndIsDelete(id,"NO");
        if(productEntity==null)
        {
            throw new NotFoundException("Sản phẫm không tồn tại với id: "+id);
        }
        if(!productEntity.getProductName().equals(productRequest.getProductName()))
        {
            List<ProductEntity> list=productRepo.findAll();
            for(ProductEntity productEntity1:list)
            {
                if(id!=productEntity1.getProductId() && productEntity1.getProductName().equals(productRequest.getProductName()))
                {
                    throw new DuplicateRecoredException("Trùng tên sản phẫm rồi");
                }
            }
        }
        CategoryEntity categoryEntity=categoryRepo.getById(productRequest.getCategoryId());
        SupplierEntity supplierEntity=supplierRepo.getById(productRequest.getSupplierId());
        ProductEntity productEntity1=productRepo.save(ProductMapper.toUpdateProduct(productEntity,productRequest,categoryEntity,supplierEntity));
        //delete current image
        for(ImageEntity image:productEntity1.getImageEntities())
        {
            image.setIsDelete("YES");
            imageRepo.save(image);
        }
        //--save imageEntity

        for(ProductImageRequest product:productRequest.getProductImage())
        {
            ImageEntity newImage=new ImageEntity();
            newImage.setImage(product.getName());
            newImage.setIsDelete("NO");
            newImage.setProductEntity(productEntity1);
            imageRepo.save(newImage);
        }
        List<ImageEntity> imageEntities=imageRepo.findImageEntitiesByProductEntityAndIsDelete(productEntity1,"NO");
        return ProductMapper.toProductDto(productEntity1,imageEntities);
    }


    @Override
    public ProductDto deleteProduct(Integer id) {
        ProductEntity productEntity=productRepo.findProductEntityByProductIdAndIsDelete(id,"NO");
        if(productEntity==null)
        {
            throw new NotFoundException("Sản phẫm không tồn tại với id: "+id);
        }
        productEntity.setIsDelete("YES");
        productRepo.save(productEntity);
        return ProductMapper.toProductDto(productEntity);
    }

    @Override
    public Page<ProductEntity> getListProductByName(ProductByNameRequest productByNameRequest, Pageable pageable) {
        return productRepo.findProductEntityByProductNameContainsAndIsDelete(productByNameRequest.getProductName(),"NO",pageable);
    }

    @Override
    public Page<ProductEntity> getListProductByCategory(ProductByCategoryRequest productByCategoryRequest, Pageable pageable) {
        CategoryEntity category=categoryRepo.findCategoryEntityByCategoryNameAndIsDelete(productByCategoryRequest.getCategoryName(),"NO");
        return productRepo.findProductEntityByCategoryEntityAndIsDelete(category,"NO",pageable);
    }
    public Page<ProductEntity> getListProductByCategoryName(String categoryName, Pageable pageable) {
        CategoryEntity category=categoryRepo.findCategoryEntityByCategoryNameAndIsDelete(categoryName,"NO");
        return productRepo.findProductEntityByCategoryEntityAndIsDelete(category,"NO",pageable);
    }

    @Override
    public Page<ProductEntity> getListProductBySupplier(ProductBySupplierRequest productBySupplierRequest, Pageable pageable) {
        SupplierEntity supplier=supplierRepo.findSupplierEntityBySupplierNameAndIsDelete(productBySupplierRequest.getSupplierName(),"NO");
        return productRepo.findProductEntityBySupplierEntityAndIsDelete(supplier,"NO",pageable);
    }

    @Override
    public Page<ProductEntity> getListProductBySupplierName(String supplierName, Pageable pageable) {
        SupplierEntity supplier=supplierRepo.findSupplierEntityBySupplierNameAndIsDelete(supplierName,"NO");
        return productRepo.findProductEntityBySupplierEntityAndIsDelete(supplier,"NO",pageable);
    }

    @Override
    public Page<ProductEntity> getListProductByKeyWord(String keyword,Pageable page) {
        return productRepo.findProductEntityByProductNameLike(keyword,page);
    }

    @Override
    public Page<ProductEntity> getListProductByKeyWord1(String keyword,Pageable page) {
        return productRepo.findAllByProductNameContainsAndIsDelete(keyword,"NO",page);
    }

    @Override
    public Page<ProductEntity> getListProductByKeyWord1Admin(String keyword, Pageable pageable) {
        return productRepo.findAllByProductNameContains(keyword,pageable);
    }

    @Override
    public Page<ProductEntity> getListProductByKeyWordAndCategory(String keyword, String categoryName, Pageable pageable) {
        CategoryEntity category=categoryRepo.findCategoryEntityByCategoryNameAndIsDelete(categoryName,"NO");
        return productRepo.findAllByProductNameContainsAndCategoryEntityAndIsDelete(keyword,category,"NO",pageable);
    }

    @Override
    public Page<ProductEntity> getListProductByKeyWordAndSupplier(String keyword, String supplierName, Pageable pageable) {
        SupplierEntity supplier=supplierRepo.findSupplierEntityBySupplierNameAndIsDelete(supplierName,"NO");
        return productRepo.findAllByProductNameContainsAndSupplierEntityAndIsDelete(keyword,supplier,"NO",pageable);
    }

    @Override
    public Page<ProductEntity> getListProductByCategoryAndSupplier(String categoryName, String supplierName, Pageable pageable) {
        CategoryEntity category=categoryRepo.findCategoryEntityByCategoryNameAndIsDelete(categoryName,"NO");
        SupplierEntity supplier=supplierRepo.findSupplierEntityBySupplierNameAndIsDelete(supplierName,"NO");
        return productRepo.findAllByCategoryEntityAndSupplierEntityAndIsDelete(category,supplier,"NO",pageable);
    }

    @Override
    public Page<ProductEntity> getListProductByCategoryAndSupplierAndKeyword(String categoryName, String supplierName, String keyword, Pageable pageable) {
        CategoryEntity category=categoryRepo.findCategoryEntityByCategoryNameAndIsDelete(categoryName,"NO");
        SupplierEntity supplier=supplierRepo.findSupplierEntityBySupplierNameAndIsDelete(supplierName,"NO");
        return productRepo.findAllByProductNameContainsAndCategoryEntityAndSupplierEntityAndIsDelete(keyword,category,supplier,"NO",pageable);
    }

    @Override
    public List<ProductDto> getTop10DiscountProduct() {
        List<ProductEntity> list=productRepo.findProductEntityByIsDeleteOrderByDiscountDesc("NO");
        List<ProductDto> listTop10DiscountProduct=new ArrayList<ProductDto>();
        for(int i=0;i<10;i++)
        {
            listTop10DiscountProduct.add(ProductMapper.toProductDto(list.get(i)));
        }
        return listTop10DiscountProduct;
    }

    @Override
    public void changeIsDelete(Integer id, ChangeIsDeleteRequest changeIsDeleteRequest) {
        ProductEntity product=productRepo.findProductEntityByProductId(id);
        product.setIsDelete(changeIsDeleteRequest.getIsDelete());
        productRepo.save(product);
    }

    @Override
    public Page<ProductEntity> getListProductCustomer(Pageable pageable) {
        return productRepo.findProductEntityByIsDelete("NO",pageable);
    }


}
