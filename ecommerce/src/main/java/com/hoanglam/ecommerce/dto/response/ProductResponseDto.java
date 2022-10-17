package com.hoanglam.ecommerce.dto.response;

import com.hoanglam.ecommerce.entites.Product;

import java.util.List;

public class ProductResponseDto {
    private int totalPage;
    private List<Product> productList;

    public ProductResponseDto(int totalPage, List<Product> productList) {
        this.totalPage = totalPage;
        this.productList = productList;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
