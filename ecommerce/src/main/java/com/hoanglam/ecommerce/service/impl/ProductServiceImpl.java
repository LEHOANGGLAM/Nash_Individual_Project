package com.hoanglam.ecommerce.service.impl;

import com.hoanglam.ecommerce.entites.Product;
import com.hoanglam.ecommerce.exception.ResourceNotFoundException;
import com.hoanglam.ecommerce.repository.ProductRepository;
import com.hoanglam.ecommerce.response.MessageResponse;
import com.hoanglam.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {
    public static final int pageSize = 9;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product getProductById(String id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product not exist with id: " + id));
        return product;
    }

    @Override
    public List<Product> getProductsByPredicates(Map<String, String> params) {
        Pageable pageable = PageRequest.of(Integer.parseInt(params.getOrDefault("page", "0")), pageSize);
        BigDecimal fromPrice = BigDecimal.valueOf(Double.valueOf(params.getOrDefault("fromPrice", "0")));
        BigDecimal toPrice = BigDecimal.valueOf(Double.valueOf(params.getOrDefault("toPrice", "9999999999")));
        String kw = params.getOrDefault("keyword", "");
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

    //TEMP
    @Override
    public boolean addProduct(Product p) {
        UUID uuid = UUID.randomUUID();

        //Set default value
        p.setId(uuid.toString());
        p.setCreatedDate(new Date());
        p.setNumberRating(0);
        p.setNumberSold(0);
        p.setAverageRating(0);
        productRepository.save(p);

        return true;
    }

    //TEMP
    @Override
    public boolean updateProduct(String id) {
        Optional<Product> product = productRepository.findById(id);
        if(product == null){
            return false;
        }
        //Set update value below

        //productRepository.save(product);
        return true;
    }
}
