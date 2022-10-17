package com.hoanglam.ecommerce.service.impl;

import com.hoanglam.ecommerce.entites.Category;
import com.hoanglam.ecommerce.entites.Product;
import com.hoanglam.ecommerce.exception.ResourceNotFoundException;
import com.hoanglam.ecommerce.repository.CategoryRepository;
import com.hoanglam.ecommerce.repository.UserRepository;
import com.hoanglam.ecommerce.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {
    public static final int pageSize = 20;
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCate() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCateById(String id) {
        Category cate = categoryRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Category not exist with id: " + id));
        return cate;
    }

    @Override
    public Category createCate(Category c) {
        UUID uuid = UUID.randomUUID();
        c.setId(uuid.toString());
        Category savedCate = categoryRepository.save(c);
        return savedCate;
    }

    @Override
    public Category updateCate(String id, Category cateUpdate) {
        //Optional<Category> cateOptional = categoryRepository.findById(id);
        if(categoryRepository.findById(id).isEmpty()){
            throw new ResourceNotFoundException("Category not exist with id: " + id);
        }
        cateUpdate = categoryRepository.save(cateUpdate);
        return cateUpdate;
    }

    @Override
    public boolean deleteCate(String id) {
        if(categoryRepository.findById(id).isEmpty()){
            throw new ResourceNotFoundException("Category not exist with id: " + id);
        }
        categoryRepository.deleteById(id);
        return true;
    }
}