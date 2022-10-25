package com.hoanglam.ecommerce.controller;

import com.hoanglam.ecommerce.dto.request.RatingRequestDto;
import com.hoanglam.ecommerce.entites.Rating;
import com.hoanglam.ecommerce.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("")
public class RatingController {
    @Autowired
    public RatingService ratingService;

    @PostMapping("/rating")
    @ResponseStatus(HttpStatus.CREATED)
    public Rating createComment(@Valid @RequestBody RatingRequestDto c){
        return ratingService.createRating(c);
    }


    @GetMapping("/ratings/product/{id}")
    public List<Rating> getRatingByProductId(@PathVariable String id){
        return ratingService.getRatingsByProductId(id);
    }
}
