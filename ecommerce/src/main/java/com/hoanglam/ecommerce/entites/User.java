/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoanglam.ecommerce.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;

import java.io.Serializable;
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
@Builder
@Table(name = "user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email"),
                @UniqueConstraint(columnNames = "mobile"),
        }
)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @Size(max = 36)
    private String id;
    @Size(max = 50)
    @Column(name = "firstName")
    private String firstName;
    @Size(max = 50)
    @Column(name = "lastName")
    private String lastName;
    @Size(max = 15)
    @Column(name = "mobile")
    private String mobile;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "passwordHash")
    private String passwordHash;
    @Basic(optional = false)
    @NotNull
    @Column(name = "username")
    private String username;

    @Column(name = "registeredDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registeredDate;
    @Column(name = "lastLogin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;
    @Lob
    @Size(max = 16383)
    @Column(name = "profile")
    private String profile;
    @Size(max = 200)
    @Column(name = "avatar_image")
    private String avatarImage;
    @Size(max = 100)
    @Column(name = "address")
    private String address;
    @Size(max = 50)
    @Column(name = "gender")
    private String gender;
    @Column(name = "dateOfBirth")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;

    @JsonIgnore
    @JoinTable(name = "user_roles", joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "role_id", referencedColumnName = "id")})
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
            }
    )
    private Collection<Role> rolesCollection;

    @OneToMany(mappedBy = "userId")
    @JsonIgnore
    private Collection<CartItem> cartItemCollection;

    @OneToMany(mappedBy = "userId")
    @JsonIgnore
    private Collection<Order> order1Collection;

    @JsonIgnore
    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY)
    private Collection<Rating> ratingCollection;

    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Collection<Rating> getRatingCollection() {
        return ratingCollection;
    }

    public void setRatingCollection(Collection<Rating> ratingCollection) {
        this.ratingCollection = ratingCollection;
    }

    @XmlTransient
    public Collection<Role> getRolesCollection() {
        return rolesCollection;
    }


    @XmlTransient
    public Collection<CartItem> getCartItemCollection() {
        return cartItemCollection;
    }

    public void setCartCollection(Collection<CartItem> cartCollection) {
        this.cartItemCollection = cartCollection;
    }

    public User() {
    }

    public User(String id) {
        this.id = id;
    }

    public User(String username, String email, String encode) {
        this.username = username;
        this.email = email;
        this.passwordHash = encode;
    }
    public User(String username, String email, String encode,String mobile) {
        this.username = username;
        this.email = email;
        this.passwordHash = encode;
        this.mobile = mobile;
    }

    public User(String id, String passwordHash, String username, Date registeredDate) {
        this.id = id;
        this.passwordHash = passwordHash;
        this.username = username;
        this.registeredDate = registeredDate;
    }

    public User(@Size(max = 36) String id, @Size(max = 50) String firstName,
                @Size(max = 50) String lastName, @Size(max = 15) String mobile,
                @Size(max = 50) String email, @Size(min = 1, max = 200) String passwordHash,
                @NotNull String username, Date registeredDate, Date lastLogin, @Size(max = 16383) String profile,
                @Size(max = 200) String avatarImage, @Size(max = 100) String address, @Size(max = 50) String gender,
                Date dateOfBirth, Collection<Role> rolesCollection, Collection<CartItem> cartItemCollection,
                Collection<Order> order1Collection, Collection<Rating> ratingCollection, @NotNull boolean active) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.email = email;
        this.passwordHash = passwordHash;
        this.username = username;
        this.registeredDate = registeredDate;
        this.lastLogin = lastLogin;
        this.profile = profile;
        this.avatarImage = avatarImage;
        this.address = address;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.rolesCollection = rolesCollection;
        this.cartItemCollection = cartItemCollection;
        this.order1Collection = order1Collection;
        this.ratingCollection = ratingCollection;
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getAvatarImage() {
        return avatarImage;
    }

    public void setAvatarImage(String avatarImage) {
        this.avatarImage = avatarImage;
    }

    public void setRolesCollection(Collection<Role> rolesCollection) {
        this.rolesCollection = rolesCollection;
    }




    @XmlTransient
    public Collection<Order> getOrder1Collection() {
        return order1Collection;
    }

    public void setOrder1Collection(Collection<Order> order1Collection) {
        this.order1Collection = order1Collection;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hoanglam.ecommerce.User[ id=" + id + " ]";
    }

}
