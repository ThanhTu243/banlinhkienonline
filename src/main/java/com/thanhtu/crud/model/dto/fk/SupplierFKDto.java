package com.thanhtu.crud.model.dto.fk;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SupplierFKDto {
    private Integer supplierId;
    private String supplierName;
}
