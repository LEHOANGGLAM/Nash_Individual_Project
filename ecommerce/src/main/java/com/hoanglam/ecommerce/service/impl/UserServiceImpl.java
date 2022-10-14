package com.hoanglam.ecommerce.service.impl;

import com.hoanglam.ecommerce.entites.Product;
import com.hoanglam.ecommerce.entites.User;
import com.hoanglam.ecommerce.exception.ResourceNotFoundException;
import com.hoanglam.ecommerce.repository.ProductRepository;
import com.hoanglam.ecommerce.repository.UserRepository;
import com.hoanglam.ecommerce.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    public static final int pageSize = 20;

    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User not exist with id: " + id));
        return user;
    }

    @Override
    public List<User> getAllUsers(Map<String, String> params) {
        Pageable pageable = PageRequest.of(Integer.parseInt(params.getOrDefault("page", "0")), pageSize);
        String kw = params.getOrDefault("keyword", "");

        Page<User> result  = userRepository.findByUsernameContainingOrderByUsernameAsc(kw ,pageable);
        return result.getContent();
    }

    @Override
    public List<User> getUsersByRole(Map<String, String> params) {
        Pageable pageable = PageRequest.of(Integer.parseInt(params.getOrDefault("page", "0")), pageSize);
        String kw = params.getOrDefault("keyword", "");
        String id = params.get("roleId");

        Page<User> result;
        if (id != null && id != "") {
            result = userRepository.findByRolesCollection_IdAndUsernameContainingOrderByUsernameAsc(id, kw, pageable);
        } else {
            result = userRepository.findByUsernameContainingOrderByUsernameAsc(kw ,pageable);
        }

        return result.getContent();
    }

    @Override
    public User addUser(User u) {
        User savedUser = userRepository.save(u);
        return savedUser;
    }

    @Override
    public User updateUser(String id, User userUpdate) {
        //Optional<User> userOptional = userRepository.findById(id);
        if(userRepository.findById(id).isEmpty()){
            throw  new ResourceNotFoundException();
        }
        userUpdate = userRepository.save(userUpdate);
        return userUpdate;
    }
}
