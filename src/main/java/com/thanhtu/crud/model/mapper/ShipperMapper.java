package com.thanhtu.crud.model.mapper;

import com.thanhtu.crud.entity.AdminsEntity;
import com.thanhtu.crud.entity.DeliveryEntity;
import com.thanhtu.crud.entity.ShipperEntity;
import com.thanhtu.crud.model.dto.AdminsDto;
import com.thanhtu.crud.model.dto.ShipperDto;
import com.thanhtu.crud.model.request.AdminsRequest;
import com.thanhtu.crud.model.request.ShipperRequest;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class ShipperMapper {
    public static ShipperDto toShipperDto(ShipperEntity shipperEntity)
    {
        ShipperDto tmp=new ShipperDto();
        tmp.setShipper_id(shipperEntity.getShipper_id());
        tmp.setUserShipper(shipperEntity.getUserShipper());
        tmp.setFullnameShipper(shipperEntity.getFullnameShipper());
        tmp.setGmailShipper(shipperEntity.getGmailShipper());
        return tmp;
    }
    public static ShipperEntity toShipperEntity(ShipperRequest shipperRequest)
    {
        ShipperEntity tmp=new ShipperEntity();
        tmp.setUserShipper(shipperRequest.getUserShipper());
        tmp.setPasswordShipper(BCrypt.hashpw(shipperRequest.getPasswordShipper(),BCrypt.gensalt(12)));
        tmp.setFullnameShipper(shipperRequest.getFullnameShipper());
        tmp.setGmailShipper(shipperRequest.getGmailShipper());
        return tmp;
    }
    public static ShipperEntity toUpdateShipperEntity(ShipperEntity shipperEntity,ShipperRequest shipperRequest)
    {
        shipperEntity.setUserShipper(shipperRequest.getUserShipper());
        shipperEntity.setPasswordShipper(BCrypt.hashpw(shipperRequest.getPasswordShipper(),BCrypt.gensalt(12)) );
        shipperEntity.setFullnameShipper(shipperRequest.getFullnameShipper());
        shipperEntity.setGmailShipper(shipperRequest.getGmailShipper());
        return shipperEntity;
    }
}
