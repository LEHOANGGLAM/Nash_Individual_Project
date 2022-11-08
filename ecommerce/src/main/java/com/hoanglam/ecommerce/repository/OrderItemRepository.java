package com.hoanglam.ecommerce.repository;

import com.hoanglam.ecommerce.entites.OrderItem;
import com.hoanglam.ecommerce.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, String>, JpaSpecificationExecutor<OrderItem> {
    List<OrderItem> findByOrderId_UserIdOrderByRating(User userid);
}