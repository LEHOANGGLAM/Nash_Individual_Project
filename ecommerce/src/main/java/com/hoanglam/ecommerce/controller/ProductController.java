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
import com.hoanglam.ecommerce.service.ProductService;
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

    @Autowired
    private ProductService productService;

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable String id) {
        return  productService.getProductById(id);
    }

    @GetMapping("/products")
    public List<Product> getProductsByPredicates(@RequestParam Map<String, String> params) {
        return  productService.getProductsByPredicates(params);
    }

    @GetMapping("/products/cate")
    public List<Product> getProductsByCateId(@RequestParam Map<String, String> params) {
        return  productService.getProductsByCateId(params);
    }


    //-------------FOR ADMIN BELOW--------------
    //------------FOR ADMIN BELOW------------------
//    @PostMapping("/admin/products")
//    public ResponseEntity<?> addProduct(@Valid @RequestBody Product product) {
//        if(productService.addProduct(product)){
//            return new ResponseEntity.ok(new MessageResponse("Add product successfully!"));
//        }
//
//        //temp return
//        return null;
//    }
//
//    @PutMapping("/admin/products/{id}")
//    public ResponseEntity<?> editProduct(@PathVariable String id) {
//        Product product = productRepository.findById(id).orElseThrow(() ->
//                new ResourceNotFoundException("Product not exist with id: " + id));
//
//        //Set update value below
//        product.setUpdatedDate(new Date());
//        productRepository.save(product);
//        return ResponseEntity.ok(new MessageResponse("Update product successfully!"));
//    }
}
