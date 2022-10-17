package com.hoanglam.ecommerce.service.impl;

import com.hoanglam.ecommerce.dto.request.RatingRequestDto;
import com.hoanglam.ecommerce.dto.response.ErrorResponse;
import com.hoanglam.ecommerce.entites.Rating;
import com.hoanglam.ecommerce.entites.Product;
import com.hoanglam.ecommerce.exception.ResourceAlreadyExistException;
import com.hoanglam.ecommerce.exception.ResourceNotFoundException;
import com.hoanglam.ecommerce.mappers.RatingMapper;
import com.hoanglam.ecommerce.repository.RatingRepository;
import com.hoanglam.ecommerce.repository.ProductRepository;
import com.hoanglam.ecommerce.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingMapper modelMapper;
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Rating createRating(RatingRequestDto c) {
        UUID uuid = UUID.randomUUID();
        Rating rating = modelMapper.mapRequestDtoToEntity(c);

        List<Rating> ratinglist = ratingRepository.findByUserId(rating.getUserId());
        for(Rating r: ratinglist){
            if(r.getOrderItem() == rating.getOrderItem()){
                throw new ResourceAlreadyExistException("User already rating this product");
            }
        }

        rating.setId(uuid.toString());
        rating.setCreatedDate(new Date());
        Rating savedRating = ratingRepository.save(rating);

        averageRating(savedRating.getOrderItem().getProductId(), savedRating.getRating());
        return savedRating;
    }

    private float averageRating(Product product, int rating) {
        float average = (product.getAverageRating() + rating) / 2;
        product.setAverageRating(average);

        productRepository.save(product);
        return average;
    }
}
