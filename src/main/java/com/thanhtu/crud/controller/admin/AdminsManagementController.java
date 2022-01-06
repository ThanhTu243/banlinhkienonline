package com.thanhtu.crud.controller.admin;
import com.thanhtu.crud.entity.AdminsEntity;
import com.thanhtu.crud.exception.SelfDestructionExeption;
import com.thanhtu.crud.model.dto.AdminsDto;
import com.thanhtu.crud.model.request.AdminIdRequest;
import com.thanhtu.crud.model.request.AdminsRequest;
import com.thanhtu.crud.service.AdminsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
//@PreAuthorize("hasAuthority('ADMIN')")
@CrossOrigin(origins = "http://localhost:4004/")
@RequestMapping("admin/admin")
public class AdminsManagementController {
    @Autowired
    AdminsService adminsService;


    @GetMapping("")
    public ResponseEntity<?> getListAdmin()
    {
        List<AdminsDto> listCategory=adminsService.getListAdmin();
        return ResponseEntity.status(HttpStatus.OK).body(listCategory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminsDto> getAdminById(@PathVariable int id) {
        AdminsDto adminById = adminsService.getAdminById(id);
        return ResponseEntity.status(HttpStatus.OK).body(adminById);
    }

    @PostMapping("")
    public ResponseEntity<?> createAdmin(@Valid @RequestBody AdminsRequest adminsRequest, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.NOT_ACCEPTABLE);
        }
        AdminsDto adminsDto= adminsService.createAdmin(adminsRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(adminsDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAdmin(@PathVariable("id") Integer id,@Valid @RequestBody AdminsRequest adminsRequest, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.NOT_ACCEPTABLE);
        }
        AdminsDto adminsDto=adminsService.updateAdmin(id,adminsRequest);
        return ResponseEntity.status(HttpStatus.OK).body(adminsDto);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<Object> deleteAdmin(@Valid @RequestBody AdminIdRequest adminIdRequest, @PathVariable("id") Integer id) throws SelfDestructionExeption
    {
        if(id.equals(adminIdRequest.getId()))
        {
            throw new SelfDestructionExeption("Không tự xóa chính mình");
        }
        adminsService.deleteAdmin(id);
        return new ResponseEntity<>("Xóa thành công",HttpStatus.OK);
    }
}
