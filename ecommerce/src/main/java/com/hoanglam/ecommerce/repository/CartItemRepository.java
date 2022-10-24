package com.hoanglam.ecommerce.repository;

import com.hoanglam.ecommerce.entites.CartItem;
import com.hoanglam.ecommerce.entites.Product;
import com.hoanglam.ecommerce.entites.Size;
import com.hoanglam.ecommerce.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, String>, JpaSpecificationExecutor<CartItem> {
    List<CartItem> findByUserId(User id);
    Optional<CartItem> findByUserIdAndProductIdAndSizeId(User userId, Product productId,Size sizeId);

    int countByUserId(User id);
}