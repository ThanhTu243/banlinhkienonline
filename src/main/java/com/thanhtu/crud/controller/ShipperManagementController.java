package com.thanhtu.crud.controller;

import com.thanhtu.crud.model.dto.AdminsDto;
import com.thanhtu.crud.model.dto.ShipperDto;
import com.thanhtu.crud.model.request.AdminsRequest;
import com.thanhtu.crud.model.request.ShipperRequest;
import com.thanhtu.crud.service.ShipperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/shipper")
public class ShipperManagementController {
    @Autowired
    ShipperService shipperService;

    @GetMapping("")
    public ResponseEntity<?> getListShipper()
    {
        List<ShipperDto> listShipper=shipperService.getListShipper();
        return ResponseEntity.status(HttpStatus.OK).body(listShipper);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShipperDto> getShipperById(@PathVariable int id) {
        ShipperDto shipperById = shipperService.getShipperById(id);
        return ResponseEntity.status(HttpStatus.OK).body(shipperById);
    }

    @PostMapping("")
    public ResponseEntity<?> createShipper(@Valid @RequestBody ShipperRequest shipperRequest, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.NOT_ACCEPTABLE);
        }
        ShipperDto shipperDto= shipperService.createShipper(shipperRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(shipperDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateShipper(@PathVariable("id") Integer id,@Valid @RequestBody ShipperRequest shipperRequest)
    {
        ShipperDto shipperDto=shipperService.updateShipper(id,shipperRequest);
        return ResponseEntity.status(HttpStatus.OK).body(shipperDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteShipper(@PathVariable("id") Integer id)
    {
        ShipperDto shipperDto=shipperService.deleteShipper(id);
        return new ResponseEntity<>("Xóa thành công",HttpStatus.OK);
    }
}
