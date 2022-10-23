package com.hoanglam.ecommerce.services.impl;

import com.hoanglam.ecommerce.dto.request.CartItemRequestDto;
import com.hoanglam.ecommerce.dto.request.ProductRequestDto;
import com.hoanglam.ecommerce.dto.response.CartItemResponseDto;
import com.hoanglam.ecommerce.dto.response.ProductResponseDto;
import com.hoanglam.ecommerce.entites.Category;
import com.hoanglam.ecommerce.entites.Product;
import com.hoanglam.ecommerce.entites.Size;
import com.hoanglam.ecommerce.exception.ResourceNotFoundException;
import com.hoanglam.ecommerce.repository.CategoryRepository;
import com.hoanglam.ecommerce.repository.ProductRepository;
import com.hoanglam.ecommerce.repository.SizeRepository;
import com.hoanglam.ecommerce.service.ProductService;
import com.hoanglam.ecommerce.service.impl.CartItemServiceImpl;
import com.hoanglam.ecommerce.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {

    ProductServiceImpl productServiceImpl;
    AutoCloseable autoCloseable;

    Product findProduct;
    Product initialProduct;
    Product savedProduct;

    ProductRequestDto productRequestDto;
    ProductResponseDto expectedResponseDto;


    Collection<Category> categories = new HashSet<>();
    Category cate;
    Size size;


    @Mock
    ProductRepository productRepository;
    @Mock
    CategoryRepository categoryRepository;
    @Mock
    SizeRepository sizeRepository;
    @Mock
    ModelMapper modelMapper;

    @BeforeEach
    void beforeEach() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        productServiceImpl = new ProductServiceImpl(productRepository, categoryRepository, sizeRepository, modelMapper);


        findProduct = Product.builder().id("100")
                .quantity((short) 100)
                .price(BigDecimal.valueOf(50000))
                .title("test")
                .active(true).build();

        savedProduct = findProduct.toBuilder().quantity((short) 100)
                .price(BigDecimal.valueOf(50000))
                .title("test")
                .active(true).build();

        when(productRepository.findById("100")).thenReturn(Optional.of(findProduct));
        when(productRepository.save(any())).thenReturn(savedProduct);


        // for createProduct method below
        //init input for create method
        Collection<String> cates = new HashSet<>();
        cates.add("1");

        Collection<String> sizes = new HashSet<>();
        sizes.add("1");

        productRequestDto = ProductRequestDto.builder()
                .quantity((short) 100)
                .price(BigDecimal.valueOf(50000))
                .title("test")
                .categoryIds(cates)
                .sizeIds(sizes)
                .build();
        //end init input for create method

        cate = Category.builder().id("1").title("testCate").build();
        size = Size.builder().id("1").sizeName("testSize").build();
        when(categoryRepository.findById("1")).thenReturn(Optional.of(cate));
        when(sizeRepository.findById("1")).thenReturn(Optional.of(size));
        categories.add(cate);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    public void getProductById_ShouldReturnProduct_WhenDataValid() {
        Product product = productServiceImpl.getProductById("100");

        assertThat(product, is(savedProduct));
        assertThat(product.getTitle(), is(savedProduct.getTitle()));
    }

    @Test
    public void getProductById_ShouldReturnException_WhenDataNotValid() {
        ResourceNotFoundException resourceNotFoundException = Assertions.assertThrows(ResourceNotFoundException.class,
                () -> productServiceImpl.getProductById("1000"));
        Assertions.assertEquals("Product not exist with id: 1000", resourceNotFoundException.getMessage());
    }

    @Test
    public void createProduct_ShouldReturnProduct_WhenDataValid() {
        initialProduct = mock(Product.class);
        expectedResponseDto = mock(ProductResponseDto.class);

        when(modelMapper.map(productRequestDto, Product.class)).thenReturn(initialProduct);
        when(modelMapper.map(savedProduct, ProductResponseDto.class)).thenReturn(expectedResponseDto);

        //call method
        ProductResponseDto result = productServiceImpl.createProduct(productRequestDto);

        verify(initialProduct).setActive(true);
        verify(initialProduct).setNumberSold(0);
        verify(initialProduct).setAverageRating(0);
        verify(initialProduct).setCategoryCollection(categories);
        assertThat(result, is(expectedResponseDto));

    }
}
