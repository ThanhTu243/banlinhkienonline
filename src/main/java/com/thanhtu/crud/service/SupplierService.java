package com.thanhtu.crud.service;

import com.thanhtu.crud.model.dto.CategoryDto;
import com.thanhtu.crud.model.dto.SupplierDto;
import com.thanhtu.crud.model.request.CategoryRequest;
import com.thanhtu.crud.model.request.SupplierRequest;

import java.util.List;

public interface SupplierService {

    List<SupplierDto> getListSupplier();

    SupplierDto getListBySupplierName(SupplierRequest supplierRequest);

    SupplierDto getSupplierById(int id);

    SupplierDto createSupplier(SupplierRequest supplierRequest);

    SupplierDto updateSupplier(Integer id, SupplierRequest supplierRequest);

    SupplierDto deleteSupplier(Integer id);
}
