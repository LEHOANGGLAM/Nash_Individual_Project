package com.nash.ecommerce.entites;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "userId", nullable = false)
    private Integer userId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "metaTitle")
    private String metaTitle;

    @Column(name = "slug", nullable = false)
    private String slug;

    @Column(name = "summary")
    private String summary;

    @Column(name = "type", nullable = false)
    private Integer type;

    @Column(name = "price", nullable = false)
    private Float price;

    @Column(name = "discount", nullable = false)
    private Float discount;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "shop", nullable = false)
    private Boolean shop;

    @Column(name = "publishedAt")
    private Date publishedAt;

    @Column(name = "endsAt")
    private Date endsAt;

    @Column(name = "content")
    private String content;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    public String getMetaTitle() {
        return metaTitle;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getSlug() {
        return slug;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSummary() {
        return summary;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getPrice() {
        return price;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setShop(Boolean shop) {
        this.shop = shop;
    }

    public Boolean isShop() {
        return shop;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setEndsAt(Date endsAt) {
        this.endsAt = endsAt;
    }

    public Date getEndsAt() {
        return endsAt;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id + '\'' +
                "userId=" + userId + '\'' +
                "title=" + title + '\'' +
                "metaTitle=" + metaTitle + '\'' +
                "slug=" + slug + '\'' +
                "summary=" + summary + '\'' +
                "type=" + type + '\'' +
                "price=" + price + '\'' +
                "discount=" + discount + '\'' +
                "quantity=" + quantity + '\'' +
                "shop=" + shop + '\'' +
                "publishedAt=" + publishedAt + '\'' +
                "endsAt=" + endsAt + '\'' +
                "content=" + content + '\'' +
                '}';
    }
}
