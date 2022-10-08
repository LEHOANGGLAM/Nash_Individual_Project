package com.nash.ecommerce.repository;

import com.nash.ecommerce.entites.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRolesRepository extends JpaRepository<UserRoles, Integer>, JpaSpecificationExecutor<UserRoles> {

}