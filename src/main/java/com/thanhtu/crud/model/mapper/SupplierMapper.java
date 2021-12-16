package com.thanhtu.crud.model.mapper;

import com.thanhtu.crud.entity.SupplierEntity;
import com.thanhtu.crud.model.dto.fk.SupplierFKDto;

public class SupplierMapper {
    public static SupplierFKDto toSupplierViewDto(SupplierEntity supplierEntity)
    {
        SupplierFKDto tmp = new SupplierFKDto();
        tmp.setSupplierId(supplierEntity.getSupplier_id());
        tmp.setSupplierName(supplierEntity.getSupplierName());
        return tmp;
    }

}
