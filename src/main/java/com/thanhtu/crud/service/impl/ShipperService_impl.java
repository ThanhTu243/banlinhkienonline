package com.thanhtu.crud.service.impl;

import com.thanhtu.crud.entity.AccountsEntity;
import com.thanhtu.crud.entity.ShipperEntity;
import com.thanhtu.crud.exception.DuplicateRecoredException;
import com.thanhtu.crud.exception.NotFoundException;
import com.thanhtu.crud.model.dto.ShipperDto;
import com.thanhtu.crud.model.mapper.AccountMapper;
import com.thanhtu.crud.model.mapper.ShipperMapper;
import com.thanhtu.crud.model.request.ShipperRequest;
import com.thanhtu.crud.repository.AccountsRepository;
import com.thanhtu.crud.repository.ShipperRepository;
import com.thanhtu.crud.service.ShipperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ShipperService_impl implements ShipperService {
    @Autowired
    ShipperRepository shipperRepo;

    @Autowired
    AccountsRepository accountsRepo;

    @Override
    public List<ShipperDto> getListShipper() {
        List<ShipperEntity> shipperList=shipperRepo.findShipperEntityByIsDelete("NO");
        List<ShipperDto> adminList=new ArrayList<ShipperDto>();
        for(ShipperEntity shipperEn:shipperList)
        {
            adminList.add(ShipperMapper.toShipperDto(shipperEn));
        }
        return adminList;
    }

    @Override
    public ShipperDto getShipperById(int id) {
        ShipperEntity shipper=shipperRepo.findShipperEntityByShipperIdAndIsDelete(id,"NO");
        if(shipper==null)
        {
            throw new NotFoundException("Không tìm thấy shipper có id: "+id);
        }
        return ShipperMapper.toShipperDto(shipper);
    }

    @Override
    public ShipperDto createShipper(ShipperRequest shipperRequest) {
        List<AccountsEntity> accountsEntityList= accountsRepo.findAll();
        for(AccountsEntity accounts: accountsEntityList)
        {
            if(accounts.getUsername().equals(shipperRequest.getUserShipper()))
            {
                throw new DuplicateRecoredException("Trùng tên tài khoản");
            }
            else if(accounts.getGmail().equals(shipperRequest.getGmailShipper()))
            {
                throw new DuplicateRecoredException("Trùng Mail rồi");
            }
        }
        ShipperEntity shipper=shipperRepo.save(ShipperMapper.toShipperEntity(shipperRequest));
        accountsRepo.save(AccountMapper.toShipperAccountEntity(shipperRequest));
        return ShipperMapper.toShipperDto(shipper);
    }

    @Override
    public ShipperDto updateShipper(Integer id, ShipperRequest shipperRequest) {
        ShipperEntity shipperEntity=shipperRepo.findShipperEntityByShipperIdAndIsDelete(id,"NO");
        if(shipperEntity==null)
        {
            throw new NotFoundException("Không tài tại tài khoản với id: "+id);
        }
        AccountsEntity accounts=accountsRepo.findAccountsEntitiesByUsername(shipperEntity.getUserShipper());
        List<AccountsEntity> list=accountsRepo.findAll();
        if(!shipperEntity.getUserShipper().equals(shipperRequest.getUserShipper()))
        {
            for(AccountsEntity account:list)
            {
                if(account.getUsername().equals(shipperRequest.getUserShipper()))
                {
                    throw new DuplicateRecoredException("Trùng tên tài khoản");
                }
            }
        }
        else if(!shipperEntity.getGmailShipper().equals(shipperRequest.getGmailShipper()))
        {
            for(AccountsEntity account:list)
            {
                if(account.getGmail().equals(shipperRequest.getGmailShipper()))
                {
                    throw new DuplicateRecoredException("Trùng Mail rồi");
                }
            }
        }
        ShipperEntity shipper=shipperRepo.save(ShipperMapper.toUpdateShipperEntity(shipperEntity,shipperRequest));
        accountsRepo.save(AccountMapper.toUpdateAccountShipperEntity(accounts,shipperRequest));

        return ShipperMapper.toShipperDto(shipper);
    }

    @Override
    public ShipperDto deleteShipper(Integer id) {
        ShipperEntity shipperEntity=shipperRepo.findShipperEntityByShipperIdAndIsDelete(id,"NO");
        if(shipperEntity==null)
        {
            throw new NotFoundException("Không tài tại tài khoản với id: "+id);
        }
        AccountsEntity account=accountsRepo.findAccountsEntitiesByUsername(shipperEntity.getUserShipper());
        shipperEntity.setIsDelete("YES");
        account.setActiveAccount("NOT ACTIVE");
        shipperRepo.save(shipperEntity);
        accountsRepo.save(account);
        return null;
    }

}
