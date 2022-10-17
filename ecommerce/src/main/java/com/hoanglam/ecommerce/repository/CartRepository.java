package com.hoanglam.ecommerce.repository;

import com.hoanglam.ecommerce.entites.Cart;
import com.hoanglam.ecommerce.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, String>, JpaSpecificationExecutor<Cart> {
    Optional<Cart> findByUserId(User id);
    Optional<Cart> findByCartItemCollection_Id(String id);
}