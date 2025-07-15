package com.javateam.SpringBootSecurityMyBatisThymeleaf.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users {
	
	private String username;
	private String password;
	private int enabled;
		
}