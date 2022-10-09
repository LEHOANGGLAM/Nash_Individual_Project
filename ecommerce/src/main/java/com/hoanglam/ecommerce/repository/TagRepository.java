package com.hoanglam.ecommerce.repository;

import com.hoanglam.ecommerce.entites.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TagRepository extends JpaRepository<Tag, Integer>, JpaSpecificationExecutor<Tag> {

}