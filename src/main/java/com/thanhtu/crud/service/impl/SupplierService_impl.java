package com.thanhtu.crud.service.impl;

import com.thanhtu.crud.entity.CategoryEntity;
import com.thanhtu.crud.entity.SupplierEntity;
import com.thanhtu.crud.exception.DuplicateRecoredException;
import com.thanhtu.crud.exception.NotFoundException;
import com.thanhtu.crud.model.dto.CategoryDto;
import com.thanhtu.crud.model.dto.SupplierDto;
import com.thanhtu.crud.model.mapper.CategoryMapper;
import com.thanhtu.crud.model.mapper.SupplierMapper;
import com.thanhtu.crud.model.request.CategoryRequest;
import com.thanhtu.crud.model.request.SupplierRequest;
import com.thanhtu.crud.repository.CategoryRepository;
import com.thanhtu.crud.repository.SupplierRepository;
import com.thanhtu.crud.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SupplierService_impl implements SupplierService  {
    @Autowired
    SupplierRepository supplierRepo;

    @Override
    public List<SupplierDto> getListSupplier()
    {
        List<SupplierEntity> supplierEntities=supplierRepo.findSupplierEntityByIsDelete("NO");
        List<SupplierDto> supplierDtos=new ArrayList<SupplierDto>();
        for(SupplierEntity supplierEntity:supplierEntities)
        {
            supplierDtos.add(SupplierMapper.toSupplierDto(supplierEntity));
        }
        return supplierDtos;
    }

    @Override
    public SupplierDto getSupplierById(int id)  {
        SupplierEntity supplier=supplierRepo.findSupplierEntityBySupplierIdAndIsDelete(id,"NO");
        if(supplier==null)
        {
            throw new NotFoundException("Nhà cung cấp không tồn tại với id: "+id);
        }
        return SupplierMapper.toSupplierDto(supplier);
    }

    @Override
    public SupplierDto createSupplier(SupplierRequest supplierRequest) {
        List<SupplierEntity> list=supplierRepo.findSupplierEntityByIsDelete("NO");
        for(SupplierEntity supplierEn:list)
        {
            if(supplierEn.getSupplierName().equals(supplierRequest.getSupplierName()))
            {
                throw new DuplicateRecoredException("Trùng tên nhà cung cấp");
            }
        }
        SupplierEntity supplierEntity=supplierRepo.save(SupplierMapper.toSupplierEntity(supplierRequest));
        return SupplierMapper.toSupplierDto(supplierEntity);
    }

    @Override
    public SupplierDto updateSupplier(Integer id, SupplierRequest supplierRequest) {
        SupplierEntity supplierEntity=supplierRepo.findSupplierEntityBySupplierIdAndIsDelete(id,"NO");
        if(supplierEntity==null)
        {
            throw new NotFoundException("Nhà cung cấp không tồn tại với id: "+id);
        }
        if(!supplierEntity.getSupplierName().equals(supplierRequest.getSupplierName()))
        {
            List<SupplierEntity> list=supplierRepo.findSupplierEntityByIsDelete("NO");
            for(SupplierEntity supplierEn:list)
            {
                if(supplierEn.getSupplierName().equals(supplierRequest.getSupplierName()))
                {
                    throw new DuplicateRecoredException("Trùng tên nhà cung cấp");
                }
            }
        }
        supplierEntity.setSupplierName(supplierRequest.getSupplierName());
        supplierEntity.setSupplierImage(supplierRequest.getSupplierImage());
        return SupplierMapper.toSupplierDto(supplierRepo.save(supplierEntity));
    }

    @Override
    public SupplierDto deleteSupplier(Integer id) {
        SupplierEntity supplierEntity=supplierRepo.findSupplierEntityBySupplierIdAndIsDelete(id,"NO");
        if(supplierEntity==null)
        {
            throw new NotFoundException("Nhà cung cấp không tồn tại với id: "+id);
        }
        supplierEntity.setIsDelete("YES");
        SupplierEntity supplier=supplierRepo.save(supplierEntity);
        return SupplierMapper.toSupplierDto(supplier);
    }

    @Override
    public SupplierDto getListBySupplierName(SupplierRequest supplierRequest) {
        SupplierEntity supplier=supplierRepo.findSupplierEntityBySupplierNameAndIsDelete(supplierRequest.getSupplierName(),"NO");
        return SupplierMapper.toSupplierDto(supplier);
    }
}
