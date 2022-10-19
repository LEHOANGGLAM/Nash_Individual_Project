package com.hoanglam.ecommerce.dto.response;

import com.hoanglam.ecommerce.entites.Product;
import com.hoanglam.ecommerce.entites.Size;
import com.hoanglam.ecommerce.entites.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class CartItemResponseDto {
    private String id;
    private BigDecimal price;
    private short quantity;
    private boolean active;
    private Date createdAt;
    private Product productId;
    private Size sizeId;
    private User userId;
}
