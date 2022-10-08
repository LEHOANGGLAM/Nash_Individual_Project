package com.nash.ecommerce.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "product_tag")
public class ProductTag implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "product_id", nullable = false)
    private Integer productId;

    @Id
    @Column(name = "tag_id", nullable = false)
    private Integer tagId;

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public Integer getTagId() {
        return tagId;
    }

    @Override
    public String toString() {
        return "ProductTag{" +
                "productId=" + productId + '\'' +
                "tagId=" + tagId + '\'' +
                '}';
    }
}
