package com.hoanglam.ecommerce.service.impl;

import com.hoanglam.ecommerce.dto.request.CommentRequestDto;
import com.hoanglam.ecommerce.entites.Comment;
import com.hoanglam.ecommerce.entites.Product;
import com.hoanglam.ecommerce.exception.ResourceNotFoundException;
import com.hoanglam.ecommerce.mappers.CommentMapper;
import com.hoanglam.ecommerce.repository.CommentRepository;
import com.hoanglam.ecommerce.repository.ProductRepository;
import com.hoanglam.ecommerce.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper modelMapper;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Comment createComment(CommentRequestDto c) {
        UUID uuid = UUID.randomUUID();
        Comment comment = modelMapper.mapRequestDtoToEntity(c);

        comment.setId(uuid.toString());
        Comment savedComment = commentRepository.save(comment);

        averageRating(c.getProductId(), c.getRating());
        return savedComment;
    }

    private float averageRating(String proId, int rating) {
        Product product = productRepository.findById(proId).orElseThrow(() ->
                new ResourceNotFoundException("Product not exist with id: " + proId));

        float average = (product.getAverageRating() + rating) / 2;
        product.setAverageRating(average);
        productRepository.save(product);
        return average;
    }
}
