package com.hoanglam.ecommerce.service;

import com.hoanglam.ecommerce.dto.request.CartItemRequestDto;
import com.hoanglam.ecommerce.entites.CartItem;
import com.hoanglam.ecommerce.entites.Product;
import com.hoanglam.ecommerce.entites.User;

public interface CartItemService {

    CartItem createOrUpdateCartItem(CartItemRequestDto p);
    boolean deleteCartItem(String id);
}
