package com.hoanglam.ecommerce.service.impl;

import com.hoanglam.ecommerce.dto.request.CartItemRequestDto;
import com.hoanglam.ecommerce.entites.*;
import com.hoanglam.ecommerce.exception.ResourceAlreadyExistException;
import com.hoanglam.ecommerce.exception.ResourceNotFoundException;
import com.hoanglam.ecommerce.mappers.CartItemMapper;
import com.hoanglam.ecommerce.mappers.RatingMapper;
import com.hoanglam.ecommerce.repository.CartItemRepository;
import com.hoanglam.ecommerce.repository.CartRepository;
import com.hoanglam.ecommerce.repository.UserRepository;
import com.hoanglam.ecommerce.service.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    private CartItemMapper cartItemMapper;


    @Override
    public Cart getCartById(String id) {
        Cart cart = cartRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Cart not exist with id: " + id));
        return cart;
    }

    @Override
    public Cart getCardByUserId(String id) {
        Optional<User> optionalUser = userRepository.findById(id);
        Optional<Cart> optionalCart = cartRepository.findByUserId(optionalUser.get());
        if (optionalCart.isEmpty()) {
            UUID uuid = UUID.randomUUID();
            Cart cart = new Cart(uuid.toString());

            cart.setUserId(userRepository.findById(id).get());
            cartRepository.save(cart);
            return cart;
        }
        return optionalCart.get();
    }

    @Override
    public Cart getCardByCartItemId(String id) {
        Optional<Cart> optionalCart = cartRepository.findByCartItemCollection_Id(id);
        if (optionalCart.isEmpty()) {
            UUID uuid = UUID.randomUUID();
            Cart cart = new Cart(uuid.toString());

            cartRepository.save(cart);
            return cart;
        }
        return optionalCart.get();
    }


    //------------------Cart Item below-----------
    //------------------Cart Item below-----------
    @Override
    public CartItem createOrUpdateCartItem(CartItemRequestDto cartItemDto) {
        //Get getCardByUserId (If first add Item into Cart then create Cart by this method)
        Cart cart = getCardByUserId(cartItemDto.getUserId());

        CartItem cartItem = cartItemMapper.mapCartItemRequestDtoToEntity(cartItemDto);

        cartItem.setCartId(cart);
        cartItem.setCreatedAt(new Date());
        cartItem.setPrice(cartItem.getProductId().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
        cartItem = cartItemRepository.save(cartItem);

        return cartItem;
    }
    //------------------Cart Item above-----------
    //------------------Cart Item above-----------

}
