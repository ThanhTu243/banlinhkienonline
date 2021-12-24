package com.thanhtu.crud.model.dto;

import com.thanhtu.crud.model.dto.fk.ProductFKDto;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class SupplierDto {
    private Integer supplierId;
    private String supplierName;
    private Set<ProductFKDto> productEntityList;
}
