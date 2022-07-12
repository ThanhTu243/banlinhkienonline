package com.thanhtu.crud.controller.customer;

import com.thanhtu.crud.model.dto.ReviewsDto;
import com.thanhtu.crud.model.request.ReviewsCreateRequest;
import com.thanhtu.crud.service.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
//@PreAuthorize("hasAuthority('CUSTOMER')")
@CrossOrigin(origins = "https://shoppt-reactapp.vercel.app")
@RequestMapping("/reviews")
public class ReviewsController {
    @Autowired
    ReviewsService reviewsService;
    @PostMapping
    public ResponseEntity<?> createReviews(@Valid @RequestBody ReviewsCreateRequest request, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
        }
        ReviewsDto reviewsDto=reviewsService.createReviews(request);
        return ResponseEntity.status(HttpStatus.OK).body(reviewsDto);
    }
}
