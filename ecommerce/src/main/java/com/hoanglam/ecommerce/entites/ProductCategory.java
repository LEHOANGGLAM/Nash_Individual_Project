package com.nash.ecommerce.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "product_category")
public class ProductCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "productId", nullable = false)
    private Integer productId;

    @Id
    @Column(name = "categoryId", nullable = false)
    private Integer categoryId;

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "productId=" + productId + '\'' +
                "categoryId=" + categoryId + '\'' +
                '}';
    }
}
