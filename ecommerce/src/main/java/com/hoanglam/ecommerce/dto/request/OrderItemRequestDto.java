package com.hoanglam.ecommerce.dto.request;

import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
public class OrderItemRequestDto {
    @NotNull
    private short quantity;
    @NotBlank(message = "productId is required")
    private String productId;
    @NotBlank(message = "sizeId is required")
    private String sizeId;

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
