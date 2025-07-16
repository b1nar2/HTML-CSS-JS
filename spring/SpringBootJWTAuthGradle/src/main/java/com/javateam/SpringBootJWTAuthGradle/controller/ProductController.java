package com.javateam.SpringBootJWTAuthGradle.controller;

import com.javateam.SpringBootJWTAuthGradle.data.dto.ChangeProductNameDto;
import com.javateam.SpringBootJWTAuthGradle.data.dto.ProductDto;
import com.javateam.SpringBootJWTAuthGradle.data.dto.ProductResponseDto;
import com.javateam.SpringBootJWTAuthGradle.service.ProductService;

import io.swagger.v3.oas.annotations.Parameter;
//import io.swagger.v3.oas.annotations.Parameters;
//import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value="viewProduct", produces="application/json; charset=UTF-8")
    public ResponseEntity<ProductResponseDto> getProduct(
    		@Parameter(description = "로그인 성공 후 발급 받은 access_token") 
		 		@RequestHeader(value = "x-auth-token", required = true) String xAuthToken,
		 	@Parameter(name="number", description="제품 번호", required = true) @RequestParam("number") long number) {
    	
    	log.info("상품 조회(번호) : " + number); 
    	
        long currentTime = System.currentTimeMillis();
        
        log.info("[getProduct] request Data :: productId : {}", number);
        ProductResponseDto productResponseDto = productService.getProduct(number);

        log.info(
            "[getProduct] response Data :: productId : {}, productName : {}, productPrice : {}, productStock : {}",
            productResponseDto.getNumber(), productResponseDto.getName(),
            productResponseDto.getPrice(), productResponseDto.getStock());
        log.info("[getProduct] Response Time : {}ms", System.currentTimeMillis() - currentTime);
        
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    // https://springdoc.org/#migrating-from-springfox
    // https://springdoc.org/#how-can-i-generate-enums-in-the-generated-description
    // https://swagger.io/docs/specification/describing-parameters/#header-parameters

    @PostMapping(value="registProduct", produces="application/json; charset=UTF-8") // , consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponseDto> createProduct(
    					@Parameter(description = "로그인 성공 후 발급 받은 access_token") 
					 		@RequestHeader(value = "x-auth-token", required = true) String xAuthToken,
    					// @RequestBody(required = true) ProductDto productDto) { // swagger 현재 버전에서 인자 전송 문제 발생 ! 
					 	@Parameter(name="name", description="제품명", required = true) @RequestParam("name") String name,
					 	@Parameter(name="price", description="가격(단가)", required = true) @RequestParam("price") int price,
					 	@Parameter(name="stock", description="재고량", required = true) @RequestParam("stock") int stock) { 
    	// swagger 현재 버전에서 인자 전송 문제 발생 ! (버그 패치)
    	
    	log.info("-------------- 상품 등록 --------------");
    	
    	ProductDto productDto = ProductDto.builder().name(name).price(price).stock(stock).build();
    	
    	log.info("상품 등록 : " + productDto);
        long currentTime = System.currentTimeMillis();
        ProductResponseDto productResponseDto = productService.saveProduct(productDto);

        log.info("[createProduct] Response Time : {}ms", System.currentTimeMillis() - currentTime);
        
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @PutMapping(value="modifyProduct", produces="application/json; charset=UTF-8")
    public ResponseEntity<ProductResponseDto> changeProductName(
		@Parameter(description = "로그인 성공 후 발급 받은 access_token") 
		 	@RequestHeader(value = "x-auth-token", required = true) String xAuthToken,
        // @RequestBody ChangeProductNameDto changeProductNameDto) throws Exception {
		 	@Parameter(name="number", description="제품번호", required = true) @RequestParam("number") long number,
		 	@Parameter(name="name", description="제품명", required = true) @RequestParam("name") String name,
		 	@Parameter(name="stock", description="재고량", required = true) @RequestParam("stock") int stock) throws Exception { 
    	
    	// log.info("상품명 수정 : " + changeProductNameDto);
    	
    	log.info("-------------- 상품 수정 --------------");
    	
    	ChangeProductNameDto changeProductNameDto 
    		= ChangeProductNameDto.builder().number(number).name(name).stock(stock).build();
    	
    	log.info("상품 등록 : " + changeProductNameDto);
    	
        long currentTime = System.currentTimeMillis();
        
        log.info("[changeProductName] request Data :: productNumber : {}, productName : {}",
            changeProductNameDto.getNumber(), changeProductNameDto.getName());

        ProductResponseDto productResponseDto = productService.changeProductName(
							            changeProductNameDto.getNumber(),
							            changeProductNameDto.getName(),
							            changeProductNameDto.getStock());

        log.info(
            "[changeProductName] response Data :: productNumber : {}, productName : {}, productPrice : {}, productStock : {}",
            productResponseDto.getNumber(), productResponseDto.getName(),
            productResponseDto.getPrice(), productResponseDto.getStock());
        
        log.info("[changeProductName] response Time : {}ms",
            System.currentTimeMillis() - currentTime);
        
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    } 

    @DeleteMapping(value="deleteProduct", produces="application/json; charset=UTF-8")
    public ResponseEntity<String> deleteProduct(
    		@Parameter(description = "로그인 성공 후 발급 받은 access_token") 
		 	@RequestHeader(value = "x-auth-token", required = true) String xAuthToken,
		 	@Parameter(name="number", description="제품 번호", required = true) @RequestParam("number") long number) throws Exception {
    	
    	log.info("상품 삭제(번호) : " + number);
    	
        long currentTime = System.currentTimeMillis();
        log.info("[deleteProduct] request Data :: productNumber : {}", number);

        productService.deleteProduct(number);

        log.info("[deleteProduct] response Time : {}ms",
            System.currentTimeMillis() - currentTime);
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }

}