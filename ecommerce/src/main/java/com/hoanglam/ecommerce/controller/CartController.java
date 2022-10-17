package com.hoanglam.ecommerce.controller;

import com.hoanglam.ecommerce.dto.request.CartItemRequestDto;
import com.hoanglam.ecommerce.entites.Cart;
import com.hoanglam.ecommerce.entites.CartItem;
import com.hoanglam.ecommerce.entites.Product;
import com.hoanglam.ecommerce.repository.CartRepository;
import com.hoanglam.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    public CartService cartService;

    @GetMapping("/{id}")
    public Cart getCardById(@PathVariable String id){
        return cartService.getCartById(id);
    }

    @GetMapping("/user/{id}")
    public Cart getCardByUserId(@PathVariable String id){
        return cartService.getCardByUserId(id);
    }


    @PostMapping("/cart-item")
    @ResponseStatus(HttpStatus.CREATED)
    public CartItem createCartItem(@Valid @RequestBody CartItemRequestDto cartItemRequestDto){
        return cartService.createOrUpdateCartItem(cartItemRequestDto);
    }
}
