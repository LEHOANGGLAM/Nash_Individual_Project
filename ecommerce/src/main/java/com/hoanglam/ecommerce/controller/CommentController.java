package com.hoanglam.ecommerce.controller;

import com.hoanglam.ecommerce.dto.request.CommentRequestDto;
import com.hoanglam.ecommerce.entites.Comment;
import com.hoanglam.ecommerce.entites.Product;
import com.hoanglam.ecommerce.repository.CartRepository;
import com.hoanglam.ecommerce.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("")
public class CommentController {
    @Autowired
    public CommentService commentService;

    @PostMapping("/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public Comment createComment(@Valid @RequestBody CommentRequestDto c){
        return commentService.createComment(c);
    }
}
