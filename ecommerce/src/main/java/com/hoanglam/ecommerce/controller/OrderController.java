package com.hoanglam.ecommerce.controller;


import com.hoanglam.ecommerce.dto.request.CartItemRequestDto;
import com.hoanglam.ecommerce.dto.request.OrderItemRequestDto;
import com.hoanglam.ecommerce.dto.request.OrderResquestDto;
import com.hoanglam.ecommerce.entites.CartItem;
import com.hoanglam.ecommerce.entites.Order;
import com.hoanglam.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;


    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Order createCartItem(@Valid @RequestBody OrderResquestDto orderResquestDto){
        return orderService.createOrder(orderResquestDto);
    }
}
