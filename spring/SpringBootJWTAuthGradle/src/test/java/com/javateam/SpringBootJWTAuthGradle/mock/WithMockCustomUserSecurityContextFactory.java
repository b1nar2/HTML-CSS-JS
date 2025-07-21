package com.javateam.SpringBootJWTAuthGradle.mock;

import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import org.springframework.stereotype.Component;

import com.javateam.SpringBootJWTAuthGradle.data.entity.User;

@Component
public class WithMockCustomUserSecurityContextFactory 
			implements WithSecurityContextFactory<WithMockCustomUser> {
	
    @Override
    public SecurityContext createSecurityContext(WithMockCustomUser annotation) {
    	
    	// 생성할 모의(목:Mock) 사용자 객체의 아이디/패쓰워드
    	User user = User.builder()
    				.userId(annotation.username())
    				.password(annotation.password())
    				.build();

    	// Spring Security Context 객체 생성
        final SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

        // 사용자 계정 목 객체에 따른 로그인 인증 토큰(authentication Token) 생성.
        // 원래는 여러 개의 인가(role : USER, ADMIN 등) 권한을 가질 수 있으나 편의상 1개만 선정하였음.
        final UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(user, 
                										  user.getPassword(), 
                										  List.of(new SimpleGrantedAuthority(annotation.roles()[0])));

        // 인증 토큰을 통한 인증 설정 
        securityContext.setAuthentication(authenticationToken);

        return securityContext;
    }
    
}