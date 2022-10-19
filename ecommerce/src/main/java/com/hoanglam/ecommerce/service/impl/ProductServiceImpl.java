package com.hoanglam.ecommerce.service.impl;

import com.hoanglam.ecommerce.dto.request.ProductRequestDto;
import com.hoanglam.ecommerce.dto.response.APIRespone;
import com.hoanglam.ecommerce.dto.response.DeleteResponseDto;
import com.hoanglam.ecommerce.dto.response.ProductResponseDto;
import com.hoanglam.ecommerce.dto.response.SuccessResponse;
import com.hoanglam.ecommerce.entites.Category;
import com.hoanglam.ecommerce.entites.Product;
import com.hoanglam.ecommerce.entites.Role;
import com.hoanglam.ecommerce.entites.Size;
import com.hoanglam.ecommerce.exception.ResourceNotFoundException;
import com.hoanglam.ecommerce.repository.CategoryRepository;
import com.hoanglam.ecommerce.repository.ProductRepository;
import com.hoanglam.ecommerce.repository.SizeRepository;
import com.hoanglam.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service

public class ProductServiceImpl implements ProductService {
    public static final int pageSize = 9;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SizeRepository sizeRepository;

    @Override
    public Product getProductById(String id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product not exist with id: " + id));
        return product;
    }

    @Override
    public APIRespone<List<Product>> getAllProducts(Map<String, String> params) {
        Pageable pageable = PageRequest.of(Integer.parseInt(params.getOrDefault("page", "0")), pageSize);

        Page<Product> result = productRepository.findByActive( true ,pageable);
        return new APIRespone<>(result.getTotalPages(),result.getContent());
    }

    @Override
    public APIRespone<List<Product>> getProductsByPredicates(Map<String, String> params) {
        Pageable pageable = PageRequest.of(Integer.parseInt(params.getOrDefault("page", "0")), pageSize);
        BigDecimal fromPrice = BigDecimal.valueOf(Double.valueOf(params.getOrDefault("fromPrice", "0")));
        BigDecimal toPrice = BigDecimal.valueOf(Double.valueOf(params.getOrDefault("toPrice", "9999999999")));
        String kw = params.getOrDefault("name", "");
        String id = params.get("cateId");

        Page<Product> result;
        if (id != null && id != "") {
            result = productRepository.findByPriceBetweenAndTitleContainingAndCategoryCollection_IdAndActiveOrderByCreatedDateDesc
                    (fromPrice, toPrice, kw, id, true, pageable);
        } else {
            result = productRepository.findByPriceBetweenAndTitleContainingAndActiveOrderByCreatedDateDesc(fromPrice, toPrice, kw, true, pageable);
        }
        return new APIRespone<>(result.getTotalPages(),result.getContent());
    }

//    @Override
//    public APIRespone<List<Product>> getProductsByCateId(Map<String, String> params) {
//        Pageable pageable = PageRequest.of(Integer.parseInt(params.getOrDefault("page", "0")), pageSize);
//        String id = params.get("cateId");
//
//        Page<Product> result;
//        if (id != null && id != "") {
//            result = productRepository.findByCategoryCollection_Id(id, pageable);
//        } else {
//            result = productRepository.findAll(pageable);
//        }
//
//        return new APIRespone<>(result.getTotalPages(),result.getContent());
//    }


    //-------------FOR ADMIN BELOW--------------
    //-------------FOR ADMIN BELOW--------------
    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        UUID uuid = UUID.randomUUID();

        Product p = modelMapper.map( productRequestDto, Product.class);
        p.setId(uuid.toString());
        p.setActive(true);
        p.setCreatedDate(new Date());
        p.setAverageRating(0);
        p.setNumberSold(0);
        p.setNumberRating(0);

        //add Category for product
        Collection<Category> categories = new HashSet<>();
        productRequestDto.getCategoryRequestDtos().forEach(cateDto ->{
            Optional<Category> category = categoryRepository.findByTitle(cateDto.getTitle());
            if(category.isEmpty()){
                throw new ResourceNotFoundException("Category not exist with title: " + cateDto.getTitle());
            }
            categories.add(category.get());
        });
        p.setCategoryCollection(categories);

        //add Size for product
        Collection<Size> sizes = new HashSet<>();
        productRequestDto.getSizeCollection().forEach(sizeDto ->{
            Optional<Size> size = sizeRepository.findBySizeName(sizeDto.getSizeName());
            if(size.isEmpty()){
                throw new ResourceNotFoundException("Size not exist with title: " + sizeDto.getSizeName());
            }
            sizes.add(size.get());
        });
        p.setSizeCollection(sizes);


        Product savedProduct = productRepository.save(p);
        ProductResponseDto productResponseDto = modelMapper.map(savedProduct, ProductResponseDto.class);
        return productResponseDto;
    }

    @Override
    public ProductResponseDto updateProduct(String id, Product productUpdated) {
        if (productRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Product not exist with id: " + id);
        }
        productUpdated = productRepository.save(productUpdated);
        ProductResponseDto productResponseDto = modelMapper.map(productUpdated, ProductResponseDto.class);
        return productResponseDto;
    }

    @Override
    public DeleteResponseDto softDeleteProduct(String id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new ResourceNotFoundException("Product not exist with id: " + id);
        }

        Product product = productOptional.get();
        product.setActive(false);
        productRepository.save(product);
        return new DeleteResponseDto("Delete product successfully", HttpStatus.OK.value(), HttpStatus.OK);
    }


    //-------------FOR ADMIN ABOVE--------------
    //-------------FOR ADMIN ABOVE--------------

}
