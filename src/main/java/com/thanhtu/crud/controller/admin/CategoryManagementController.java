package com.thanhtu.crud.controller.admin;

import com.thanhtu.crud.entity.CategoryEntity;
import com.thanhtu.crud.model.dto.CategoryDto;
import com.thanhtu.crud.model.request.CategoryRequest;
import com.thanhtu.crud.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryManagementController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity<Object> getListCategory()
    {
        List<CategoryDto> listCategory=categoryService.getListCategory();
        return ResponseEntity.status(HttpStatus.OK).body(listCategory);
    }

    @GetMapping("")
    public ResponseEntity<?> getListByCategoryName(@Valid @RequestBody CategoryRequest categoryRequest, BindingResult bindingResult)
    {
        List<CategoryDto> listCategory=categoryService.getListByCategoryName(categoryRequest);
        return ResponseEntity.status(HttpStatus.OK).body(listCategory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCateById(@PathVariable int id) {
        CategoryDto cateById = categoryService.getCateById(id);
        return ResponseEntity.status(HttpStatus.OK).body(cateById);
    }

    @PostMapping("")
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryRequest categoryRequest, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.NOT_ACCEPTABLE);
        }
        CategoryDto categoryDto= categoryService.createCategory(categoryRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable("id") Integer id,@Valid @RequestBody CategoryRequest categoryRequest,BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.NOT_ACCEPTABLE);
        }
        CategoryDto categoryDto=categoryService.updateCategory(id,categoryRequest);
        return ResponseEntity.status(HttpStatus.OK).body(categoryDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable("id") Integer id)
    {
        CategoryDto categoryDto=categoryService.deleteCategory(id);
        return new ResponseEntity<>("Xóa thành công",HttpStatus.OK);
    }
}
