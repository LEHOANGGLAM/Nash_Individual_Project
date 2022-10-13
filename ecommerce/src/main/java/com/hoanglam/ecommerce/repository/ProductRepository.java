package com.hoanglam.ecommerce.repository;

import com.hoanglam.ecommerce.entites.Product;
import com.hoanglam.ecommerce.entites.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigDecimal;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String>, JpaSpecificationExecutor<Product> {
    Optional<Product> findByTitle(String title);

    Page<Product> findAll(Pageable pageable);
    Page<Product> findByPriceBetweenAndTitleContainingAndCategoryCollection_Id
            (BigDecimal fromPrice, BigDecimal toPrice, String title,String id,Pageable pageable);
    Page<Product> findByPriceBetweenAndTitleContaining(BigDecimal fromPrice, BigDecimal toPrice, String title,Pageable pageable);
    Page<Product> findByCategoryCollection_Id(String id,  Pageable pageable);

//    Page<Product> findByPriceLessThanEqual(BigDecimal price, Pageable pageable);
//    Page<Product> findByPriceGreaterThanEqual(BigDecimal price, Pageable pageable);
}