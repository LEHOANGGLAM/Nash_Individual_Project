package com.hoanglam.ecommerce.service;

import com.hoanglam.ecommerce.dto.response.DeleteResponseDto;
import com.hoanglam.ecommerce.entites.Category;
import com.hoanglam.ecommerce.entites.User;

import java.util.List;
import java.util.Map;

public interface CategoryService {
    List<Category> getAllCate();
    Category getCateById(String id);
    Category createCate(Category u);
    Category updateCate(String id, Category cateUpdate);
    DeleteResponseDto softDeleteCate(String id);
}
