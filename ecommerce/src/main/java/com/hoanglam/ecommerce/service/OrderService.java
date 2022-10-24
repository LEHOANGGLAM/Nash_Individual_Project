package com.hoanglam.ecommerce.service;

import com.hoanglam.ecommerce.dto.request.OrderResquestDto;
import com.hoanglam.ecommerce.dto.response.entities.OrderResponseDto;

public interface OrderService {
    OrderResponseDto createOrder(OrderResquestDto o);
}
