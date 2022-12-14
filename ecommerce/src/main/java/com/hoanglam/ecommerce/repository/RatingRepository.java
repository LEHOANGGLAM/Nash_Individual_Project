package com.hoanglam.ecommerce.repository;

import com.hoanglam.ecommerce.entites.OrderItem;
import com.hoanglam.ecommerce.entites.Product;
import com.hoanglam.ecommerce.entites.Rating;
import com.hoanglam.ecommerce.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, String>, JpaSpecificationExecutor<Rating> {
    Optional<Rating> findByUserIdAndOrderItem(User userId, OrderItem orderItemId);
    List<Rating> findByProductId(String id);
}
