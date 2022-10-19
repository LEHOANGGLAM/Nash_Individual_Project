package com.hoanglam.ecommerce.mappers;

import com.hoanglam.ecommerce.dto.request.OrderItemRequestDto;
import com.hoanglam.ecommerce.dto.request.OrderResquestDto;
import com.hoanglam.ecommerce.entites.*;
import com.hoanglam.ecommerce.exception.ResourceNotFoundException;
import com.hoanglam.ecommerce.repository.OrderItemRepository;
import com.hoanglam.ecommerce.repository.ProductRepository;
import com.hoanglam.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class OrderMapper {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private OrderItemRepository orderItemRepository;

    public Order mapOrderRequestDtoToEntity(OrderResquestDto cDto) {
        Optional<User> userOptional = userRepository.findById(cDto.getUserId());
        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException("User not exist with id: " + cDto.getUserId());
        }



        return Order.builder()
                .userId(userOptional.get())
                .address(cDto.getAddress())
                .paymentMethod(cDto.getPaymentMethod())
                .build();
    }
}
