package com.thanhtu.crud.service.impl;

import com.thanhtu.crud.entity.CartEntity;
import com.thanhtu.crud.entity.CartIDKey;
import com.thanhtu.crud.entity.CustomerEntity;
import com.thanhtu.crud.entity.ProductEntity;
import com.thanhtu.crud.exception.NotEnoughQuantityException;
import com.thanhtu.crud.exception.NotFoundException;
import com.thanhtu.crud.model.dto.CartByCustomerDto;
import com.thanhtu.crud.model.dto.CartDto;
import com.thanhtu.crud.model.dto.fk.CartFKViewDto;
import com.thanhtu.crud.model.mapper.CartIdKeyMapper;
import com.thanhtu.crud.model.mapper.CartMapper;
import com.thanhtu.crud.model.request.CartRequest;
import com.thanhtu.crud.repository.CartRepository;
import com.thanhtu.crud.repository.CustomerRepository;
import com.thanhtu.crud.repository.ProductRepository;
import com.thanhtu.crud.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CartService_impl implements CartService {
    @Autowired
    CartRepository cartRepo;
    @Autowired
    ProductRepository proRepo;
    @Autowired
    CustomerRepository customerRepo;
    @Override
    public CartDto createCart(CartRequest cartRequest) {
        ProductEntity product=proRepo.findProductEntityByProductIdAndIsDelete(cartRequest.getProductId(), "NO");
        if(product==null)
        {
            throw new NotFoundException("Không tìm thấy sản phẫm có id: "+cartRequest.getProductId());
        }
        if(cartRequest.getQuantity()> product.getQuantity())
        {
            throw new NotEnoughQuantityException("Không đủ số lượng. Sản phẫm chỉ còn "+product.getQuantity());
        }
        CustomerEntity customer=customerRepo.getById(cartRequest.getCustomerId());
        CartEntity cartEntity=cartRepo.save(CartMapper.toCartEntity(cartRequest,product,customer));
        return CartMapper.toCartDto(cartEntity);
    }

    @Override
    public CartDto updateCart(CartRequest cartRequest) {
        ProductEntity product=proRepo.findProductEntityByProductIdAndIsDelete(cartRequest.getProductId(), "NO");
        CartEntity cart=cartRepo.findCartEntityById(CartIdKeyMapper.toCartIdKey(cartRequest));
        if(cart==null)
        {
            throw new NotFoundException("Không tìm thấy sản phẫm có id: "+cartRequest.getProductId());
        }
        if(cartRequest.getQuantity()> product.getQuantity())
        {
            throw new NotEnoughQuantityException("Không đủ số lượng. Sản phẫm chỉ còn "+product.getQuantity());
        }

        cart.setQuantity(cartRequest.getQuantity());
        cartRepo.save(cart);
        return CartMapper.toCartDto(cart);
    }

    @Override
    public CartDto deleteCart(CartRequest cartRequest) {
        ProductEntity product=proRepo.findProductEntityByProductIdAndIsDelete(cartRequest.getProductId(), "NO");
        CartEntity cart=cartRepo.findCartEntityById(CartIdKeyMapper.toCartIdKey(cartRequest));
        if(cart==null)
        {
            throw new NotFoundException("Không có mặt hàng này trong giỏ");
        }
        cartRepo.delete(cart);
        return CartMapper.toCartDto(cart);
    }

    @Override
    public CartByCustomerDto getCartByCustomer(int customerId) {
        CustomerEntity customerEntity= customerRepo.findCustomerEntityByCustomerIdAndIsDelete(customerId,"NO");
        if(customerEntity==null)
        {
            throw new NotFoundException("Không tìm thấy khách hàng có Id: "+customerId);
        }
        List<CartEntity> listCart=cartRepo.findCartEntityByCustomerEntity(customerEntity);
        CartByCustomerDto cartByCustomerDto=new CartByCustomerDto();
        cartByCustomerDto.setCustomerId(customerId);
        cartByCustomerDto.setUserCustomer(customerEntity.getUserCustomer());
        cartByCustomerDto.setFullnameCustomer(customerEntity.getFullnameCustomer());
        cartByCustomerDto.setGmailCustomer(customerEntity.getGmailCustomer());
        cartByCustomerDto.setPhoneNumberCustomer(customerEntity.getPhonenumberCustomer());
        cartByCustomerDto.setAddress(customerEntity.getAddress());
        Set<CartFKViewDto> set=new HashSet<CartFKViewDto>();
        for(CartEntity cart:listCart)
        {
            ProductEntity product= proRepo.findProductEntityByProductIdAndIsDelete(cart.getId().getProductId(),"NO");
            set.add(CartMapper.toCartFKViewDto(product,cart));
        }
        cartByCustomerDto.setCartEntities(set);
        return cartByCustomerDto;
    }
}
