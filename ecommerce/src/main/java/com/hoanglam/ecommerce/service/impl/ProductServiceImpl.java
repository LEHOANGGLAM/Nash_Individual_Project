package com.hoanglam.ecommerce.service.impl;

import com.cloudinary.Cloudinary;
import com.hoanglam.ecommerce.dto.request.ProductRequestDto;
import com.hoanglam.ecommerce.dto.response.*;
import com.hoanglam.ecommerce.dto.response.entities.ProductResponseDto;
import com.hoanglam.ecommerce.entites.Category;
import com.hoanglam.ecommerce.entites.Image;
import com.hoanglam.ecommerce.entites.Product;
import com.hoanglam.ecommerce.entites.Size;
import com.hoanglam.ecommerce.exception.ResourceNotFoundException;
import com.hoanglam.ecommerce.repository.CategoryRepository;
import com.hoanglam.ecommerce.repository.ImageRepository;
import com.hoanglam.ecommerce.repository.ProductRepository;
import com.hoanglam.ecommerce.repository.SizeRepository;
import com.hoanglam.ecommerce.service.CloudinaryService;
import com.hoanglam.ecommerce.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @Autowired
    private ImageRepository imageRepository;
    @Autowired(required = false)
    private CloudinaryService cloudinaryService;
    @Autowired
    private Cloudinary cloudinary;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, SizeRepository sizeRepository, ModelMapper modelMapper) {
        super();
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.sizeRepository = sizeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Product getProductById(String id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product not exist with id: " + id));
        return product;
    }

    @Override
    public List<ProductResponseDto> getAllProducts(Map<String, String> params) {
        //Pageable pageable = PageRequest.of(Integer.parseInt(params.getOrDefault("page", "0")), pageSize);

        List<Product> result = productRepository.findByActiveOrderByCreatedDateDesc(true);

        //map to listDTO
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        result.forEach(p -> {
            ProductResponseDto productResponseDto = modelMapper.map(p, ProductResponseDto.class);
            productResponseDtos.add(productResponseDto);
        });
        return productResponseDtos;
    }

    @Override
    public APIRespone<List<ProductResponseDto>> getProductsByPredicates(Map<String, String> params) {
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

        //map to listDTO
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        result.getContent().forEach(p -> {
            ProductResponseDto productResponseDto = modelMapper.map(p, ProductResponseDto.class);
            productResponseDtos.add(productResponseDto);
        });
        return new APIRespone<>(result.getTotalPages(), productResponseDtos);
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

        Product p = modelMapper.map(productRequestDto, Product.class);
        p.setId(uuid.toString());
        p.setActive(true);
        p.setCreatedDate(new Date());
        p.setAverageRating(0);
        p.setNumberSold(0);
        p.setNumberRating(0);

        //add Category for product
        Collection<Category> categories = new HashSet<>();
        productRequestDto.getCategoryIds().forEach(cateDto -> {
            Optional<Category> category = categoryRepository.findById(cateDto);
            if (category.isEmpty()) {
                throw new ResourceNotFoundException("Category not exist with id: " + cateDto);
            }
            categories.add(category.get());
        });
        p.setCategoryCollection(categories);

        //add Size for product
        Collection<Size> sizes = new HashSet<>();
        productRequestDto.getSizeIds().forEach(sizeDto -> {
            Optional<Size> size = sizeRepository.findBySizeName(sizeDto);
            if (size.isEmpty()) {
                throw new ResourceNotFoundException("Size not exist with name: " + sizeDto);
            }
            sizes.add(size.get());
        });
        p.setSizeCollection(sizes);

        Product savedProduct = productRepository.save(p);

        if(productRequestDto.getImages() != null){
            for (int i = 0; i < productRequestDto.getImages().size(); i++) {
                uuid = UUID.randomUUID();
                Image image = new Image();
                image.setId(uuid.toString());
                image.setProductId(p);
                image.setLink(productRequestDto.getImages().get(i));
                imageRepository.save(image);
            }
        }

        //map to responseDto
        ProductResponseDto productResponseDto = modelMapper.map(savedProduct, ProductResponseDto.class);
        return productResponseDto;
    }

    @Override
    public ProductResponseDto updateProduct(String id, ProductRequestDto productRequestDto) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product not exist with id: " + id));

//        //update new Category for product
        Collection<Category> categories = new HashSet<>();
        productRequestDto.getCategoryIds().forEach(cateDto -> {
            Optional<Category> category = categoryRepository.findById(cateDto);
            if (category.isEmpty()) {
                throw new ResourceNotFoundException("Category not exist with id: " + cateDto);
            }
            categories.add(category.get());
        });
        product.setCategoryCollection(categories);

        //update new Size for product
        Collection<Size> sizes = new HashSet<>();
        productRequestDto.getSizeIds().forEach(sizeDto -> {
            Optional<Size> size = sizeRepository.findBySizeName(sizeDto);
            if (size.isEmpty()) {
                throw new ResourceNotFoundException("Size not exist with id: " + sizeDto);
            }
            sizes.add(size.get());
        });
        product.setSizeCollection(sizes);

        //delete link Image old
        List<Image> tempList = imageRepository.findByProductId(productRepository.findById(id).get());
        imageRepository.deleteAllInBatch(tempList);
        //Set  new image
        List<Image> images = new ArrayList<>();
        UUID uuid;
        if(productRequestDto.getImages() != null){
            for (int i = 0; i < productRequestDto.getImages().size(); i++) {
                uuid = UUID.randomUUID();
                Image image = new Image();
                image.setId(uuid.toString());
                image.setProductId(product);
                image.setLink(productRequestDto.getImages().get(i));

                imageRepository.save(image);
                images.add(image);
            }
        }
        product.setImageCollection(images);

        //set new Value
        product.setDesciption(productRequestDto.getDesciption());
        product.setPrice(productRequestDto.getPrice());
        product.setQuantity(productRequestDto.getQuantity());
        product.setTitle(productRequestDto.getTitle());
       //save new product
        product = productRepository.save(product);

        //map to responseDto
        ProductResponseDto productResponseDto = modelMapper.map(product, ProductResponseDto.class);
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
