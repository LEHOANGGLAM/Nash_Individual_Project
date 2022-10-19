package com.hoanglam.ecommerce.dto.request;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class RatingRequestDto {
    @NotBlank(message = "User id is required")
    private String userId;
    @NotBlank(message = "orderItemId id is required")
    private String orderItemId;
    private String content;
    private int rating;

    private String productId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(String orderItemId) {
        this.orderItemId = orderItemId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
