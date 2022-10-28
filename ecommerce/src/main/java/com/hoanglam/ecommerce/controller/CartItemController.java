package com.hoanglam.ecommerce.controller;

import com.hoanglam.ecommerce.dto.request.CartItemRequestDto;
import com.hoanglam.ecommerce.dto.response.entities.CartItemResponseDto;
import com.hoanglam.ecommerce.dto.response.DeleteResponseDto;
import com.hoanglam.ecommerce.entites.CartItem;
import com.hoanglam.ecommerce.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("")
public class CartItemController {
    @Autowired
    public CartItemService cartItemService;

    @GetMapping("/user/{id}/carts")
    public List<CartItem> getCartItemByUserId(@PathVariable String id) {
        return cartItemService.getCartItemByUserId(id);
    }
    @GetMapping("/user/{id}/carts/count")
    public int countCartItemByUserId(@PathVariable String id) {
        return cartItemService.countCartItemByUserId(id);
    }

    @PostMapping("/user/{userId}/carts")
    @ResponseStatus(HttpStatus.CREATED)
    public CartItemResponseDto createCartItem(@PathVariable String userId, @Valid @RequestBody CartItem c){
        return cartItemService.createCartItem(c, userId );
    }

    @PutMapping("/carts/{id}")
    public CartItemResponseDto updateCartItem(@PathVariable String id,@Valid @RequestBody CartItem c){
        return cartItemService.updateCartItem(id, c);
    }

    @DeleteMapping("/carts/{id}")
    public DeleteResponseDto deleteCartItem(@PathVariable String id) {
        return this.cartItemService.deleteCartItem(id);
    }
}
