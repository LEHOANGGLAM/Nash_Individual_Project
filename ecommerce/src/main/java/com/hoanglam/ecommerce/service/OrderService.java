package com.hoanglam.ecommerce.service;

import com.hoanglam.ecommerce.dto.request.OrderResquestDto;
import com.hoanglam.ecommerce.dto.response.entities.OrderResponseDto;
import com.hoanglam.ecommerce.entites.OrderItem;

import java.util.List;

public interface OrderService {
    OrderResponseDto createOrder(OrderResquestDto o, String userId);
    List<OrderResponseDto> getOrderByUserId(String userId);

    List<OrderItem> getOrderItemNoRatingByUserId(String userId);
}
