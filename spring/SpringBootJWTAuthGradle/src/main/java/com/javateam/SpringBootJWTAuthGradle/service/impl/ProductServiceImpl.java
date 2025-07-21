package com.javateam.SpringBootJWTAuthGradle.service.impl;


import com.javateam.SpringBootJWTAuthGradle.data.dto.ProductDto;
import com.javateam.SpringBootJWTAuthGradle.data.dto.ProductResponseDto;
import com.javateam.SpringBootJWTAuthGradle.data.entity.Product;
import com.javateam.SpringBootJWTAuthGradle.data.repository.ProductRepository;
import com.javateam.SpringBootJWTAuthGradle.service.ProductService;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service("productService")
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDto getProduct(Long number) {
    	
        log.info("[getProduct] product number : {}", number);
        Product product = productRepository.findById(number).orElseThrow(RuntimeException::new);

        log.info(
            "[getProduct] found Product :: productId : {}, productName : {}, productPrice : {}, productStock : {}",
            product.getNumber(), product.getName(), product.getPrice(), product.getStock());

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(product.getNumber());
        productResponseDto.setName(product.getName());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setStock(product.getStock());

        return productResponseDto;
    }

    @Override
    public ProductResponseDto saveProduct(ProductDto productDto) {
        log.info("[saveProduct] productName : {}", productDto.getName());
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        Product savedProduct = productRepository.save(product);
        log.info("[saveProduct] saved ProductId : {}", savedProduct.getNumber());

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(savedProduct.getNumber());
        productResponseDto.setName(savedProduct.getName());
        productResponseDto.setPrice(savedProduct.getPrice());
        productResponseDto.setStock(savedProduct.getStock());

        return productResponseDto;
    }

    @Override
    public ProductResponseDto changeProductName(long number, String name, int stock) {
    	
        log.info("[changeProductName] request productId : {}", number);
        
        Product foundProduct = productRepository.findById(number)
            .orElseThrow(RuntimeException::new);
        log.info("[changeProductName] found Product's name : {}", foundProduct.getName());
        
        foundProduct.setName(name);
        log.info("[changeProductName] change Product's name : {}", name);
        
        foundProduct.setStock(stock);
        log.info("[changeProductName] change Product's 수량 : {}", stock);

        Product changedProduct = productRepository.save(foundProduct);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(changedProduct.getNumber());
        productResponseDto.setName(changedProduct.getName());
        productResponseDto.setPrice(changedProduct.getPrice());
        productResponseDto.setStock(changedProduct.getStock());

        return productResponseDto;
    }

    @Override
    public void deleteProduct(Long number) {
        log.info("[deleteProduct] delete ProductId : {}", number);
        productRepository.deleteById(number);
    }

	@Override
	public ProductResponseDto changeProduct(ProductDto productDto) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
