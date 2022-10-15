package com.hoanglam.ecommerce.service.impl;

import com.hoanglam.ecommerce.entites.Cart;
import com.hoanglam.ecommerce.entites.Product;
import com.hoanglam.ecommerce.exception.ResourceNotFoundException;
import com.hoanglam.ecommerce.repository.CartRepository;
import com.hoanglam.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart getCartById(String id) {
        Cart cart = cartRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Cart not exist with id: " + id));
        return cart;
    }
}
