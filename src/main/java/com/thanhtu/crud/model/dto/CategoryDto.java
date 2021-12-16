package com.thanhtu.crud.model.dto;

import com.thanhtu.crud.model.dto.fk.ProductFKDto;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class CategoryDto {
    private Integer categoryId;
    private String categoryName;
    private Set<ProductFKDto> productEntityList;
}
