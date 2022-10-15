package com.hoanglam.ecommerce.dto.request;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class CommentRequestDto {
    @NotBlank(message = "User id is required")
    private String userId;
    @NotBlank(message = "Product id is required")
    private String productId;
    private Date createdDate;
    private String content;
    private int rating;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
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
