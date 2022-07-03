package com.thanhtu.crud.model.request.product;

import lombok.*;
import javax.validation.constraints.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductRequest {
    @NotNull(message = "Nhập tên sản phẫm")
    @NotEmpty(message = "Nhập tên sản phẫm")
    @Size(max =1000,message = "Nhập tên sản phẫm nhỏ hơn 100 ký tự")
    private String productName;

    @NotNull(message = "Nhập số lượng sản phẫm")
    @Digits(message = "Vui lòng nhập số",fraction = 0,integer = 5)
    @Min(value =0,message = "Nhập số dương")
    @Max(value = 10000,message = "Số lượng nhỏ hơn 10000")
    private Integer quantity;

    @NotNull(message = "Vui lòng tải lên hình sản phẫm")
    @NotEmpty(message = "Vui lòng tải lên hình sản phẫm")
    private List<ProductImageRequest> productImage;

    @NotNull(message = "Nhập phần trăm giảm giá")
    @Min(value =0,message = "Nhập số dương")
    @Max(value = 100,message = "Nhập giá nhỏ hơn 100")
    private Integer discount;

    @NotNull(message = "Nhập số giá sản phẫm")
    @Min(value =0,message = "Nhập số dương")
    private Integer unitPrice;

    //@NotNull(message = "Nhập mô tả sản phẫm")
    //@NotEmpty(message = "Nhập mô tả sản phẫm")
    //@Pattern(regexp = "^[a-zA-ZàáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ]+(\\s[a-zA-ZàáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ]+)*$",message = "Nhập ký tự không nhập những ký tự đặc biệt")
    private String descriptionProduct;

    @NotNull(message = "Vui lòng chọn danh mục")
    private Integer categoryId;

    @NotNull(message = "Vui lòng chọn nhà sản xuất")
    private Integer supplierId;
}
