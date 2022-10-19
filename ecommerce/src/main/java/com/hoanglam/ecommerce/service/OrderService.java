package com.hoanglam.ecommerce.service;

import com.hoanglam.ecommerce.dto.request.OrderItemRequestDto;
import com.hoanglam.ecommerce.dto.request.OrderResquestDto;
import com.hoanglam.ecommerce.entites.Order;
import com.hoanglam.ecommerce.entites.Product;

import java.util.List;

public interface OrderService {
    Order createOrder(OrderResquestDto o);
}
