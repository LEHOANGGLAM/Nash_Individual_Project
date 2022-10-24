package com.hoanglam.ecommerce.dto.response.entities;

import com.hoanglam.ecommerce.entites.OrderItem;
import com.hoanglam.ecommerce.entites.User;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

@Data
public class OrderResponseDto {
    private String id;
    private short status;
    private float tax;
    private BigDecimal total;
    private int numberItem;
    private String paymentMethod;
    private String address;
    private Date createdAt;
    private Collection<OrderItem> orderItemCollection;
    private User userId;
}
