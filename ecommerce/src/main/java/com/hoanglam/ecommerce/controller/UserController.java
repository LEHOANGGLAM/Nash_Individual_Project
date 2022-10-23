package com.hoanglam.ecommerce.controller;

import com.hoanglam.ecommerce.dto.response.DeleteResponseDto;
import com.hoanglam.ecommerce.dto.response.ProductResponseDto;
import com.hoanglam.ecommerce.dto.response.SuccessResponse;
import com.hoanglam.ecommerce.dto.response.UserResponseDto;
import com.hoanglam.ecommerce.entites.Product;
import com.hoanglam.ecommerce.entites.User;
import com.hoanglam.ecommerce.exception.ResourceNotFoundException;
import com.hoanglam.ecommerce.repository.UserRepository;
import com.hoanglam.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/{id}")
    public UserResponseDto getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @PutMapping("/users/{id}")
    public UserResponseDto updateUserById(@PathVariable String id, @Valid @RequestBody User user) {
        return userService.updateUser(id, user);
    }


    @GetMapping("/admin/users")
    public List<UserResponseDto> getAllUsers(@RequestParam Map<String, String> params) {
        return  userService.getAllUsers(params);
    }

    @GetMapping("/admin/users/role")
    public List<UserResponseDto> getUserByRole(@RequestParam Map<String, String> params) {
        return  userService.getUsersByRole(params);
    }

    @DeleteMapping("/admin/users/{id}")
    public DeleteResponseDto deleteUser(@PathVariable String id) {
        return  this.userService.softDeleteUser(id);
    }
}
