package com.hoanglam.ecommerce.repository;

import com.hoanglam.ecommerce.entites.Image;
import com.hoanglam.ecommerce.entites.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, String>, JpaSpecificationExecutor<Image> {
    List<Image> findByProductId(Product proId);
    void deleteAllByProductId(Product proId);
}