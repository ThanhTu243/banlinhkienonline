package com.thanhtu.crud.service;


import com.thanhtu.crud.model.dto.WishListDto;
import com.thanhtu.crud.model.request.WishListRequest;

import java.util.List;

public interface WishListService {
    void createWishList(WishListRequest request);

    List<WishListDto> getListWishList(String customerId);

    void deleteWishList(Integer id);
}
