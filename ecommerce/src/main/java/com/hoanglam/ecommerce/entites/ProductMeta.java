package com.nash.ecommerce.entites;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product_meta")
public class ProductMeta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "productId", nullable = false)
    private Integer productId;

    @Column(name = "key", nullable = false)
    private String key;

    @Column(name = "content")
    private String content;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "ProductMeta{" +
                "id=" + id + '\'' +
                "productId=" + productId + '\'' +
                "key=" + key + '\'' +
                "content=" + content + '\'' +
                '}';
    }
}
