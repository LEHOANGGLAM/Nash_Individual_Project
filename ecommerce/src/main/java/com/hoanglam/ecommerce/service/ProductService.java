package com.hoanglam.ecommerce.service;

import com.hoanglam.ecommerce.entites.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ProductService{
    Product getProductById(String id);
    List<Product> getProductsByPredicates(Map<String, String> params);
    List<Product> getProductsByCateId(Map<String, String> params);
    boolean addProduct(Product p);
    boolean updateProduct(String id);
}
