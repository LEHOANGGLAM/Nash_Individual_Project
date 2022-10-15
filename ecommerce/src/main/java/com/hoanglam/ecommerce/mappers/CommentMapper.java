package com.hoanglam.ecommerce.mappers;

import com.hoanglam.ecommerce.dto.request.CommentRequestDto;
import com.hoanglam.ecommerce.entites.Comment;
import com.hoanglam.ecommerce.entites.User;
import com.hoanglam.ecommerce.repository.ProductRepository;
import com.hoanglam.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CommentMapper {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;


    public Comment mapRequestDtoToEntity(CommentRequestDto cDto) {
        return Comment.builder()
                .userId(userRepository.findById(cDto.getUserId()).get())
                .productId(productRepository.findById(cDto.getProductId()).get())
                .content(cDto.getContent())
                .createdDate(cDto.getCreatedDate())
                .rating(cDto.getRating())
                .build();
    }
}
