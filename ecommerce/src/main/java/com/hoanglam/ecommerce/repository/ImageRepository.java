package com.hoanglam.ecommerce.repository;

import com.hoanglam.ecommerce.entites.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ImageRepository extends JpaRepository<Image, Integer>, JpaSpecificationExecutor<Image> {

}