package com.hoanglam.ecommerce.dto.request;

import com.hoanglam.ecommerce.entites.Size;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Data
@Builder
public class ProductRequestDto {
    @NotNull
    private BigDecimal price;
    @NotNull
    private short quantity;
    @NotBlank(message = "title is required")
    private String title;
    private String desciption;
    private Collection<String> categoryIds;
    private Collection<String> sizeIds;
    private List<String> images;
}
