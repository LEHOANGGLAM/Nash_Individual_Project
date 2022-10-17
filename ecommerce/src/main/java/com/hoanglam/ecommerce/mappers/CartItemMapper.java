package com.hoanglam.ecommerce.mappers;


import com.hoanglam.ecommerce.dto.request.CartItemRequestDto;
import com.hoanglam.ecommerce.dto.request.RatingRequestDto;
import com.hoanglam.ecommerce.entites.*;
import com.hoanglam.ecommerce.exception.ResourceNotFoundException;
import com.hoanglam.ecommerce.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CartItemMapper {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SizeRepository sizeRepository;

    public CartItem mapCartItemRequestDtoToEntity(CartItemRequestDto cDto) {
        //Optional<Cart> cart = cartRepository.findById(cDto.getCardId());
        Optional<Product> product = productRepository.findById(cDto.getProductId());
        Optional<Size> size = sizeRepository.findById(cDto.getSizeId());


        if (product.isEmpty()) {
            throw new ResourceNotFoundException("Product not exist with id: " + cDto.getProductId());
        }
        if (size.isEmpty()) {
            throw new ResourceNotFoundException("Size not exist with id: " + cDto.getSizeId());
        }

        return CartItem.builder()
                .id(cDto.getId())
                .active(cDto.isActive())
                .quantity(cDto.getQuantity())
                .productId(product.get())
                .sizeId(size.get())
                .build();
    }
}
