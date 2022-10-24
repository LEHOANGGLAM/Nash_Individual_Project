package com.hoanglam.ecommerce.repository;

import com.hoanglam.ecommerce.entites.Product;
import com.hoanglam.ecommerce.entites.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigDecimal;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
    Page<User> findByUsernameContainingOrderByUsernameAsc(String kw, Pageable pageable);

    Page<User> findByRolesCollection_IdAndUsernameContainingOrderByUsernameAsc
            (String id, String keyword, Pageable pageable);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByMobile(String mobile);
}