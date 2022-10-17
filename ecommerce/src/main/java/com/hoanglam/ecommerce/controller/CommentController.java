package com.hoanglam.ecommerce.controller;

import com.hoanglam.ecommerce.dto.request.RatingRequestDto;
import com.hoanglam.ecommerce.entites.Rating;
import com.hoanglam.ecommerce.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("")
public class CommentController {
    @Autowired
    public RatingService ratingService;

    @PostMapping("/ratings")
    @ResponseStatus(HttpStatus.CREATED)
    public Rating createComment(@Valid @RequestBody RatingRequestDto c){
        return ratingService.createRating(c);
    }
}
