package com.hoanglam.ecommerce.service;

import com.hoanglam.ecommerce.dto.response.entities.CategoryReponseDto;
import com.hoanglam.ecommerce.dto.response.DeleteResponseDto;
import com.hoanglam.ecommerce.entites.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCate();
    Category getCateById(String id);
    CategoryReponseDto createCate(Category u);
    CategoryReponseDto updateCate(String id, Category cateUpdate);
    DeleteResponseDto softDeleteCate(String id);
}
