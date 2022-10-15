package com.hoanglam.ecommerce.controller;

import com.hoanglam.ecommerce.entites.Cart;
import com.hoanglam.ecommerce.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("")
public class CartController {
    @Autowired
    public CartRepository cartRepository;

    @GetMapping("/cart/{id}")
    public String getCard(@PathVariable String id){
//        Optional<Cart> cart = cartRepository.findById(id);
//        if(cart.isEmpty()){
//            cart.get() = new Cart();
//        }
       return "Public content";
    }
}
