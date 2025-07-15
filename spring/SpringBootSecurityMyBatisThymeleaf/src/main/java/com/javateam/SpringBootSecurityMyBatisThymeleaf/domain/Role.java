package com.javateam.SpringBootSecurityMyBatisThymeleaf.domain;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
public class Role implements GrantedAuthority {

	private static final long serialVersionUID = 7464267597005842862L;
	
	private String username;
	private String role;

	// 이 메서드에서 주의할 것 ! => lombok 사용시 주의
	@Override
	public String getAuthority() {
		return this.role; 
	}
   
}