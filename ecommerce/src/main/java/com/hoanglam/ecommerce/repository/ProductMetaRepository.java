package com.nash.ecommerce.repository;

import com.nash.ecommerce.entites.ProductMeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductMetaRepository extends JpaRepository<ProductMeta, Integer>, JpaSpecificationExecutor<ProductMeta> {

}