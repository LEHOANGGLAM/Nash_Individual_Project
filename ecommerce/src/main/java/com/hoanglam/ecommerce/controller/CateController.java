package com.hoanglam.ecommerce.controller;

import com.hoanglam.ecommerce.dto.response.entities.CategoryReponseDto;
import com.hoanglam.ecommerce.dto.response.DeleteResponseDto;
import com.hoanglam.ecommerce.entites.Category;
import com.hoanglam.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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



    @GetMapping("/admin/categories/{id}")
    public Category getCateById(@PathVariable String id) {
        return categoryService.getCateById(id);
    }

    @PostMapping("/admin/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryReponseDto addCate(@Valid @RequestBody Category cate) {
        return this.categoryService.createCate(cate);
    }

    @PutMapping("/admin/categories/{id}")
    public CategoryReponseDto updateCate(@PathVariable String id, @Valid @RequestBody Category cate) {
        return this.categoryService.updateCate(id, cate);
    }

    @DeleteMapping("/admin/categories/{id}")
    public DeleteResponseDto deleteCate(@PathVariable String id) {
        return this.categoryService.softDeleteCate(id);
    }

}
