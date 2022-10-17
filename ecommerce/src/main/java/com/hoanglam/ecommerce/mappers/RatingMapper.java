package com.hoanglam.ecommerce.mappers;

import com.hoanglam.ecommerce.dto.request.RatingRequestDto;
import com.hoanglam.ecommerce.entites.OrderItem;
import com.hoanglam.ecommerce.entites.Rating;
import com.hoanglam.ecommerce.entites.User;
import com.hoanglam.ecommerce.exception.ResourceNotFoundException;
import com.hoanglam.ecommerce.repository.OrderItemRepository;
import com.hoanglam.ecommerce.repository.ProductRepository;
import com.hoanglam.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RatingMapper {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    public Rating mapRatingRequestDtoToEntity(RatingRequestDto cDto) {
        Optional<OrderItem> orderItem = orderItemRepository.findById(cDto.getOrderItemId());
        Optional<User> user = userRepository.findById(cDto.getUserId());

        if(orderItem.isEmpty()){
            throw new ResourceNotFoundException("OderItem not exist with id: "+ cDto.getOrderItemId());
        }
        if(user.isEmpty()){
            throw new ResourceNotFoundException("User not exist with id: "+ cDto.getUserId());
        }

        return Rating.builder()
                .userId(user.get())
                .orderItem(orderItem.get())
                .content(cDto.getContent())
                .rating(cDto.getRating())
                .build();
    }
}
