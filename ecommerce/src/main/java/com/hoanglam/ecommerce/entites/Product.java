/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoanglam.ecommerce.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author dell
 */
@Entity
@Table(name = "product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @Size(max = 36)
    private String id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 75)
    @Column(name = "title")
    private String title;

    @Size(max = 100)
    @Column(name = "metaTitle")
    private String metaTitle;

    @Size(max = 255)
    @Column(name = "desciption")
    private String desciption;

    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "discount")
    private float discount;

    @Column(name = "averageRating")
    private float averageRating;

    @Column(name = "numberSold")
    private int numberSold;

    @Column(name = "numberRating")
    private int numberRating;

    @Size(max = 65535)
    @Column(name = "detail")
    private String detail;

    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity")
    private short quantity;

    @Column(name = "createdDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "updatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    @Size(max = 65535)
    @Column(name = "content")
    private String content;

    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private boolean active;

    @JoinTable(name = "product_category", joinColumns = {
            @JoinColumn(name = "productId", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "categoryId", referencedColumnName = "id")})
    @ManyToMany
    @JsonIgnore
    private Collection<Category> categoryCollection;

    @OneToMany(mappedBy = "productId")
    private Collection<Image> imageCollection;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private Collection<OrderItem> orderItemCollection;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private Collection<CartItem> cartItemCollection;

    @JsonIgnore
    @OneToMany(mappedBy = "productId", fetch = FetchType.LAZY)
    private Collection<Comment> commentCollection;


    @JoinTable(name = "product_size", joinColumns = {
            @JoinColumn(name = "product_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "size_id", referencedColumnName = "id")})
    @ManyToMany
    @JsonIgnore
    private Collection<com.hoanglam.ecommerce.entites.Size> sizeCollection;

    public Collection<com.hoanglam.ecommerce.entites.Size> getSizeCollection() {
        return sizeCollection;
    }

    public void setSizeCollection(Collection<com.hoanglam.ecommerce.entites.Size> sizeCollection) {
        this.sizeCollection = sizeCollection;
    }

    public Product() {
    }

    public Product(String id) {
        this.id = id;
    }


    public Product(@Size(max = 36) String id, @NotNull @Size(min = 1, max = 75) String title,
                   @Size(max = 100) String metaTitle, @Size(max = 255) String desciption,
                   @NotNull BigDecimal price, float discount, float averageRating,
                   int numberSold, int numberRating, @Size(max = 65535) String detail,
                   @NotNull short quantity, Date createdDate, Date updatedDate, @Size(max = 65535) String content) {
        this.id = id;
        this.title = title;
        this.metaTitle = metaTitle;
        this.desciption = desciption;

        this.price = price;
        this.discount = discount;
        this.averageRating = averageRating;
        this.numberSold = numberSold;
        this.numberRating = numberRating;
        this.detail = detail;
        this.quantity = quantity;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.content = content;
    }

    public float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMetaTitle() {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public short getQuantity() {
        return quantity;
    }

    public void setQuantity(short quantity) {
        this.quantity = quantity;
    }

    public int getNumberSold() {
        return numberSold;
    }

    public void setNumberSold(int numberSold) {
        this.numberSold = numberSold;
    }

    public int getNumberRating() {
        return numberRating;
    }

    public void setNumberRating(int numberRating) {
        this.numberRating = numberRating;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }


    @XmlTransient
    public Collection<Category> getCategoryCollection() {
        return categoryCollection;
    }

    public void setCategoryCollection(Collection<Category> categoryCollection) {
        this.categoryCollection = categoryCollection;
    }


    @XmlTransient
    public Collection<Image> getImageCollection() {
        return imageCollection;
    }

    public void setImageCollection(Collection<Image> imageCollection) {
        this.imageCollection = imageCollection;
    }


    @XmlTransient
    public Collection<OrderItem> getOrderItemCollection() {
        return orderItemCollection;
    }

    public void setOrderItemCollection(Collection<OrderItem> orderItemCollection) {
        this.orderItemCollection = orderItemCollection;
    }

    @XmlTransient
    public Collection<CartItem> getCartItemCollection() {
        return cartItemCollection;
    }

    public void setCartItemCollection(Collection<CartItem> cartItemCollection) {
        this.cartItemCollection = cartItemCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hoanglam.ecommerce.Product[ id=" + id + " ]";
    }

}
