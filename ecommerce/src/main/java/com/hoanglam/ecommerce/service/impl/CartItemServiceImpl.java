package com.hoanglam.ecommerce.service.impl;

import com.hoanglam.ecommerce.config.jwt.AuthTokenFilter;
import com.hoanglam.ecommerce.config.jwt.JwtUtils;
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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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

        List<CartItem> list = cartItemRepository.findByUserIdOrderByProductId(userOptional.get());
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
    public CartItemResponseDto createCartItem(CartItem cartItem, String userId) {
        //Check quantity
        Product product = productRepository.findById(cartItem.getProductId().getId()).orElseThrow(() ->
                new ResourceNotFoundException("Product not exist with id: " + cartItem.getProductId().getId()));
        if (product.getQuantity() < cartItem.getQuantity()) {
            throw new MessageException("Not enough quantity ");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with id: " + userId));
        //When in cart already exists  the same product and size
        Optional<CartItem> optionalCartItem =
                cartItemRepository.findByUserIdAndProductIdAndSizeId(user, product, cartItem.getSizeId());
        if (optionalCartItem.isPresent()) {
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
        //cartItem = cartItemMapper.mapCartItemRequestDtoToEntity(cartItemDto);
        cartItem.setId(uuid.toString());
        cartItem.setActive(true);
        cartItem.setCreatedAt(new Date());
        cartItem.setUserId(user);
        cartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
        cartItemRepository.save(cartItem);

        CartItemResponseDto cartItemResponseDto = modelMapper.map(cartItem, CartItemResponseDto.class);
        return cartItemResponseDto;
    }

    @Override
    public CartItemResponseDto updateCartItem(String cartId, CartItem cartItem) {
        User user = userRepository.findById(cartItem.getUserId().getId())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with id: " + cartItem.getUserId().getId()));

        Optional<CartItem> optionalCartItem =
                cartItemRepository.findById(cartId);
        if (optionalCartItem.isEmpty()) {
            throw new ResourceNotFoundException("This product not exists in your cart");
        }

        Product product = productRepository.findById(cartItem.getProductId().getId()).orElseThrow(() ->
                new ResourceNotFoundException("Product not exist "));
        if (product.getQuantity() < cartItem.getQuantity()) {
            throw new MessageException("Not enough quantity ");
        }

        cartItem.setId(optionalCartItem.get().getId());
        cartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
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
