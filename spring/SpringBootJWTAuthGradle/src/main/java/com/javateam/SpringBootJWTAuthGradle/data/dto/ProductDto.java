package com.javateam.SpringBootJWTAuthGradle.data.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
//@Schema(description = "ProductDto")
public class ProductDto {
	
	//@Schema(description = "상품명", example = "사과")
    private String name;

	//@Schema(description = "가격(단가)", example = "3000")
    private int price;

	//@Schema(description = "재고량", example = "10")
    private int stock;

}
