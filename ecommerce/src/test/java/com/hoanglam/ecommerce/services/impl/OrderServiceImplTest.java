package com.hoanglam.ecommerce.services.impl;

import com.hoanglam.ecommerce.dto.request.CartItemRequestDto;
import com.hoanglam.ecommerce.dto.request.OrderItemRequestDto;
import com.hoanglam.ecommerce.dto.request.OrderResquestDto;
import com.hoanglam.ecommerce.dto.response.entities.CartItemResponseDto;
import com.hoanglam.ecommerce.dto.response.entities.OrderResponseDto;
import com.hoanglam.ecommerce.entites.*;
import com.hoanglam.ecommerce.exception.MessageException;
import com.hoanglam.ecommerce.mappers.CartItemMapper;
import com.hoanglam.ecommerce.mappers.OrderItemMapper;
import com.hoanglam.ecommerce.mappers.OrderMapper;
import com.hoanglam.ecommerce.repository.*;
import com.hoanglam.ecommerce.service.CartItemService;
import com.hoanglam.ecommerce.service.impl.CartItemServiceImpl;
import com.hoanglam.ecommerce.service.impl.OrderServiceImpl;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class OrderServiceImplTest {
    OrderServiceImpl orderServiceImpl;
    AutoCloseable autoCloseable;

    Order savedOrder;
    OrderItem savedOrderItem;
    OrderResponseDto expectedOrderResDto;
    OrderResquestDto orderResquestDto;
    OrderItemRequestDto orderItemRequestDto;
    List<OrderItemRequestDto> orderItems = new ArrayList<>();
    Product product;
    User user;

    @Mock
    OrderMapper orderMapper;
    @Mock
    OrderItemMapper orderItemMapper;
    @Mock
    ModelMapper modelMapper;

    @Mock
    OrderItemRepository orderItemRepository;
    @Mock
    CartItemRepository cartItemRepository;
    @Mock
    ProductRepository productRepository;
    @Mock
    OrderRepository orderRepository;
    @Mock
    UserRepository userRepository;

    @Mock
    CartItemService cartItemService;

    @BeforeEach
    void beforeEach() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        orderServiceImpl = new OrderServiceImpl(orderRepository, orderItemRepository, cartItemRepository,
                productRepository, userRepository, orderMapper , orderItemMapper, modelMapper);

        product = Product.builder().id("1").quantity((short) 100).price(BigDecimal.valueOf(100)).title("test").build();
        user = User.builder().id("2").build();

        when(orderRepository.save(any())).thenReturn(savedOrder);
        when(productRepository.findById("1")).thenReturn(Optional.of(product));
        when(userRepository.findById("2")).thenReturn(Optional.of(user));

        orderItemRequestDto = OrderItemRequestDto.builder().quantity((short) 1)
                .productId("1")
                .sizeId("2").build();

        orderItems.add(orderItemRequestDto);
        orderResquestDto = OrderResquestDto.builder()
                .userId("2")
                .address("test")
                .paymentMethod("cod")
                .orderItems(orderItems).build();

    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    public void createOrder_ShouldThrowException_WhenQuantityNotEnough() {
        orderItemRequestDto.setQuantity((short) 101);
        MessageException messageException = Assertions.assertThrows(MessageException.class,
                () -> orderServiceImpl.createOrder(orderResquestDto, "2"));

        Assertions.assertEquals("Not enough quantity with Item: " + product.getTitle(), messageException.getMessage());
    }

    @Test
    public void createOrder_ShouldReturnValue_WhenDataValid() {
        savedOrder = mock(Order.class);
        expectedOrderResDto = mock(OrderResponseDto.class);
        savedOrderItem = OrderItem.builder().quantity((short) 1).productId(product).orderId(savedOrder).build();

        when(orderMapper.mapOrderRequestDtoToEntity(orderResquestDto)).thenReturn(savedOrder);
        when(orderItemMapper.mapOrderItemRequestDtoToEntity(any())).thenReturn(savedOrderItem);
        when(orderServiceImpl.addOrderItemIntoOrder(savedOrder, orderResquestDto.getOrderItems())).thenReturn(savedOrder);
        when(modelMapper.map(savedOrder, OrderResponseDto.class)).thenReturn(expectedOrderResDto);

        OrderResponseDto result = orderServiceImpl.createOrder(orderResquestDto, "2");

        verify(savedOrder).setStatus((short) 0);
        verify(savedOrder).setTotal(BigDecimal.valueOf(0));
        assertThat(result, is(expectedOrderResDto));
    }

//    @Test
//    public void getOrderTotalPrice_ShouldReturnValue_WhenDataValid() {
//        savedOrderItem = OrderItem.builder().productId(product).quantity((short) 2).price(BigDecimal.valueOf(100)).build();
//        Collection<OrderItem> orderItems = new HashSet<>();
//        orderItems.add(savedOrderItem);
//        savedOrder = Order.builder().orderItemCollection( orderItems).build();
//
//        BigDecimal result = orderServiceImpl.getOrderTotalPrice(savedOrder.getOrderItemCollection());
//
//        assertThat(result, is(savedOrderItem.getPrice()));
//    }
}
