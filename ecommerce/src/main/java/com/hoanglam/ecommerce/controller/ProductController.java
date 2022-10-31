package com.hoanglam.ecommerce.controller;

import com.hoanglam.ecommerce.dto.request.ProductRequestDto;
import com.hoanglam.ecommerce.dto.response.APIRespone;
import com.hoanglam.ecommerce.dto.response.DeleteResponseDto;
import com.hoanglam.ecommerce.dto.response.entities.ProductResponseDto;
import com.hoanglam.ecommerce.entites.Product;
import com.hoanglam.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable String id) {
        return productService.getProductById(id);
    }


    @GetMapping("")
    public List<ProductResponseDto> getAllProducts(@RequestParam Map<String, String> params) {
        return productService.getAllProducts(params);
    }

    @GetMapping("/search")
    public APIRespone<List<ProductResponseDto>> getProductsByPredicates(@RequestParam Map<String, String> params) {
        return productService.getProductsByPredicates(params);
    }

//    @GetMapping("/products/cate")
//    public APIRespone<List<Product>> getProductsByCateId(@RequestParam Map<String, String> params) {
//        return productService.getProductsByCateId(params);
//    }


    //-------------FOR ADMIN BELOW--------------
    //------------FOR ADMIN BELOW------------------
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDto addProduct(@Valid @RequestBody ProductRequestDto product) {
        return this.productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public ProductResponseDto updateProduct(@PathVariable String id, @Valid @RequestBody ProductRequestDto product) {
        return this.productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public DeleteResponseDto deleteProduct(@PathVariable String id) {
        return this.productService.softDeleteProduct(id);
    }
    //-------------FOR ADMIN ABOVE--------------
    //------------FOR ADMIN ABOVE------------------
}
