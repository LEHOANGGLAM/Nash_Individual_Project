package com.hoanglam.ecommerce.service;

import com.hoanglam.ecommerce.dto.request.CommentRequestDto;
import com.hoanglam.ecommerce.entites.Comment;
import com.hoanglam.ecommerce.entites.Product;

public interface CommentService {
    Comment createComment(CommentRequestDto c);
}
