package com.hoanglam.ecommerce.service;

import com.hoanglam.ecommerce.dto.request.CartItemRequestDto;
import com.hoanglam.ecommerce.entites.Cart;
import com.hoanglam.ecommerce.entites.CartItem;
import com.hoanglam.ecommerce.entites.Product;
import com.hoanglam.ecommerce.entites.User;

public interface CartService {
    Cart getCartById(String id);
    Cart getCardByUserId(String id);
    Cart getCardByCartItemId(String id);

    CartItem createOrUpdateCartItem(CartItemRequestDto p);
}
