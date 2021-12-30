package com.thanhtu.crud.controller.admin;
import com.thanhtu.crud.entity.OrderDetailEntity;
import com.thanhtu.crud.entity.OrdersEntity;
import com.thanhtu.crud.entity.ProductEntity;
import com.thanhtu.crud.model.dto.OrderDetailViewDto;
import com.thanhtu.crud.model.dto.OrdersDetailDto;
import com.thanhtu.crud.model.dto.OrdersDto;
import com.thanhtu.crud.model.dto.OrdersIdDto;
import com.thanhtu.crud.model.mapper.OrdersMapper;
import com.thanhtu.crud.model.request.*;
import com.thanhtu.crud.service.OrdersDetailService;
import com.thanhtu.crud.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4004/")
@RequestMapping("/admin/orders")
public class OrdersManagementController {
    @Autowired
    OrdersService ordersService;
    @Autowired
    OrdersDetailService orderDetaiService;


    @PostMapping("")
    public ResponseEntity<?> getListOrderByStatus(@RequestParam(value = "page",required = false) Optional<Integer> page,@Valid @RequestBody OrdersStatusRequest ordersStatusRequest, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.NOT_ACCEPTABLE);
        }
        if(page.isPresent())
        {
            int pageNumber= page.get();
            page=Optional.of(pageNumber-1);

        }
        else{
            page=Optional.of(0);
        }
        Pageable pageable= PageRequest.of(page.get(),10);
        Page<OrdersEntity> list=ordersService.getListOrderByStatus(ordersStatusRequest.getStatusOrder(),pageable);
        int totalPages=list.getTotalPages();
        int currentPage=list.getNumber()+1;
        List<OrdersEntity> listOrders=list.toList();
        return ResponseEntity.status(HttpStatus.OK).body(OrdersMapper.toOrdersPageDto(listOrders,totalPages,currentPage));
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
    public ResponseEntity<?> approvalOrders(@RequestParam(value = "page",required = false) Optional<Integer> page,@Valid @RequestBody OrdersUpdateStatusRequest ordersUpdateStatusRequest,
                                            BindingResult bingResult)
    {
        if(bingResult.hasErrors())
        {
            return new ResponseEntity<>(bingResult.getAllErrors(),HttpStatus.NOT_ACCEPTABLE);
        }
        ordersService.approvalOrders(ordersUpdateStatusRequest);
        if(page.isPresent())
        {
            int pageNumber= page.get();
            page=Optional.of(pageNumber-1);

        }
        else{
            page=Optional.of(0);
        }
        Pageable pageable= PageRequest.of(page.get(),10);
        Page<OrdersEntity> list=ordersService.getListOrderByStatus("Chưa duyệt",pageable);
        int totalPages=list.getTotalPages();
        int currentPage=list.getNumber()+1;
        List<OrdersEntity> listOrders=list.toList();
        return ResponseEntity.status(HttpStatus.OK).body(OrdersMapper.toOrdersPageDto(listOrders,totalPages,currentPage));
    }
    @PutMapping("/delivered")
    public ResponseEntity<?> orderDelivered(@RequestParam(value = "page",required = false) Optional<Integer> page, @Valid @RequestBody OrdersUpdateStatusRequest ordersUpdateStatusRequest,
                                            BindingResult bingResult)
    {
        if(bingResult.hasErrors())
        {
            return new ResponseEntity<>(bingResult.getAllErrors(),HttpStatus.NOT_ACCEPTABLE);
        }
        ordersService.orderDelivered(ordersUpdateStatusRequest);
        if(page.isPresent())
        {
            int pageNumber= page.get();
            page=Optional.of(pageNumber-1);

        }
        else{
            page=Optional.of(0);
        }
        Pageable pageable= PageRequest.of(page.get(),10);
        Page<OrdersEntity> list=ordersService.getListOrderByStatus("Đã duyệt",pageable);
        int totalPages=list.getTotalPages();
        int currentPage=list.getNumber()+1;
        List<OrdersEntity> listOrders=list.toList();
        return ResponseEntity.status(HttpStatus.OK).body(OrdersMapper.toOrdersPageDto(listOrders,totalPages,currentPage));
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
        OrderDetailViewDto orders=orderDetaiService.detailOrders(id);
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<?> cancelOrder(@RequestParam(value = "page",required = false) Optional<Integer> page, @PathVariable("id") Integer id)
    {
        ordersService.cancelOrder(id);
        if(page.isPresent())
        {
            int pageNumber= page.get();
            page=Optional.of(pageNumber-1);

        }
        else{
            page=Optional.of(0);
        }
        Pageable pageable= PageRequest.of(page.get(),10);
        Page<OrdersEntity> list=ordersService.getListOrderByStatus("Chưa duyệt",pageable);
        int totalPages=list.getTotalPages();
        int currentPage=list.getNumber()+1;
        List<OrdersEntity> listOrders=list.toList();
        return ResponseEntity.status(HttpStatus.OK).body(OrdersMapper.toOrdersPageDto(listOrders,totalPages,currentPage));
    }
}
