package com.thanhtu.crud.service.impl;

import com.thanhtu.crud.entity.CustomerEntity;
import com.thanhtu.crud.entity.ProductEntity;
import com.thanhtu.crud.entity.SupplierEntity;
import com.thanhtu.crud.entity.WishListEntity;
import com.thanhtu.crud.exception.DuplicateRecoredException;
import com.thanhtu.crud.exception.NotFoundException;
import com.thanhtu.crud.model.dto.SupplierDto;
import com.thanhtu.crud.model.dto.WishListDto;
import com.thanhtu.crud.model.mapper.SupplierMapper;
import com.thanhtu.crud.model.mapper.WishListMapper;
import com.thanhtu.crud.model.request.SupplierRequest;
import com.thanhtu.crud.model.request.WishListRequest;
import com.thanhtu.crud.repository.CustomerRepository;
import com.thanhtu.crud.repository.ProductRepository;
import com.thanhtu.crud.repository.SupplierRepository;
import com.thanhtu.crud.repository.WishListRepository;
import com.thanhtu.crud.service.SupplierService;
import com.thanhtu.crud.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishListService_impl implements WishListService {
    @Autowired
    WishListRepository wishListRepo;
    @Autowired
    CustomerRepository customerRepo;
    @Autowired
    ProductRepository productRepo;

    @Override
    public void createWishList(WishListRequest request) {
        CustomerEntity customer=customerRepo.findCustomerEntityByCustomerIdAndIsDelete(request.getCustomerId(), "NO");
        ProductEntity product=productRepo.findProductEntityByProductIdAndIsDelete(request.getProductId(),"NO");
        wishListRepo.save(WishListMapper.toWishListEntity(customer,product));
    }

    @Override
    public List<WishListDto> getListWishList(String customerId) {
        CustomerEntity customer=customerRepo.findCustomerEntityByCustomerIdAndIsDelete(Integer.valueOf(customerId), "NO");
        List<WishListEntity> wishListEntityList=wishListRepo.findWishListEntitiesByCustomerEntityAndIsDelete(customer,"NO");
        List<WishListDto> wishListDtoList=new ArrayList<>();
        for(WishListEntity wishList:wishListEntityList)
        {
            wishListDtoList.add(WishListMapper.toWishListDto(wishList));
        }
        return wishListDtoList;
    }

    @Override
    public void deleteWishList(Integer id) {
        WishListEntity wishList=wishListRepo.findWishListEntitiesByWishlistIdAndIsDelete(id,"NO");
        wishList.setIsDelete("YES");
        wishListRepo.save(wishList);
    }
}
