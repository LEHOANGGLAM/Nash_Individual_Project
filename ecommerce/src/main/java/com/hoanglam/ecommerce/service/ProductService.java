package com.hoanglam.ecommerce.service;

import com.hoanglam.ecommerce.entites.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ProductService{
    Product getProductById(String id);
    List<Product> getAllProducts(Map<String, String> params);
    List<Product> getProductsByPredicates(Map<String, String> params);
    List<Product> getProductsByCateId(Map<String, String> params);
    Product createProduct(Product p);
    Product updateProduct(String id, Product product);
    boolean deleteProduct(String id);
}
