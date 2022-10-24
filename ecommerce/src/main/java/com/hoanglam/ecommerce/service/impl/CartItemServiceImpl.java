package com.hoanglam.ecommerce.service.impl;

import com.hoanglam.ecommerce.dto.request.CartItemRequestDto;
import com.hoanglam.ecommerce.dto.response.entities.CartItemResponseDto;
import com.hoanglam.ecommerce.dto.response.DeleteResponseDto;
import com.hoanglam.ecommerce.entites.*;
import com.hoanglam.ecommerce.exception.MessageException;
import com.hoanglam.ecommerce.exception.ResourceAlreadyExistException;
import com.hoanglam.ecommerce.exception.ResourceNotFoundException;
import com.hoanglam.ecommerce.mappers.CartItemMapper;
import com.hoanglam.ecommerce.repository.CartItemRepository;
import com.hoanglam.ecommerce.repository.ProductRepository;
import com.hoanglam.ecommerce.repository.UserRepository;
import com.hoanglam.ecommerce.service.CartItemService;
import com.hoanglam.ecommerce.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CartItemMapper cartItemMapper;

    public CartItemServiceImpl(CartItemRepository cartItemRepository, UserRepository userRepository,
                               ProductRepository productRepository, CartItemMapper cartItemMapper,
                               ModelMapper modelMapper) {
        super();
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.cartItemMapper = cartItemMapper;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CartItem> getCartItemByUserId(String id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException("User not exist with id: " + id);
        }

        List<CartItem> list = cartItemRepository.findByUserId(userOptional.get());
        return list;
    }

    @Override
    public int countCartItemByUserId(String id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException("User not exist with id: " + id);
        }

        int numberItem = cartItemRepository.countByUserId(userOptional.get());
        return numberItem;
    }

    @Override
    public CartItemResponseDto createCartItem(CartItemRequestDto cartItemDto) {
        //Check quantity
        Product pro = productRepository.findById(cartItemDto.getProductId()).orElseThrow(() ->
                new ResourceNotFoundException("Product not exist with id: " + cartItemDto.getProductId()));
        if (pro.getQuantity() < cartItemDto.getQuantity()) {
            throw new MessageException("Not enough quantity ");
        }

        //When in cart already exists  the same product and size
        List<CartItem> cartItemList = getCartItemByUserId(cartItemDto.getUserId());
        CartItem cartItem = cartItemList.stream().filter(c -> (c.getProductId().getId().equals(cartItemDto.getProductId()) && c.getSizeId().getId().equals(cartItemDto.getSizeId())))
                .findFirst().orElse(null);
        if (cartItem != null) {
            throw new ResourceAlreadyExistException("This product already exists in your cart");

            //The second way is update quantity and price
//            cartItem.setQuantity((short) (cartItem.getQuantity() + cartItemDto.getQuantity()));
//            cartItem.setPrice(cartItem.getProductId().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
//            cartItem = cartItemRepository.save(cartItem);
//
//            CartItemResponseDto cartItemResponseDto = modelMapper.map(cartItem, CartItemResponseDto.class);
//            return cartItemResponseDto;
        }

        //When in cart does not exist the same product and size
        UUID uuid = UUID.randomUUID();
        cartItem = cartItemMapper.mapCartItemRequestDtoToEntity(cartItemDto);
        cartItem.setId(uuid.toString());
        cartItem.setActive(true);
        cartItem.setCreatedAt(new Date());
        cartItem.setPrice(pro.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
        cartItemRepository.save(cartItem);

        CartItemResponseDto cartItemResponseDto = modelMapper.map(cartItem, CartItemResponseDto.class);
        return cartItemResponseDto;
    }

    @Override
    public CartItemResponseDto updateCartItem(CartItemRequestDto cartItemDto) {
        List<CartItem> cartItemList = getCartItemByUserId(cartItemDto.getUserId());
        CartItem cartItem = cartItemList.stream().filter(c -> (c.getProductId().getId().equals(cartItemDto.getProductId()) && c.getSizeId().getId().equals(cartItemDto.getSizeId())))
                .findFirst().orElse(null);
        if (cartItem == null) {
            throw new ResourceNotFoundException("Item not exist in cart");
        }

        cartItem.setQuantity(cartItemDto.getQuantity());
        cartItem.setPrice(cartItem.getProductId().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
        cartItemRepository.save(cartItem);

        CartItemResponseDto cartItemResponseDto = modelMapper.map(cartItem, CartItemResponseDto.class);
        return cartItemResponseDto;
    }


    @Override
    public DeleteResponseDto deleteCartItem(String id) {
        if (cartItemRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("CartItem not exist with id: " + id);
        }
        cartItemRepository.deleteById(id);
        return new DeleteResponseDto("Delete Item successfully!", HttpStatus.OK.value(), HttpStatus.OK);
    }
}
