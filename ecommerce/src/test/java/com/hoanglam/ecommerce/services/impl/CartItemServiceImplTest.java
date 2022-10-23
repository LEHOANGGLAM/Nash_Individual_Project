package com.hoanglam.ecommerce.services.impl;

import com.hoanglam.ecommerce.dto.request.CartItemRequestDto;
import com.hoanglam.ecommerce.dto.response.CartItemResponseDto;
import com.hoanglam.ecommerce.entites.*;
import com.hoanglam.ecommerce.mappers.CartItemMapper;
import com.hoanglam.ecommerce.repository.CartItemRepository;
import com.hoanglam.ecommerce.repository.ProductRepository;
import com.hoanglam.ecommerce.repository.SizeRepository;
import com.hoanglam.ecommerce.repository.UserRepository;
import com.hoanglam.ecommerce.service.ProductService;
import com.hoanglam.ecommerce.service.impl.CartItemServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class CartItemServiceImplTest {

    CartItemServiceImpl cartItemServiceImpl;
    AutoCloseable autoCloseable;

    CartItem findedCartItem;
    CartItem savedCartItem;
    CartItemResponseDto expectedCartItemResponseDto;
    CartItemRequestDto cartItemRequestDto;

    @Mock
    CartItemRepository cartItemRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    ProductService productService;

    @BeforeEach
    void beforeEach() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        cartItemServiceImpl = new CartItemServiceImpl(cartItemRepository, userRepository, productService);

        findedCartItem = CartItem.builder().id("1")
                .quantity((short) 1)
                .productId(Product.builder().id("1").build())
                .sizeId(Size.builder().id("2").build())
                .userId(User.builder().id("2").build()).build();

        savedCartItem = findedCartItem.toBuilder().quantity((short) 1)
                .productId(Product.builder().id("1").build())
                .sizeId(Size.builder().id("2").build())
                .userId(User.builder().id("2").build()).build();

        when(cartItemRepository.findById("1")).thenReturn(Optional.of(findedCartItem));
        when(cartItemRepository.save(any())).thenReturn(expectedCartItemResponseDto);

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
       // CartItemResponseDto cartItemResponseDto = cartItemServiceImpl.createCartItem(cartItemRequestDto);

//        ArgumentCaptor<CartItem> cartItemArgumentCaptor = ArgumentCaptor.forClass(CartItem.class);
//
//        verify(cartItemRepository).save(cartItemArgumentCaptor.capture());
//        verify(initialCartItem).setQuantity((short) 1);

        //  CartItem c = cartItemArgumentCaptor.getValue();

        //assertThat(c.getQuantity(), is(expectedCartItem.getQuantity()));
        //assertThat(cartItemResponseDto.getQuantity(), is(1));
        System.out.println(savedCartItem.getId());
    }

}
