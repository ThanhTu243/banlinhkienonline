package com.thanhtu.crud.model.dto;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartIDKeyDto {
    private Integer customerId;
    private Integer productId;
}
