package com.hoanglam.ecommerce.controller;

import com.hoanglam.ecommerce.dto.response.SuccessResponse;
import com.hoanglam.ecommerce.entites.Product;
import com.hoanglam.ecommerce.repository.ProductRepository;
import com.hoanglam.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("")
public class ProductController {
    public static final int pageSize = 9;

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
    @PostMapping("/admin/products")
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@Valid @RequestBody Product product) {
        return this.productService.addProduct(product);
    }

    @PutMapping("/admin/products/{id}")
    public Product updateProduct(@PathVariable String id, @Valid @RequestBody Product product) {
        return this.productService.updateProduct(id,product);
    }
}
