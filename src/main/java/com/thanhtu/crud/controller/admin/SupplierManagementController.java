package com.thanhtu.crud.controller.admin;

import com.thanhtu.crud.entity.SupplierEntity;
import com.thanhtu.crud.model.dto.CategoryDto;
import com.thanhtu.crud.model.dto.SupplierDto;
import com.thanhtu.crud.model.request.CategoryRequest;
import com.thanhtu.crud.model.request.SupplierRequest;
import com.thanhtu.crud.service.CategoryService;
import com.thanhtu.crud.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
//@PreAuthorize("hasAuthority('ADMIN')")
@CrossOrigin(origins = "https://react-admin-eight.vercel.app")
@RequestMapping("admin/supplier")
public class SupplierManagementController {

    @Autowired
    SupplierService supplierService;

    @GetMapping("/all")
    public ResponseEntity<Object> getListSupplier()
    {
        List<SupplierDto> listCategory=supplierService.getListSupplier();
        return ResponseEntity.status(HttpStatus.OK).body(listCategory);
    }

    @GetMapping("")
    public ResponseEntity<?> getListBySupplierName(@Valid @RequestBody SupplierRequest supplierRequest, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.NOT_ACCEPTABLE);
        }
        SupplierDto supplierDto=supplierService.getListBySupplierName(supplierRequest);
        return ResponseEntity.status(HttpStatus.OK).body(supplierDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierDto> getSupplierById(@PathVariable int id) {
        SupplierDto supplierById = supplierService.getSupplierById(id);
        return ResponseEntity.status(HttpStatus.OK).body(supplierById);
    }

    @PostMapping("")
    public ResponseEntity<?> createSupplier(@Valid @RequestBody SupplierRequest supplierRequest, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.NOT_ACCEPTABLE);
        }
        SupplierDto supplierDto= supplierService.createSupplier(supplierRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(supplierDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSupplier(@PathVariable("id") Integer id,@Valid @RequestBody SupplierRequest supplierRequest,BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.NOT_ACCEPTABLE);
        }
        SupplierDto supplierDto=supplierService.updateSupplier(id,supplierRequest);
        return ResponseEntity.status(HttpStatus.OK).body(supplierDto);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<Object> deleteSupplier(@PathVariable("id") Integer id)
    {
        SupplierDto supplierDto=supplierService.deleteSupplier(id);
        return new ResponseEntity<>("Xóa thành công",HttpStatus.OK);
    }
}
