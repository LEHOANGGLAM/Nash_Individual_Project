package com.hoanglam.ecommerce.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class RatingRequestDto {
    @NotBlank(message = "User id is required")
    private String userId;
    @NotBlank(message = "orderItemId id is required")
    private String orderItemId;
    @NotBlank(message = "content is required")
    private String content;
    @NotNull
    private int rating;


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

}
