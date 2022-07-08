package com.thanhtu.crud.controller.customer;

import com.thanhtu.crud.model.dto.ReviewsDto;
import com.thanhtu.crud.model.dto.WishListDto;
import com.thanhtu.crud.model.request.ReviewsCreateRequest;
import com.thanhtu.crud.model.request.WishListRequest;
import com.thanhtu.crud.service.ReviewsService;
import com.thanhtu.crud.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
//@PreAuthorize("hasAuthority('CUSTOMER')")
//@CrossOrigin(origins = "https://shoppt-reactapp.vercel.app/")
@RequestMapping("/wishlist")
public class WishListController {
    @Autowired
    WishListService wishListService;
    @PostMapping
    public ResponseEntity<?> createWishList(@Valid @RequestBody WishListRequest request, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
        }
        wishListService.createWishList(request);
        return ResponseEntity.status(HttpStatus.OK).body("Thêm thành công");
    }

    @GetMapping("")
    public ResponseEntity<?> getListWishList(@RequestParam ("customerId") String customerId)
    {
        List<WishListDto> wishList= wishListService.getListWishList(customerId);
        if(wishList.size()==0)
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Danh sách yêu thích trống");
        }
        return ResponseEntity.status(HttpStatus.OK).body(wishList);
    }
    @PutMapping("")
    public ResponseEntity<?> deleteWishList(@RequestParam("wishlistId") String wishlistId)
    {
        wishListService.deleteWishList(Integer.valueOf(wishlistId));
        return ResponseEntity.status(HttpStatus.OK).body("Xóa thành  công");
    }
}
