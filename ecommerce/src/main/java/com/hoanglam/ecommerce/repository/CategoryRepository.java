package com.hoanglam.ecommerce.repository;

import com.hoanglam.ecommerce.entites.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, String>, JpaSpecificationExecutor<Category> {
    List<Category> findByActive(boolean active);
    Optional<Category> findByTitle(String title);
}