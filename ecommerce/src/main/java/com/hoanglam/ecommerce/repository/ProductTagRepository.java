package com.nash.ecommerce.repository;

import com.nash.ecommerce.entites.ProductTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductTagRepository extends JpaRepository<ProductTag, Integer>, JpaSpecificationExecutor<ProductTag> {

}