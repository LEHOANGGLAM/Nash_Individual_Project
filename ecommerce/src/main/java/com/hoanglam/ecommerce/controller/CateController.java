package com.hoanglam.ecommerce.controller;

import com.hoanglam.ecommerce.dto.response.SuccessResponse;
import com.hoanglam.ecommerce.entites.Category;
import com.hoanglam.ecommerce.entites.Product;
import com.hoanglam.ecommerce.entites.User;
import com.hoanglam.ecommerce.exception.ResourceNotFoundException;
import com.hoanglam.ecommerce.repository.CategoryRepository;
import com.hoanglam.ecommerce.repository.UserRepository;
import com.hoanglam.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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



    @GetMapping("/admin/categories/{id}")
    public Category getCateById(@PathVariable String id) {
        return categoryService.getCateById(id);
    }

    @PostMapping("/admin/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public Category addCate(@Valid @RequestBody Category cate) {
        return this.categoryService.createCate(cate);
    }

    @PutMapping("/admin/categories/{id}")
    public Category updateCate(@PathVariable String id, @Valid @RequestBody Category cate) {
        return this.categoryService.updateCate(id, cate);
    }

    @DeleteMapping("/admin/categories/{id}")
    public ResponseEntity<?> deleteCate(@PathVariable String id) {
        this.categoryService.deleteCate(id);
        return ResponseEntity.ok(new SuccessResponse("Delete category successfully!"));
    }
//    @DeleteMapping("/admin/users/{id}")
//    public ResponseEntity<?> deleteUser(@PathVariable String id) {
//        this.userService.deleteUser(id);
//        return ResponseEntity.ok(new SuccessResponse("Delete user successfully!"));
//    }
}
