package com.hoanglam.ecommerce.services.impl;

import com.hoanglam.ecommerce.dto.request.CartItemRequestDto;
import com.hoanglam.ecommerce.dto.response.entities.CartItemResponseDto;
import com.hoanglam.ecommerce.dto.response.entities.ProductResponseDto;
import com.hoanglam.ecommerce.entites.*;
import com.hoanglam.ecommerce.exception.MessageException;
import com.hoanglam.ecommerce.exception.ResourceAlreadyExistException;
import com.hoanglam.ecommerce.exception.ResourceNotFoundException;
import com.hoanglam.ecommerce.mappers.CartItemMapper;
import com.hoanglam.ecommerce.repository.CartItemRepository;
import com.hoanglam.ecommerce.repository.ProductRepository;
import com.hoanglam.ecommerce.repository.UserRepository;
import com.hoanglam.ecommerce.service.ProductService;
import com.hoanglam.ecommerce.service.impl.CartItemServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class CartItemServiceImplTest {

    CartItemServiceImpl cartItemServiceImpl;
    AutoCloseable autoCloseable;

    CartItem savedCartItem;
    CartItemResponseDto expectedCartItemResponseDto;
    CartItemRequestDto cartItemRequestDto;
    Product product;
    User user;

    List<CartItem> cartItems = new ArrayList<>();
    CartItem cartItem;

    @Mock
    CartItemRepository cartItemRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    ProductRepository productRepository;
    @Mock
    CartItemMapper cartItemMapper;
    @Mock
    ModelMapper modelMapper;

    @BeforeEach
    void beforeEach() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        cartItemServiceImpl = new CartItemServiceImpl(cartItemRepository, userRepository, productRepository, cartItemMapper, modelMapper);
        product = Product.builder().id("1").quantity((short) 100).price(BigDecimal.valueOf(100)).build();
        user = User.builder().id("2").build();

        savedCartItem =  CartItem.builder().id("1").quantity((short) 1)
                .productId(product)
                .sizeId(Size.builder().id("2").build())
                .userId(user).build();

        when(cartItemRepository.save(any())).thenReturn(savedCartItem);
        when(productRepository.findById("1")).thenReturn(Optional.of(product));
        when(userRepository.findById("2")).thenReturn(Optional.of(user));


        //init input
        cartItemRequestDto = CartItemRequestDto.builder().quantity((short) 1)
                .productId("1")
                .sizeId("2")
                .userId("2").build();
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    public void createCartItem_ShouldThrowException_WhenQuantityNotEnough() {
        cartItemRequestDto.setQuantity((short) 101);
        MessageException messageException = Assertions.assertThrows(MessageException.class,
                () -> cartItemServiceImpl.createCartItem(cartItemRequestDto));

        Assertions.assertEquals("Not enough quantity ", messageException.getMessage());
    }

    @Test
    public void createCartItem_ShouldReturnValue_WhenCartItemIsExists() {
        cartItem = CartItem.builder().id("1")
                .quantity((short) 1)
                .productId(product)
                .sizeId(Size.builder().id("2").build())
                .userId(user).build();
        cartItems.add(cartItem);

        when(cartItemServiceImpl.getCartItemByUserId(cartItemRequestDto.getUserId())).thenReturn(cartItems);

        ResourceAlreadyExistException messageException = Assertions.assertThrows(ResourceAlreadyExistException.class,
                () -> cartItemServiceImpl.createCartItem(cartItemRequestDto));

        Assertions.assertEquals("This product already exists in your cart", messageException.getMessage());
    }

    @Test
    public void createCartItem_ShouldReturnValue_WhenNewDataValid() {
        savedCartItem = mock(CartItem.class);
        expectedCartItemResponseDto = mock(CartItemResponseDto.class);

        when(cartItemMapper.mapCartItemRequestDtoToEntity(cartItemRequestDto)).thenReturn(savedCartItem);
        when(modelMapper.map(savedCartItem, CartItemResponseDto.class)).thenReturn(expectedCartItemResponseDto);

        CartItemResponseDto result = cartItemServiceImpl.createCartItem(cartItemRequestDto);

        verify(savedCartItem).setActive(true);
        verify(savedCartItem).setPrice(product.getPrice().multiply(BigDecimal.valueOf(savedCartItem.getQuantity())));
        assertThat(result, is(expectedCartItemResponseDto));
    }
}
