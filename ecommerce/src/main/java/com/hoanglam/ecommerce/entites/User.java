package com.nash.ecommerce.entites;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "email")
    private String email;

    @Column(name = "passwordHash", nullable = false)
    private String passwordHash;

    @Column(name = "vendor", nullable = false)
    private Boolean vendor;

    @Column(name = "registeredDate", nullable = false)
    private Date registeredDate;

    @Column(name = "lastLogin")
    private Date lastLogin;

    @Column(name = "avatar_image")
    private String avatarImage;

    @Column(name = "intro")
    private String intro;

    @Column(name = "profile")
    private String profile;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
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

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setVendor(Boolean vendor) {
        this.vendor = vendor;
    }

    public Boolean isVendor() {
        return vendor;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setAvatarImage(String avatarImage) {
        this.avatarImage = avatarImage;
    }

    public String getAvatarImage() {
        return avatarImage;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getIntro() {
        return intro;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getProfile() {
        return profile;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id + '\'' +
                "firstName=" + firstName + '\'' +
                "lastName=" + lastName + '\'' +
                "mobile=" + mobile + '\'' +
                "email=" + email + '\'' +
                "passwordHash=" + passwordHash + '\'' +
                "vendor=" + vendor + '\'' +
                "registeredDate=" + registeredDate + '\'' +
                "lastLogin=" + lastLogin + '\'' +
                "avatarImage=" + avatarImage + '\'' +
                "intro=" + intro + '\'' +
                "profile=" + profile + '\'' +
                '}';
    }
}
