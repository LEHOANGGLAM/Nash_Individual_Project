package com.hoanglam.ecommerce.dto.response;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public class DeleteResponseDto {
    private final String message;
    private final int statusCode;
    private final HttpStatus httpStatus;
}
