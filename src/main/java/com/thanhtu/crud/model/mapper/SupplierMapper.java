package com.thanhtu.crud.model.mapper;

import com.thanhtu.crud.entity.CategoryEntity;
import com.thanhtu.crud.entity.SupplierEntity;
import com.thanhtu.crud.model.dto.SupplierDto;
import com.thanhtu.crud.model.dto.fk.SupplierFKDto;
import com.thanhtu.crud.model.request.CategoryRequest;
import com.thanhtu.crud.model.request.SupplierRequest;

public class SupplierMapper {
    public static SupplierFKDto toSupplierViewDto(SupplierEntity supplierEntity)
    {
        SupplierFKDto tmp = new SupplierFKDto();
        tmp.setSupplierId(supplierEntity.getSupplierId());
        tmp.setSupplierName(supplierEntity.getSupplierName());
        return tmp;
    }
    public static SupplierDto toSupplierDto(SupplierEntity supplierEntity)
    {
        SupplierDto tmp=new SupplierDto();
        tmp.setSupplierId(supplierEntity.getSupplierId());
        tmp.setSupplierName(supplierEntity.getSupplierName());
        tmp.setSupplierImage(supplierEntity.getSupplierImage());
        tmp.setProductEntityList(ProductMapper.toProductFKDto(supplierEntity.getProductEntitySet()));
        return tmp;
    }
    public static SupplierEntity toSupplierEntity(SupplierRequest supplierRequest)
    {
        SupplierEntity tmp = new SupplierEntity();
        tmp.setSupplierName(supplierRequest.getSupplierName());
        tmp.setSupplierImage(supplierRequest.getSupplierImage());
        tmp.setIsDelete("NO");
        return tmp;
    }

}
