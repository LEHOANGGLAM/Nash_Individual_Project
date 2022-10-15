package com.hoanglam.ecommerce.service;

import com.hoanglam.ecommerce.entites.Cart;
import com.hoanglam.ecommerce.entites.Product;

public interface CartService {
    Cart getCartById(String id);
}
