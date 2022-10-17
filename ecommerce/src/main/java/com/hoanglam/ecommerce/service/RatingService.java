package com.hoanglam.ecommerce.service;

import com.hoanglam.ecommerce.dto.request.RatingRequestDto;
import com.hoanglam.ecommerce.entites.Rating;

public interface RatingService {
    Rating createRating(RatingRequestDto c);
}
