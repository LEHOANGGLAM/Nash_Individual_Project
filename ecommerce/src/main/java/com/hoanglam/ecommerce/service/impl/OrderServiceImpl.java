package com.hoanglam.ecommerce.service.impl;

import com.hoanglam.ecommerce.dto.request.OrderItemRequestDto;
import com.hoanglam.ecommerce.dto.request.OrderResquestDto;
import com.hoanglam.ecommerce.dto.response.entities.OrderResponseDto;
import com.hoanglam.ecommerce.entites.CartItem;
import com.hoanglam.ecommerce.entites.Order;
import com.hoanglam.ecommerce.entites.OrderItem;
import com.hoanglam.ecommerce.entites.Product;
import com.hoanglam.ecommerce.exception.MessageException;
import com.hoanglam.ecommerce.exception.ResourceNotFoundException;
import com.hoanglam.ecommerce.mappers.CartItemMapper;
import com.hoanglam.ecommerce.mappers.OrderItemMapper;
import com.hoanglam.ecommerce.mappers.OrderMapper;
import com.hoanglam.ecommerce.repository.*;
import com.hoanglam.ecommerce.service.CartItemService;
import com.hoanglam.ecommerce.service.OrderService;
import com.hoanglam.ecommerce.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ModelMapper modelMapper;


    public OrderServiceImpl(OrderRepository orderRepository ,OrderItemRepository orderItemRepository,
                            CartItemRepository cartItemRepository, ProductRepository productRepository,
                            OrderMapper orderMapper, OrderItemMapper orderItemMapper, ModelMapper modelMapper) {
        super();
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.orderMapper = orderMapper;
        this.orderItemMapper = orderItemMapper;
        this.modelMapper = modelMapper;
    }

    @Override
    public OrderResponseDto createOrder(OrderResquestDto orderResquestDto) {
        //Check quantity
        for (OrderItemRequestDto o : orderResquestDto.getOrderItems()) {
            Product product = productRepository.findById(o.getProductId()).orElseThrow(() ->
                    new ResourceNotFoundException("Product not exist with id: " + o.getProductId()));
            if (product.getQuantity() < o.getQuantity()) {
                throw new MessageException("Not enough quantity with Item: " + product.getTitle());
            }
        }

        //Create Order
        UUID uuid = UUID.randomUUID();
        Order order = orderMapper.mapOrderRequestDtoToEntity(orderResquestDto);
        order.setId(uuid.toString());
        order.setCreatedAt(new Date());
        order.setStatus((short) 0);
        order.setTotal(BigDecimal.valueOf(0));

        //save Order for OrderItem can getId from Order
        orderRepository.save(order);

        //add OrderItem into Order
        order = addOrderItemIntoOrder(order, orderResquestDto.getOrderItems());

        //delete CartItem When Order Product Exist In Cart
        deleteCartItemWhenOrderProductExistInCart(order.getOrderItemCollection());

        //Set Output Response
        OrderResponseDto orderResponseDto = modelMapper.map(order, OrderResponseDto.class);
        return orderResponseDto;
    }

    public Order addOrderItemIntoOrder(Order order, List<OrderItemRequestDto> orderItemRequestDtos) {
        Collection<OrderItem> orderItems = new HashSet<>();
        UUID uuid;

        for (OrderItemRequestDto o : orderItemRequestDtos) {
            uuid = UUID.randomUUID();

            OrderItem orderItem = orderItemMapper.mapOrderItemRequestDtoToEntity(o);
            orderItem.setId(uuid.toString());
            orderItem.setCreatedAt(new Date());
            orderItem.setPrice(orderItem.getProductId().getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity())));
            orderItem.setOrderId(order);

            //add OrderItem into database
            orderItemRepository.save(orderItem);
            orderItems.add(orderItem);
        }

        //set TotalPrice,numberItem for Order
        order.setTotal(getOrderTotalPrice(orderItems));
        order.setNumberItem(orderItems.size());
        order.setOrderItemCollection(orderItems);

        order = orderRepository.save(order);
        return order;
    }

    public BigDecimal getOrderTotalPrice(Collection<OrderItem> orderItems) {
        BigDecimal totalPrice = new BigDecimal(0);

        for (OrderItem orderItem : orderItems) {
            totalPrice = totalPrice.add(orderItem.getPrice());

            //Set Quantity And NumberSold after Oder
//            orderItem.getProductId().setNumberSold(orderItem.getProductId().getNumberSold() + orderItem.getQuantity());
//            orderItem.getProductId().setQuantity((short) (orderItem.getProductId().getQuantity() - orderItem.getQuantity()));
//            productRepository.save(orderItem.getProductId());
        }

        return totalPrice;
    }

    public void deleteCartItemWhenOrderProductExistInCart(Collection<OrderItem> orderItems) {
        for (OrderItem o : orderItems) {
            Optional<CartItem> cartItemOptional = cartItemRepository.findByUserIdAndProductIdAndSizeId
                    (o.getOrderId().getUserId(), o.getProductId(),o.getSizeId());
            if (cartItemOptional.isPresent()) {
                cartItemService.deleteCartItem(cartItemOptional.get().getId());
            }
        }
    }
}
