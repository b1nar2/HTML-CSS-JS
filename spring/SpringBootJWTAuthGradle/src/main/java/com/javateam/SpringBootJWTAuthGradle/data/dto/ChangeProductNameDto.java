package com.javateam.SpringBootJWTAuthGradle.data.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
//import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChangeProductNameDto implements Serializable {
	
    private static final long serialVersionUID = 6396838968525010502L;

	private long number;

    private String name;
    
    private int stock;

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "ChangeProductNameDto [number=" + number + ", name=" + name + ", stock=" + stock + "]";
	}

}
