package com.hoanglam.ecommerce.service;

import com.hoanglam.ecommerce.dto.request.CartItemRequestDto;
import com.hoanglam.ecommerce.dto.response.entities.CartItemResponseDto;
import com.hoanglam.ecommerce.dto.response.DeleteResponseDto;
import com.hoanglam.ecommerce.entites.CartItem;

import java.util.List;

public interface CartItemService {

    CartItemResponseDto createCartItem(CartItem c, String userId);
    CartItemResponseDto updateCartItem(String cartId, CartItem c);

    DeleteResponseDto deleteCartItem(String id);

    List<CartItem> getCartItemByUserId(String id);

    int countCartItemByUserId(String id);
}
