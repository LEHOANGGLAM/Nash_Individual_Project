/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoanglam.ecommerce.entites;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author dell
 */
@Entity
@Table(name = "product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 75)
    @Column(name = "title")
    private String title;
    @Size(max = 100)
    @Column(name = "metaTitle")
    private String metaTitle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "slug")
    private String slug;
    @Size(max = 255)
    @Column(name = "summary")
    private String summary;
    @Basic(optional = false)
    @NotNull
    @Column(name = "type")
    private short type;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private float price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "discount")
    private float discount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity")
    private short quantity;
    @Basic(optional = false)
    @NotNull
    @Column(name = "shop")
    private boolean shop;
    @Column(name = "publishedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date publishedAt;
    @Column(name = "endsAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endsAt;
    @Lob
    @Size(max = 65535)
    @Column(name = "content")
    private String content;
    @JoinTable(name = "product_tag", joinColumns = {
        @JoinColumn(name = "product_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "tag_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Tag> tagCollection;
    @ManyToMany(mappedBy = "productCollection")
    private Collection<Category> categoryCollection;

    @OneToMany(mappedBy = "productId")
    private Collection<Image> imageCollection;
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private Collection<OrderItem> orderItemCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private Collection<CartItem> cartItemCollection;

    public Product() {
    }

    public Product(Integer id) {
        this.id = id;
    }

    public Product(Integer id, String title, String slug, short type, float price, float discount, short quantity, boolean shop) {
        this.id = id;
        this.title = title;
        this.slug = slug;
        this.type = type;
        this.price = price;
        this.discount = discount;
        this.quantity = quantity;
        this.shop = shop;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
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

    public boolean getShop() {
        return shop;
    }

    public void setShop(boolean shop) {
        this.shop = shop;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Date getEndsAt() {
        return endsAt;
    }

    public void setEndsAt(Date endsAt) {
        this.endsAt = endsAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @XmlTransient
    public Collection<Tag> getTagCollection() {
        return tagCollection;
    }

    public void setTagCollection(Collection<Tag> tagCollection) {
        this.tagCollection = tagCollection;
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

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
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
        return "com.mycompany.pojo.Product[ id=" + id + " ]";
    }
    
}
