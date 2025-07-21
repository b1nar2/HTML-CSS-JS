package com.javateam.SpringBootJWTAuthGradle.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder // 추가
public class ProductResponseDto {

    private Long number;
	// private BigDecimal number;

    private String name;

    private int price;

    private int stock;

}
