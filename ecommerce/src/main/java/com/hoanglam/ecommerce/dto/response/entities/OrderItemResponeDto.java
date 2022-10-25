package com.hoanglam.ecommerce.dto.response.entities;

import lombok.Builder;
import lombok.Data;

@Data
public class OrderItemResponeDto {
    private short quantity;
    private String productName;
    private String sizeName;

}
