package com.hoanglam.ecommerce.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.hoanglam.ecommerce.service.CloudinaryService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {
    @Override
    public String uploadImg(MultipartFile file, Cloudinary cloudinary) {
        try {
            Map res = cloudinary.uploader().upload(file.getBytes(),
                    ObjectUtils.asMap("resource_type", "auto"));
            return res.get("secure_url").toString();
        } catch (IOException ex) {
            return "False";
        }
    }
}
