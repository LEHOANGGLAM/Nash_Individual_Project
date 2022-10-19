package com.hoanglam.ecommerce.controller;

import com.hoanglam.ecommerce.dto.request.CartItemRequestDto;
import com.hoanglam.ecommerce.dto.response.CartItemResponseDto;
import com.hoanglam.ecommerce.dto.response.DeleteResponseDto;
import com.hoanglam.ecommerce.dto.response.SuccessResponse;
import com.hoanglam.ecommerce.entites.CartItem;
import com.hoanglam.ecommerce.entites.Category;
import com.hoanglam.ecommerce.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/carts")
public class CartItemController {
    @Autowired
    public CartItemService cartItemService;

    @GetMapping("/user/{id}")
    public List<CartItem> getCartItemByUserId(@PathVariable String id) {
        return cartItemService.getCartItemByUserId(id);
    }
    @GetMapping("/user/{id}/count")
    public int countCartItemByUserId(@PathVariable String id) {
        return cartItemService.countCartItemByUserId(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public CartItemResponseDto createCartItem(@Valid @RequestBody CartItemRequestDto cartItemRequestDto){
        return cartItemService.createCartItem(cartItemRequestDto);
    }

    @PutMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public CartItemResponseDto updateCartItem(@Valid @RequestBody CartItemRequestDto cartItemRequestDto){
        return cartItemService.updateCartItem(cartItemRequestDto);
    }

    @DeleteMapping("/{id}")
    public DeleteResponseDto deleteCartItem(@PathVariable String id) {
        return this.cartItemService.deleteCartItem(id);
    }
}
