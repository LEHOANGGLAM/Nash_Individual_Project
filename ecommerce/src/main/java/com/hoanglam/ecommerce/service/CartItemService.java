package com.hoanglam.ecommerce.service;

import com.hoanglam.ecommerce.dto.request.CartItemRequestDto;
import com.hoanglam.ecommerce.dto.response.CartItemResponseDto;
import com.hoanglam.ecommerce.dto.response.DeleteResponseDto;
import com.hoanglam.ecommerce.entites.CartItem;
import com.hoanglam.ecommerce.entites.Product;
import com.hoanglam.ecommerce.entites.User;

import java.util.List;

public interface CartItemService {

    CartItemResponseDto createCartItem(CartItemRequestDto c);
    CartItemResponseDto updateCartItem(CartItemRequestDto c);

    DeleteResponseDto deleteCartItem(String id);

    List<CartItem> getCartItemByUserId(String id);

    int countCartItemByUserId(String id);
}
