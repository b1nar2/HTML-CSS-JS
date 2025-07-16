package com.javateam.SpringBootJWTAuthGradle.service;


import com.javateam.SpringBootJWTAuthGradle.data.dto.ProductDto;
import com.javateam.SpringBootJWTAuthGradle.data.dto.ProductResponseDto;

public interface ProductService {

    ProductResponseDto getProduct(Long number);

    ProductResponseDto saveProduct(ProductDto productDto);

    ProductResponseDto changeProductName(long number, String name, int stock) throws Exception;

    void deleteProduct(Long number) throws Exception;
    
    // 추가
    ProductResponseDto changeProduct(ProductDto productDto) throws Exception;

}