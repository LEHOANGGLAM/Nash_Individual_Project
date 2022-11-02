package com.hoanglam.ecommerce.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@SpringBootConfiguration
@RequiredArgsConstructor
public class AppConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Cloudinary cloudinaryConfig() {
        Cloudinary cloudinary
                = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dmstiyczr",
                "api_key", "624246788974614",
                "api_secret", "-Uz1msvYALJyI96oaJRmC_28DjU",
                "secure", true));
        return cloudinary;
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        return resolver;
    }
}
