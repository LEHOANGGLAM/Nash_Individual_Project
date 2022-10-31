package com.hoanglam.ecommerce.service;

import com.hoanglam.ecommerce.dto.request.ProductRequestDto;
import com.hoanglam.ecommerce.dto.response.APIRespone;
import com.hoanglam.ecommerce.dto.response.DeleteResponseDto;
import com.hoanglam.ecommerce.dto.response.entities.ProductResponseDto;
import com.hoanglam.ecommerce.entites.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {
    Product getProductById(String id);

    List<ProductResponseDto> getAllProducts(Map<String, String> params);

    APIRespone<List<ProductResponseDto>> getProductsByPredicates(Map<String, String> params);

    //    APIRespone<List<Product>> getProductsByCateId(Map<String, String> params);
    ProductResponseDto createProduct(ProductRequestDto p);

    ProductResponseDto updateProduct(String id, ProductRequestDto product);

    DeleteResponseDto softDeleteProduct(String id);
}
