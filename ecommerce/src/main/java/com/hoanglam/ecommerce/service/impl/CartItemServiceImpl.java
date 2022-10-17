package com.hoanglam.ecommerce.service.impl;

import com.hoanglam.ecommerce.dto.request.CartItemRequestDto;
import com.hoanglam.ecommerce.entites.*;
import com.hoanglam.ecommerce.exception.ResourceNotFoundException;
import com.hoanglam.ecommerce.mappers.CartItemMapper;
import com.hoanglam.ecommerce.repository.CartItemRepository;
import com.hoanglam.ecommerce.repository.UserRepository;
import com.hoanglam.ecommerce.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartItemMapper cartItemMapper;


    @Override
    public CartItem createOrUpdateCartItem(CartItemRequestDto cartItemDto) {
        CartItem cartItem = cartItemMapper.mapCartItemRequestDtoToEntity(cartItemDto);

        cartItem.setCreatedAt(new Date());
        cartItem.setPrice(cartItem.getProductId().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
        cartItem = cartItemRepository.save(cartItem);

        return cartItem;
    }

    @Override
    public boolean deleteCartItem(String id) {
        if (cartItemRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("CartItem not exist with id: " + id);
        }
        cartItemRepository.deleteById(id);
        return true;
    }

}
