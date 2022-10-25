package com.hoanglam.ecommerce.service;

import com.hoanglam.ecommerce.dto.request.RatingRequestDto;
import com.hoanglam.ecommerce.entites.Rating;

import java.util.List;

public interface RatingService {
    Rating createRating(RatingRequestDto c);
    List<Rating> getRatingsByProductId(String proId);
}
