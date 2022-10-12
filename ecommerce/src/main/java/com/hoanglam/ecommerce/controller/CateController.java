package com.hoanglam.ecommerce.controller;

import com.hoanglam.ecommerce.entites.Category;
import com.hoanglam.ecommerce.entites.User;
import com.hoanglam.ecommerce.exception.ResourceNotFoundException;
import com.hoanglam.ecommerce.repository.CategoryRepository;
import com.hoanglam.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("")
public class CateController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }


}
