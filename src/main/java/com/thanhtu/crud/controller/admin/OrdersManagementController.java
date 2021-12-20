package com.thanhtu.crud.controller.admin;
import com.thanhtu.crud.entity.OrderDetailEntity;
import com.thanhtu.crud.entity.OrdersEntity;
import com.thanhtu.crud.model.dto.OrdersDetailDto;
import com.thanhtu.crud.model.dto.OrdersDto;
import com.thanhtu.crud.model.mapper.OrdersMapper;
import com.thanhtu.crud.model.request.*;
import com.thanhtu.crud.service.OrdersDetailService;
import com.thanhtu.crud.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersManagementController {
    @Autowired
    OrdersService ordersService;
    @Autowired
    OrdersDetailService orderDetaiService;


    @GetMapping("")
    public ResponseEntity<?> getListOrderByStatus(@Valid @RequestBody OrdersStatusRequest ordersStatusRequest,BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.NOT_ACCEPTABLE);
        }
        List<OrdersEntity> listOrders=ordersService.getListOrderByStatus(ordersStatusRequest);
        List<OrdersDto> dtoList=new ArrayList<OrdersDto>();
        for(OrdersEntity ordersEntity:listOrders)
        {
            dtoList.add(OrdersMapper.toOrdersDto(ordersEntity));
        }
        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrders(@PathVariable("id") Integer id,
                                          @Valid @RequestBody OrdersUpdateRequest ordersUpdateRequest, BindingResult bing)
    {
        if(bing.hasErrors())
        {
            return new ResponseEntity<>(bing.getAllErrors(),HttpStatus.NOT_ACCEPTABLE);
        }
        OrdersDto ordersDto=ordersService.updateOrders(id,ordersUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(ordersDto);
    }
    @PutMapping("/approval")
    public ResponseEntity<?> approvalOrders(@Valid @RequestBody List<OrdersUpdateStatusRequest> list,
                                            BindingResult bingResult)
    {
        if(bingResult.hasErrors())
        {
            return new ResponseEntity<>(bingResult.getAllErrors(),HttpStatus.NOT_ACCEPTABLE);
        }
        ordersService.approvalOrders(list);
        List<OrdersEntity> listOrders=ordersService.getListOrderByStatus("Chưa duyệt");
        List<OrdersDto> dtoList=new ArrayList<OrdersDto>();
        for(OrdersEntity ordersEntity:listOrders)
        {
            dtoList.add(OrdersMapper.toOrdersDto(ordersEntity));
        }
        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }
    @PutMapping ("/assign")
    public ResponseEntity<?> assignOrders(@Valid @RequestBody List<OrdersAssignRequest> list,BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.NOT_ACCEPTABLE);
        }
        ordersService.assignOrders(list);
        List<OrdersEntity> listOrders=ordersService.getListOrderByStatus("Đã duyệt");
        List<OrdersDto> dtoList=new ArrayList<OrdersDto>();
        for(OrdersEntity ordersEntity:listOrders)
        {
            dtoList.add(OrdersMapper.toOrdersDto(ordersEntity));
        }
        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }
    @GetMapping ("/detail/{id}")
    public ResponseEntity<?> detailOrders(@PathVariable("id") Integer id)
    {
        List<OrdersDetailDto> list=orderDetaiService.detailOrders(id);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
    @GetMapping("/statics")
    public ResponseEntity<?> statics(@RequestBody RequestDate requestDate)
    {
        List<OrdersEntity> list=ordersService.statics(requestDate);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
}
