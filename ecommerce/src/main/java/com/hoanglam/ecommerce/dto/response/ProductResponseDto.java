package com.hoanglam.ecommerce.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hoanglam.ecommerce.entites.*;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
public class ProductResponseDto {
    private String id;
    private String title;
    private String metaTitle;
    private String desciption;
    private BigDecimal price;
    private float discount;
    private float averageRating;
    private int numberSold;
    private int numberRating;
    private String detail;
    private short quantity;
    private Date createdDate;
    private Date updatedDate;
    private String content;
    private boolean active;
    private Collection<Category> categoryCollection;
    private Collection<com.hoanglam.ecommerce.entites.Size> sizeCollection;


}
