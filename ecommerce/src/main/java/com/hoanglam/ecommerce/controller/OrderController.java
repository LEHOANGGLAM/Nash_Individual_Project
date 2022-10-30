package com.hoanglam.ecommerce.controller;


import com.hoanglam.ecommerce.dto.request.OrderResquestDto;
import com.hoanglam.ecommerce.dto.response.entities.OrderResponseDto;
import com.hoanglam.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("")
public class OrderController {
    @Autowired
    private OrderService orderService;


    @PostMapping("/user/{userId}/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponseDto createOrder(@PathVariable String userId, @Valid @RequestBody OrderResquestDto orderResquestDto){
        return orderService.createOrder(orderResquestDto , userId);
    }
}
