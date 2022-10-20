package com.hoanglam.ecommerce.dto.request;

import com.hoanglam.ecommerce.entites.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Collection;

@Data
public class ProductRequestDto {
    private BigDecimal price;
    private short quantity;
    private String title;
    private Collection<String> categoryIds;
    private Collection<String> sizeIds;
}
