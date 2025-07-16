package com.javateam.SpringBootJWTAuthGradle.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.extern.slf4j.Slf4j;

/**
 * 어플리케이션 보안 설정
 *
 * @author javateam
 * @version 1.0.0
 */
@Configuration
@EnableWebSecurity // Spring Security에 대한 디버깅 모드를 사용하기 위한 어노테이션 (default : false)
@Slf4j
public class SecurityConfiguration {

	// private final JwtTokenProvider jwtTokenProvider;
	private final NewJwtTokenProvider newJwtTokenProvider;

    public SecurityConfiguration(NewJwtTokenProvider newJwtTokenProvider) {
        this.newJwtTokenProvider = newJwtTokenProvider;
    }
    
    // security 적용 예외 URL 등록
    // Swagger 페이지 접근에 대한 제외(열외) 처리
    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
    	
    	return (web) -> web.ignoring()
    					   .requestMatchers("/",
    							   			"/v3/api-docs/**",    							   			
    							   			"/favicon.ico",
    							   			"/swagger-ui/**", 
    							   			"/swagger-resources/**",
							   				"/webjars/**", 
							   				"/sign-api/exception");
    }

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		log.info("Spring Security SecurityFilterChain(filterChain)");
		
        http.httpBasic((httpBasic) -> httpBasic.disable()); // REST API는 UI를 사용하지 않으므로 기본설정을 비활성화
        
        // http.csrf((csrf)->csrf.disable()); // REST API는 csrf 보안이 필요 없으므로 비활성화
        http.cors(Customizer.withDefaults()).csrf(AbstractHttpConfigurer::disable);
            
        http.sessionManagement(sessionManagement ->
							        sessionManagement.sessionCreationPolicy(
						                SessionCreationPolicy.STATELESS)); // JWT Token 인증방식으로 세션은 필요 없으므로 비활성화

            
        http.authorizeHttpRequests(authorizeHttpRequests ->  
									authorizeHttpRequests
										.requestMatchers("/sign-api/sign-in", 
														 "/sign-api/sign-up", 
														 "/home",
								                	     "/sign-api/exception").permitAll() // 가입 및 로그인 주소는 허용
							            .requestMatchers(HttpMethod.GET, "/product/**").permitAll() // product로 시작하는 Get 요청은 허용
							            .requestMatchers("**exception**").permitAll()
							            .anyRequest().hasRole("ADMIN")); 
    								// 나머지 요청은 인증된 ADMIN만 접근 가능) // 요청에 대한 사용권한 체크
            
        http.exceptionHandling(handler ->
    							handler.accessDeniedHandler(new CustomAccessDeniedHandler())
        							   .authenticationEntryPoint(new CustomAuthenticationEntryPoint()));

        // .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
        http.addFilterBefore(new JwtAuthenticationFilter(newJwtTokenProvider),
        					 UsernamePasswordAuthenticationFilter.class); 
        					// JWT Token 필터를 id/password 인증 필터 이전에 추가
        
        return http.build();
    }

}
