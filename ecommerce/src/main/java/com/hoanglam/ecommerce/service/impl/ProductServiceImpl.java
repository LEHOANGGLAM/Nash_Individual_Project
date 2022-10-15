package com.hoanglam.ecommerce.service.impl;

import com.hoanglam.ecommerce.dto.response.SuccessResponse;
import com.hoanglam.ecommerce.entites.Product;
import com.hoanglam.ecommerce.exception.ResourceNotFoundException;
import com.hoanglam.ecommerce.repository.ProductRepository;
import com.hoanglam.ecommerce.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {
    public static final int pageSize = 9;

    private ModelMapper modelMapper;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product getProductById(String id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product not exist with id: " + id));
        return product;
    }

    @Override
    public List<Product> getAllProducts(Map<String, String> params) {
        Pageable pageable = PageRequest.of(Integer.parseInt(params.getOrDefault("page", "0")), pageSize);

        Page<Product> result = productRepository.findAll(pageable);
        return result.getContent();
    }

    @Override
    public List<Product> getProductsByPredicates(Map<String, String> params) {
        Pageable pageable = PageRequest.of(Integer.parseInt(params.getOrDefault("page", "0")), pageSize);
        BigDecimal fromPrice = BigDecimal.valueOf(Double.valueOf(params.getOrDefault("fromPrice", "0")));
        BigDecimal toPrice = BigDecimal.valueOf(Double.valueOf(params.getOrDefault("toPrice", "9999999999")));
        String kw = params.getOrDefault("name", "");
        String id = params.get("cateId");

        Page<Product> result;
        if (id != null && id != "") {
            result = productRepository.findByPriceBetweenAndTitleContainingAndCategoryCollection_IdOrderByCreatedDateDesc
                    (fromPrice, toPrice, kw, id, pageable);
        } else {
            result = productRepository.findByPriceBetweenAndTitleContainingOrderByCreatedDateDesc(fromPrice, toPrice, kw, pageable);
        }
        return result.getContent();
    }

    @Override
    public List<Product> getProductsByCateId(Map<String, String> params) {
        Pageable pageable = PageRequest.of(Integer.parseInt(params.getOrDefault("page", "0")), pageSize);
        String id = params.get("cateId");

        Page<Product> result;
        if (id != null && id != "") {
            result = productRepository.findByCategoryCollection_Id(id, pageable);
        } else {
            result = productRepository.findAll(pageable);
        }

        return result.getContent();
    }


    //-------------FOR ADMIN BELOW--------------
    //-------------FOR ADMIN BELOW--------------
    @Override
    public Product createProduct(Product p) {
        UUID uuid = UUID.randomUUID();
        p.setId(uuid.toString());
        Product savedProduct = productRepository.save(p);
        return savedProduct;
    }

    @Override
    public Product updateProduct(String id, Product productUpdated) {
        //Optional<Product> productOptional = productRepository.findById(id);
        if (productRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Product not exist with id: " + id);
        }
        productUpdated = productRepository.save(productUpdated);
        return productUpdated;
    }

    @Override
    public boolean deleteProduct(String id) {
        if (productRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Product not exist with id: " + id);
        }
        productRepository.deleteById(id);
        return true;
    }


    //-------------FOR ADMIN ABOVE--------------
    //-------------FOR ADMIN ABOVE--------------

}
