package com.hoanglam.ecommerce.dto.request;

import com.hoanglam.ecommerce.entites.OrderItem;
import lombok.Builder;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Builder
public class OrderResquestDto {
    @NotBlank(message = "userId is required")
    private String userId;
    @NotBlank(message = "address is required")
    private String address;
    @NotBlank(message = "paymentMethod is required")
    private String paymentMethod;
    private List<OrderItemRequestDto> orderItems;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public List<OrderItemRequestDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemRequestDto> orderItems) {
        this.orderItems = orderItems;
    }
}
