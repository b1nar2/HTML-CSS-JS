package com.javateam.SpringBootJWTAuthGradle.mock;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import org.springframework.security.test.context.support.WithSecurityContext;

// https://docs.spring.io/spring-security/reference/6.4/servlet/test/method.html#test-method-withmockuser
	
@Retention(RUNTIME)
@WithSecurityContext(factory = WithMockCustomUserSecurityContextFactory.class)
public @interface WithMockCustomUser {
	
	String username() default "mockUSer"; // 테스트용 모의(목:Mock) 사용자 계정 
	
	String password() default "#Abcd1234"; // 테스트용 모의(목:Mock) 사용자 패쓰워드
	
	String[] roles() default { "USER" }; // 테스트용 모의(목:Mock) 사용자 인가 권한 

}