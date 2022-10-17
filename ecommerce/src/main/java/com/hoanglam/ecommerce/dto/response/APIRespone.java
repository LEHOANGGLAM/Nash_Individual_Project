package com.hoanglam.ecommerce.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIRespone<T> {
    private int totalPage;
    T listResponse;
}
