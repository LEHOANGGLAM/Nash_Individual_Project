/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoanglam.ecommerce.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dell
 */
@Entity
@Table(name = "order_item")
@Builder
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @Size(max = 36)
    private String id;

    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private BigDecimal price;

    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity")
    private short quantity;

    @JsonIgnore
    @Basic(optional = false)
    @NotNull
    @Column(name = "createdAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;


    @JsonIgnore
    @Column(name = "updatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @JsonIgnore
    @Lob
    @Size(max = 65535)
    @Column(name = "content")
    private String content;

    @JsonIgnore
    @JoinColumn(name = "orderId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Order orderId;
    @JoinColumn(name = "productId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Product productId;
    @JoinColumn(name = "sizeId", referencedColumnName = "id")
    @ManyToOne
    private com.hoanglam.ecommerce.entites.Size sizeId;

    @OneToOne(mappedBy = "orderItem", cascade = CascadeType.ALL)
    private Rating rating;

    public OrderItem() {
    }

    public OrderItem(@Size(max = 36) String id, @NotNull BigDecimal price,
                     @NotNull short quantity, @NotNull Date createdAt,
                     Date updatedAt, @Size(max = 65535) String content,
                     Order orderId, Product productId, com.hoanglam.ecommerce.entites.Size sizeId,
                     Rating rating) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.content = content;
        this.orderId = orderId;
        this.productId = productId;
        this.sizeId = sizeId;
        this.rating = rating;
    }

    public OrderItem(String id) {
        this.id = id;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public com.hoanglam.ecommerce.entites.Size getSizeId() {
        return sizeId;
    }

    public void setSizeId(com.hoanglam.ecommerce.entites.Size sizeId) {
        this.sizeId = sizeId;
    }


    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public short getQuantity() {
        return quantity;
    }

    public void setQuantity(short quantity) {
        this.quantity = quantity;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Order getOrderId() {
        return orderId;
    }

    public void setOrderId(Order orderId) {
        this.orderId = orderId;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
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
        if (!(object instanceof OrderItem)) {
            return false;
        }
        OrderItem other = (OrderItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hoanglam.ecommerce.OrderItem[ id=" + id + " ]";
    }
    
}
