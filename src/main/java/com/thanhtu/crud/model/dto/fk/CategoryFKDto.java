package com.thanhtu.crud.model.dto.fk;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryFKDto {
    private Integer categoryId;
    private String categoryName;
}
