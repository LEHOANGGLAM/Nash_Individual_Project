package com.hoanglam.ecommerce.service.impl;

import com.hoanglam.ecommerce.entites.Size;
import com.hoanglam.ecommerce.repository.CategoryRepository;
import com.hoanglam.ecommerce.repository.SizeRepository;
import com.hoanglam.ecommerce.service.CategoryService;
import com.hoanglam.ecommerce.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeServiceImpl implements SizeService {
    @Autowired
    private SizeRepository sizeRepository;

    @Override
    public List<Size> gettAllSizes() {
        return sizeRepository.findAll();
    }
}
