package com.hoanglam.ecommerce.dto.response.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hoanglam.ecommerce.entites.Category;
import com.hoanglam.ecommerce.entites.Product;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Data
public class CategoryReponseDto {
    private String id;
    private String title;
    private boolean active;
    private Collection<Product> productCollection;
    private Category parentId;
}
