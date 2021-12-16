package com.thanhtu.crud.service;

import com.thanhtu.crud.model.dto.AdminsDto;
import com.thanhtu.crud.model.dto.ShipperDto;
import com.thanhtu.crud.model.request.AdminsRequest;
import com.thanhtu.crud.model.request.ShipperRequest;

import java.util.List;


public interface ShipperService {
    List<ShipperDto> getListShipper();

    ShipperDto getShipperById(int id);

    ShipperDto createShipper(ShipperRequest shipperRequest);

    ShipperDto updateShipper(Integer id, ShipperRequest shipperRequest);

    ShipperDto deleteShipper(Integer id);
}
