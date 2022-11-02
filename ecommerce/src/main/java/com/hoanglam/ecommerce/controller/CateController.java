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
@RequestMapping("/categories")
public class CateController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public List<Category> getAllCategories() {
        return categoryService.getAllCateActive();
    }



    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public Category getCateById(@PathVariable String id) {
        return categoryService.getCateById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryReponseDto addCate(@Valid @RequestBody Category cate) {
        return this.categoryService.createCate(cate);
    }

    @PutMapping("/{id}")
    public CategoryReponseDto updateCate(@PathVariable String id, @Valid @RequestBody Category cate) {
        return this.categoryService.updateCate(id, cate);
    }

    @DeleteMapping("/{id}")
    public DeleteResponseDto deleteCate(@PathVariable String id) {
        return this.categoryService.softDeleteCate(id);
    }

}
