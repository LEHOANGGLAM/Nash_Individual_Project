package com.hoanglam.ecommerce.dto.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Data
public class DeleteResponseDto {
    private final String message;
    private final int statusCode;
    private final HttpStatus httpStatus;
}
