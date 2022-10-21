package com.hoanglam.ecommerce.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderItemResponeDto {
    private short quantity;
    private String productName;
    private String sizeName;

}
