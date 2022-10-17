package com.hoanglam.ecommerce.service;

import com.hoanglam.ecommerce.dto.response.APIRespone;
import com.hoanglam.ecommerce.dto.response.ProductResponseDto;
import com.hoanglam.ecommerce.entites.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ProductService{
    Product getProductById(String id);
    APIRespone<List<Product>> getAllProducts(Map<String, String> params);
    APIRespone<List<Product>> getProductsByPredicates(Map<String, String> params);
    APIRespone<List<Product>> getProductsByCateId(Map<String, String> params);
    Product createProduct(Product p);
    Product updateProduct(String id, Product product);
    boolean deleteProduct(String id);
}
