package com.hoanglam.ecommerce.controller;

import com.hoanglam.ecommerce.entites.Category;
import com.hoanglam.ecommerce.entites.Product;
import com.hoanglam.ecommerce.entites.User;
import com.hoanglam.ecommerce.exception.ResourceNotFoundException;
import com.hoanglam.ecommerce.repository.CategoryRepository;
import com.hoanglam.ecommerce.repository.ProductRepository;
import com.hoanglam.ecommerce.repository.UserRepository;
import com.hoanglam.ecommerce.request.SignUpRequest;
import com.hoanglam.ecommerce.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("")
public class ProductController {
    public static final int pageSize = 9;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product not exist with id: " + id));
        return ResponseEntity.ok(product);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProductsByPredicates(@RequestParam Map<String, String> params) {
        Pageable pageable = PageRequest.of(Integer.parseInt(params.getOrDefault("page", "0")), pageSize);
        BigDecimal fromPrice = BigDecimal.valueOf(Double.valueOf(params.getOrDefault("fromPrice", "0")));
        BigDecimal toPrice = BigDecimal.valueOf(Double.valueOf(params.getOrDefault("toPrice", "9999999999")));
        String kw = params.getOrDefault("keyword", "");
        String id = params.get("cateId");

        Page<Product> result;
        if (id != null && id != "") {
            result = productRepository.findByPriceBetweenAndTitleContainingAndCategoryCollection_Id
                    (fromPrice, toPrice, kw, id, pageable);
        } else {
            result = productRepository.findByPriceBetweenAndTitleContaining(fromPrice, toPrice , kw, pageable);
        }
        return new ResponseEntity<>(result.getContent(), HttpStatus.OK);
    }

    @GetMapping("/products/cate")
    public ResponseEntity<List<Product>> getProductsByCateId(@RequestParam Map<String, String> params) {
        Pageable pageable = PageRequest.of(Integer.parseInt(params.getOrDefault("page", "0")), pageSize);
        String id = params.get("cateId");

        Page<Product> result;
        if (id != null && id != "") {
            result = productRepository.findByCategoryCollection_Id(id, pageable);
            return new ResponseEntity<>(result.getContent(), HttpStatus.OK);
        }
        result = productRepository.findAll(pageable);
        return new ResponseEntity<>(result.getContent(), HttpStatus.OK);
    }


    //------------FOR ADMIN BELOW------------------
    @PostMapping("/admin/products")
    public ResponseEntity<?> addProduct(@Valid @RequestBody Product product) {
        UUID uuid = UUID.randomUUID();

        //Set default value
        product.setId(uuid.toString());
        product.setCreatedDate(new Date());
        product.setNumberRating(0);
        product.setNumberSold(0);
        product.setAverageRating(0);
        productRepository.save(product);

        return ResponseEntity.ok(new MessageResponse("Add product successfully!"));
    }


}
