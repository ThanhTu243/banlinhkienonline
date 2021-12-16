package com.thanhtu.crud.service.impl;

import com.thanhtu.crud.entity.AdminsEntity;
import com.thanhtu.crud.entity.ShipperEntity;
import com.thanhtu.crud.exception.DuplicateRecoredException;
import com.thanhtu.crud.exception.NotFoundException;
import com.thanhtu.crud.model.dto.AdminsDto;
import com.thanhtu.crud.model.dto.ShipperDto;
import com.thanhtu.crud.model.mapper.AdminsMapper;
import com.thanhtu.crud.model.mapper.ShipperMapper;
import com.thanhtu.crud.model.request.AdminsRequest;
import com.thanhtu.crud.model.request.ShipperRequest;
import com.thanhtu.crud.repository.ShipperRepository;
import com.thanhtu.crud.service.ShipperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ShipperService_imp implements ShipperService {
    @Autowired
    ShipperRepository shipperRepo;
    @Override
    public List<ShipperDto> getListShipper() {
        List<ShipperEntity> shipperEntityList=shipperRepo.findAll();
        List<ShipperDto> shipperDtos=new ArrayList<ShipperDto>();
        for(ShipperEntity shipperEntity:shipperEntityList)
        {
            shipperDtos.add(ShipperMapper.toShipperDto(shipperEntity));
        }
        return shipperDtos;
    }

    @Override
    public ShipperDto getShipperById(int id) {
        ShipperEntity shipperEntity=shipperRepo.findById(id).orElseThrow(()-> new NotFoundException("không tồn tại tài khoản shipper với id: "+id));
        return ShipperMapper.toShipperDto(shipperEntity);
    }

    @Override
    public ShipperDto createShipper(ShipperRequest shipperRequest) {
        List<ShipperEntity> list=shipperRepo.findAll();
        for(ShipperEntity shipperEntity:list)
        {
            if(shipperEntity.getUserShipper().equals(shipperRequest.getUserShipper()))
            {
                throw new DuplicateRecoredException("Trùng tên tài khoản");
            }
            else if(shipperEntity.getGmailShipper().equals(shipperRequest.getGmailShipper()))
            {
                throw new DuplicateRecoredException("Trùng Mail rồi");
            }
        }
        ShipperEntity shipperEntity=shipperRepo.save(ShipperMapper.toShipperEntity(shipperRequest));
        return ShipperMapper.toShipperDto(shipperEntity);
    }

    @Override
    public ShipperDto updateShipper(Integer id, ShipperRequest shipperRequest) {
        ShipperEntity shipperEntity=shipperRepo.findById(id).orElseThrow(()-> new NotFoundException("Không tồn tại tài khoản shipper với id: "+id));
        List<ShipperEntity> list=shipperRepo.findAll();
        if(!shipperEntity.getUserShipper().equals(shipperRequest.getUserShipper()))
        {
            for(ShipperEntity shipper:list)
            {
                if(shipper.getUserShipper().equals(shipperRequest.getUserShipper()))
                {
                    throw new DuplicateRecoredException("Trùng tên tài khoản");
                }
            }
        }
        else if(!shipperEntity.getGmailShipper().equals(shipperRequest.getGmailShipper()))
        {
            for(ShipperEntity shipper:list)
            {
                if(shipper.getGmailShipper().equals(shipperRequest.getGmailShipper()))
                {
                    throw new DuplicateRecoredException("Trùng Mail rồi");
                }
            }
        }

        ShipperEntity shipper=shipperRepo.save(ShipperMapper.toUpdateShipperEntity(shipperEntity,shipperRequest));
        return ShipperMapper.toShipperDto(shipper);
    }

    @Override
    public ShipperDto deleteShipper(Integer id) {
        ShipperEntity shipper=shipperRepo.findById(id).orElseThrow(()-> new NotFoundException("Không tồn tại tài khoản shipper với id: "+id));
        shipperRepo.deleteById(id);
        return null;
    }

}
