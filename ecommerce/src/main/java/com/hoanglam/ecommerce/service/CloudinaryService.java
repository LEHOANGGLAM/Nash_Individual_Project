package com.hoanglam.ecommerce.service;

import com.cloudinary.Cloudinary;
import org.springframework.web.multipart.MultipartFile;


public interface CloudinaryService {
    String uploadImg(MultipartFile file, Cloudinary cloudinary);
}
