package com.hoanglam.ecommerce.controller;

import com.hoanglam.ecommerce.dto.request.CartItemRequestDto;
import com.hoanglam.ecommerce.dto.response.SuccessResponse;
import com.hoanglam.ecommerce.entites.CartItem;
import com.hoanglam.ecommerce.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cart")
public class CartItemController {
    @Autowired
    public CartItemService cartItemService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public CartItem createCartItem(@Valid @RequestBody CartItemRequestDto cartItemRequestDto){
        return cartItemService.createOrUpdateCartItem(cartItemRequestDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id) {
        this.cartItemService.deleteCartItem(id);
        return ResponseEntity.ok(new SuccessResponse("Delete cart successfully!"));
    }
}
