package com.hoanglam.ecommerce.mappers;


import com.hoanglam.ecommerce.dto.request.CartItemRequestDto;
import com.hoanglam.ecommerce.dto.request.OrderItemRequestDto;
import com.hoanglam.ecommerce.dto.response.OrderItemResponeDto;
import com.hoanglam.ecommerce.dto.response.OrderResponseDto;
import com.hoanglam.ecommerce.entites.*;
import com.hoanglam.ecommerce.exception.ResourceNotFoundException;
import com.hoanglam.ecommerce.repository.ProductRepository;
import com.hoanglam.ecommerce.repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class OrderItemMapper {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SizeRepository sizeRepository;

    public OrderItem mapOrderItemRequestDtoToEntity(OrderItemRequestDto cDto) {
        Optional<Product> product = productRepository.findById(cDto.getProductId());
        Optional<Size> size = sizeRepository.findById(cDto.getSizeId());

        if (product.isEmpty()) {
            throw new ResourceNotFoundException("Product not exist with id: " + cDto.getProductId());
        }
        if (size.isEmpty()) {
            throw new ResourceNotFoundException("Size not exist with id: " + cDto.getSizeId());
        }

        return OrderItem.builder()
                .quantity(cDto.getQuantity())
                .productId(product.get())
                .sizeId(size.get())
                .build();
    }

    public Collection<OrderItemResponeDto> mapEntitysToResponseDtos(Collection<OrderItem> orderItems) {
        List<OrderItemResponeDto> orderItemResponeDtos = new ArrayList<>();
        for (OrderItem o : orderItems) {
            orderItemResponeDtos.add(OrderItemResponeDto.builder()
                    .quantity(o.getQuantity())
                    .productName(o.getProductId().getTitle())
                    .sizeName(o.getSizeId().getSizeName())
                    .build());
        }
        return orderItemResponeDtos;
    }
}
