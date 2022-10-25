package com.hoanglam.ecommerce.controller;

import com.hoanglam.ecommerce.dto.response.entities.CategoryReponseDto;
import com.hoanglam.ecommerce.dto.response.DeleteResponseDto;
import com.hoanglam.ecommerce.entites.Category;
import com.hoanglam.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("")
public class CateController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return categoryService.getAllCate();
    }



    @GetMapping("/category/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public Category getCateById(@PathVariable String id) {
        return categoryService.getCateById(id);
    }

    @PostMapping("/category")
    @PreAuthorize("hasAuthority('admin')")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryReponseDto addCate(@Valid @RequestBody Category cate) {
        return this.categoryService.createCate(cate);
    }

    @PutMapping("/category/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public CategoryReponseDto updateCate(@PathVariable String id, @Valid @RequestBody Category cate) {
        return this.categoryService.updateCate(id, cate);
    }

    @DeleteMapping("/category/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public DeleteResponseDto deleteCate(@PathVariable String id) {
        return this.categoryService.softDeleteCate(id);
    }

}
