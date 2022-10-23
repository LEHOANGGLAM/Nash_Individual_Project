package com.hoanglam.ecommerce.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hoanglam.ecommerce.entites.CartItem;
import com.hoanglam.ecommerce.entites.Order;
import com.hoanglam.ecommerce.entites.Rating;
import com.hoanglam.ecommerce.entites.Role;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;

@Data
public class UserResponseDto {
    private String id;
    private String firstName;
    private String lastName;
    private String mobile;
    private String email;
    private String username;
    private Date registeredDate;
    private String avatarImage;
    private String address;
    private String gender;
    private Date dateOfBirth;
    private Collection<Role> rolesCollection;
    private boolean active;

}
