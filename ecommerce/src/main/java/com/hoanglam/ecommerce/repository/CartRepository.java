package com.hoanglam.ecommerce.repository;

import com.hoanglam.ecommerce.entites.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CartRepository extends JpaRepository<Cart, Integer>, JpaSpecificationExecutor<Cart> {

}