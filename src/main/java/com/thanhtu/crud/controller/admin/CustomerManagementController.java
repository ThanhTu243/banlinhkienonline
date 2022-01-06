package com.thanhtu.crud.controller.admin;

import com.thanhtu.crud.entity.CustomerEntity;
import com.thanhtu.crud.entity.ProductEntity;
import com.thanhtu.crud.model.dto.AdminsDto;
import com.thanhtu.crud.model.dto.CustomerDto;
import com.thanhtu.crud.model.mapper.CustomerMapper;
import com.thanhtu.crud.model.mapper.ProductMapper;
import com.thanhtu.crud.model.request.AdminsRequest;
import com.thanhtu.crud.model.request.CustomerRequest;
import com.thanhtu.crud.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
//@PreAuthorize("hasAuthority('ADMIN')")
@CrossOrigin(origins = "http://localhost:4004/")
@RequestMapping("/admin/customer")
public class CustomerManagementController {
    @Autowired
    CustomerService customerService;
    @GetMapping("/all")
    public ResponseEntity<Object> getListCustomer(@RequestParam(value = "page",required = false) Optional<Integer> page)
    {
        if(page.isPresent())
        {
            int pageNumber= page.get();
            page=Optional.of(pageNumber-1);

        }
        else{
            page=Optional.of(0);
        }
        Pageable pageable= PageRequest.of(page.get(),10);

        Page<CustomerEntity> list=customerService.getListCustomer(pageable);
        int totalPages=list.getTotalPages();
        int currentPage=list.getNumber()+1;
        List<CustomerEntity> listCustomer=list.toList();
        return ResponseEntity.status(HttpStatus.OK).body(CustomerMapper.toCustomerPageDto(listCustomer,totalPages,currentPage));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCustomer(@PathVariable("id") Integer id, @Valid @RequestBody CustomerRequest customerRequest, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.NOT_ACCEPTABLE);
        }
        CustomerDto customerDto=customerService.updateCustomer(id,customerRequest);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable int id) {
        return new ResponseEntity<>(CustomerMapper.toCustomerDto(customerService.getCustomerById(id)), HttpStatus.OK);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<Object> deleteAdmin(@PathVariable("id") Integer id)
    {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>("Xóa thành công",HttpStatus.OK);
    }
}
