package com.hoanglam.ecommerce.controller;

import com.hoanglam.ecommerce.dto.request.RatingRequestDto;
import com.hoanglam.ecommerce.entites.Rating;
import com.hoanglam.ecommerce.entites.Size;
import com.hoanglam.ecommerce.service.RatingService;
import com.hoanglam.ecommerce.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/sizes")
public class SizeController {
    @Autowired
    public SizeService service;

    @GetMapping("")
    public List<Size> getAllSizes(){
        return service.gettAllSizes();
    }



}
