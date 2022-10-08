package com.nash.ecommerce.entites;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "userId")
    private Integer userId;

    @Column(name = "sessionId", nullable = false)
    private String sessionId;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "subTotal", nullable = false)
    private Float subTotal;

    @Column(name = "itemDiscount", nullable = false)
    private Float itemDiscount;

    @Column(name = "tax", nullable = false)
    private Float tax;

    @Column(name = "shipping", nullable = false)
    private Float shipping;

    @Column(name = "total", nullable = false)
    private Float total;

    @Column(name = "promo")
    private String promo;

    @Column(name = "discount", nullable = false)
    private Float discount;

    @Column(name = "grandTotal", nullable = false)
    private Float grandTotal;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "email")
    private String email;

    @Column(name = "line1")
    private String line1;

    @Column(name = "line2")
    private String line2;

    @Column(name = "city")
    private String city;

    @Column(name = "province")
    private String province;

    @Column(name = "country")
    private String country;

    @Column(name = "createdAt", nullable = false)
    private Date createdAt;

    @Column(name = "updatedAt")
    private Date updatedAt;

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

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setSubTotal(Float subTotal) {
        this.subTotal = subTotal;
    }

    public Float getSubTotal() {
        return subTotal;
    }

    public void setItemDiscount(Float itemDiscount) {
        this.itemDiscount = itemDiscount;
    }

    public Float getItemDiscount() {
        return itemDiscount;
    }

    public void setTax(Float tax) {
        this.tax = tax;
    }

    public Float getTax() {
        return tax;
    }

    public void setShipping(Float shipping) {
        this.shipping = shipping;
    }

    public Float getShipping() {
        return shipping;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Float getTotal() {
        return total;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }

    public String getPromo() {
        return promo;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setGrandTotal(Float grandTotal) {
        this.grandTotal = grandTotal;
    }

    public Float getGrandTotal() {
        return grandTotal;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getLine2() {
        return line2;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvince() {
        return province;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id + '\'' +
                "userId=" + userId + '\'' +
                "sessionId=" + sessionId + '\'' +
                "token=" + token + '\'' +
                "status=" + status + '\'' +
                "subTotal=" + subTotal + '\'' +
                "itemDiscount=" + itemDiscount + '\'' +
                "tax=" + tax + '\'' +
                "shipping=" + shipping + '\'' +
                "total=" + total + '\'' +
                "promo=" + promo + '\'' +
                "discount=" + discount + '\'' +
                "grandTotal=" + grandTotal + '\'' +
                "firstName=" + firstName + '\'' +
                "lastName=" + lastName + '\'' +
                "mobile=" + mobile + '\'' +
                "email=" + email + '\'' +
                "line1=" + line1 + '\'' +
                "line2=" + line2 + '\'' +
                "city=" + city + '\'' +
                "province=" + province + '\'' +
                "country=" + country + '\'' +
                "createdAt=" + createdAt + '\'' +
                "updatedAt=" + updatedAt + '\'' +
                "content=" + content + '\'' +
                '}';
    }
}
