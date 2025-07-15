/**
 * 
 */
package com.javateam.SpringBootSecurityMyBatisThymeleaf.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
/**
 * @author javateam
 *
 */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.Customizer;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.web.AuthenticationEntryPoint;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.config.annotation.web.configurers.PasswordManagementConfigurer;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import com.javateam.SpringBootSecurityMyBatisThymeleaf.service.CustomProvider;

import lombok.extern.slf4j.Slf4j;

// spring & thymeleaf : 
// https://www.thymeleaf.org/doc/articles/springsecurity.html

@Configuration
@EnableWebSecurity
@Slf4j
public class WebSecurityConfig {

	@Autowired
	CustomProvider customProvider;
	
	// @Autowired
    // private final AuthenticationEntryPoint entryPoint;	// 추가

//	@Bean
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		return (AuthenticationManager) this.customProvider;
//	}

	private UserDetailsService userDetailsService;

	private DataSource dataSource;

	// public WebSecurityConfig(UserDetailsService userDetailsService, DataSource dataSource, AuthenticationEntryPoint entryPoint) {
	public WebSecurityConfig(UserDetailsService userDetailsService, DataSource dataSource) {

		log.info("생성자 주입 wiring");
		this.dataSource = dataSource;
		this.userDetailsService = userDetailsService;
		// this.entryPoint = entryPoint;
	}

	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	// @Order(1)
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.userDetailsService(userDetailsService);
		
		http.authenticationProvider(customProvider);
		
		/////////////////////////////////////////////////////////////////////////////////////////////

		// 동일 출처 정책(Same-origin policy) 
		// https://developer.mozilla.org/ko/docs/Web/Security/Same-origin_policy
		// https://ko.wikipedia.org/wiki/%EB%8F%99%EC%9D%BC-%EC%B6%9C%EC%B2%98_%EC%A0%95%EC%B1%85
		// https://docs.spring.io/spring-security/reference/servlet/exploits/headers.html#servlet-headers-frame-options
		
		http
			.headers(headers -> headers
				.frameOptions(frameOptions -> frameOptions
					.sameOrigin()
				)
			);
		
		// 주의) 코드 파편화 심한 구간 
		// https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/config/annotation/web/builders/HttpSecurity.html#authorizeHttpRequests(org.springframework.security.config.Customizer)
		
		// http.authorizeRequests()
		// http.authorizeHttpRequests()
		
		http.authorizeHttpRequests((authorizeHttpRequests) ->  
									authorizeHttpRequests
									// 해당 url을 허용한다.
									.requestMatchers("/resources/**", "/loginError", "/join", "/joinAction", 
													 "/login/idCheck", "/login")
									.permitAll()
									// admin 폴더에 경우 admin(ROLE_ADMIN) 롤이 있는 회원에게만 허용
									// .antMatchers("/admin/**").hasAnyRole("ROLE_ADMIN")
									.requestMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN")
									// user 폴더에 경우 user(ROLE_USER) 롤이 있는 회원에게만 허용
									.requestMatchers("/user/**").hasAnyAuthority("ROLE_USER")
																.anyRequest().authenticated());

		/////////////////////////////////////////////////////////////////////////////////////////////////
		//
		// csrf(Cross-Site Request Forgery) token
		// : https://developer.mozilla.org/ko/docs/Glossary/CSRF
		// : https://namu.wiki/w/CSRF
		// : https://docs.spring.io/spring-security/reference/servlet/exploits/csrf.html#csrf-components
		
		// http.csrf(Customizer.withDefaults());
		// http.csrf((csrf)->csrf.csrfTokenRepository(new HttpSessionCsrfTokenRepository()));
		http.csrf((csrf)->csrf.disable()); // csrf 토큰 미사용
		
		// - login : https://docs.spring.io/spring-security/reference/migration-7/configuration.html#_use_the_lambda_ds
		// - logout : https://docs.spring.io/spring-security/reference/servlet/authentication/logout.html#logout-java-configuration

		// 로그인/로그아웃 (인증) 처리
		http
			.formLogin(formLogin -> formLogin
						.loginPage("/login") // 로그인 이후 주소
							.usernameParameter("username") // 아이디
							.passwordParameter("password") // 패쓰워드
							.defaultSuccessUrl("/myPage") // 로그인 성공시 이동 주소
						.failureUrl("/loginError") // 로그인 에러 처리
						// .failureHandler(new CustomAuthenticationFailure()) // 로그인 실패 핸들러
						.permitAll())
			// logout 핸들링(처리)
			.logout((logout) -> logout
				        .logoutSuccessUrl("/myPage") // 로그아웃 이후 이동 주소
				        // .invalidateHttpSession(true)
				        .permitAll()
				    );
		
		// 예외처시 해당 url로 이동
		http.exceptionHandling(handler -> handler.accessDeniedPage("/403")); 

		return http.build();
	}

}