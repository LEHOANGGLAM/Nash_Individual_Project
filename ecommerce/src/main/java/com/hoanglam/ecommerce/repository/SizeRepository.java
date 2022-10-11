package com.hoanglam.ecommerce.repository;

import com.hoanglam.ecommerce.entites.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SizeRepository extends JpaRepository<Size, String>, JpaSpecificationExecutor<Size> {

}