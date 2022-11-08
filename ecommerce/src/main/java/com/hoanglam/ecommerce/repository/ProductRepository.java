package com.hoanglam.ecommerce.repository;

import com.hoanglam.ecommerce.entites.Product;
import com.hoanglam.ecommerce.entites.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String>, JpaSpecificationExecutor<Product> {
    Optional<Product> findByTitle(String title);

    List<Product> findByActiveOrderByCreatedDateDesc(boolean active);

    Page<Product> findByPriceBetweenAndTitleContainingAndCategoryCollection_IdAndActiveOrderByCreatedDateDesc
            (BigDecimal fromPrice, BigDecimal toPrice, String title, String id, boolean active, Pageable pageable);

    Page<Product> findByPriceBetweenAndTitleContainingAndActiveOrderByCreatedDateDesc
            (BigDecimal fromPrice, BigDecimal toPrice, String title,boolean active, Pageable pageable);

    Page<Product> findByCategoryCollection_Id(String id, Pageable pageable);


//    Page<Product> findByPriceLessThanEqual(BigDecimal price, Pageable pageable);
//    Page<Product> findByPriceGreaterThanEqual(BigDecimal price, Pageable pageable);
}