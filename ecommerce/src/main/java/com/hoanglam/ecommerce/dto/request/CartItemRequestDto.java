package com.hoanglam.ecommerce.dto.request;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.Date;

@Builder
public class CartItemRequestDto {

    private short quantity;
    private String productId;
    private String sizeId;
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public short getQuantity() {
        return quantity;
    }

    public void setQuantity(short quantity) {
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSizeId() {
        return sizeId;
    }

    public void setSizeId(String sizeId) {
        this.sizeId = sizeId;
    }
}
