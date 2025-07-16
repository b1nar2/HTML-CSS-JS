package com.javateam.SpringBootJWTAuthGradle.config.security;

import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    
//  private final JwtTokenProvider jwtTokenProvider;
    
    private final NewJwtTokenProvider newJwtTokenProvider;

//  public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
//      this.jwtTokenProvider = jwtTokenProvider;
//  }
    
    public JwtAuthenticationFilter(NewJwtTokenProvider jwtTokenProvider) {
        this.newJwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest servletRequest,
		        HttpServletResponse servletResponse,
		        FilterChain filterChain) throws ServletException, IOException {
    	
        // String token = jwtTokenProvider.resolveToken(servletRequest);
    	String token = newJwtTokenProvider.resolveToken(servletRequest);
    	
        LOGGER.info("[doFilterInternal] token 값 추출 완료. token : {}", token);

        LOGGER.info("[doFilterInternal] token 값 유효성 체크 시작");
       
//        if (token != null && jwtTokenProvider.validateToken(token)) {
        if (token != null && newJwtTokenProvider.validateToken(token)) {
        	
            // Authentication authentication = jwtTokenProvider.getAuthentication(token);
        	Authentication authentication = newJwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            LOGGER.info("[doFilterInternal] token 값 유효성 체크 완료");
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}