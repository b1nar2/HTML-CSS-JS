package com.javateam.SpringBootJWTAuthGradle.config;

// import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

// https://springdoc.org/#migrating-from-springfox
// https://happy-jjang-a.tistory.com/165

/*
@OpenAPIDefinition(
        info = @Info(title = "App",
                description = "App Api명세",
                version = "v1"))
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
*/
@Configuration
public class SwaggerConfig {

	@Bean
	OpenAPI openAPI() {

		Info info = new Info().version("v1.0.0").title("API 타이틀").description("API Description");

		// SecuritySecheme명
		String jwtSchemeName = "jwtAuth";
		
		// API 요청헤더에 인증정보 포함
		SecurityRequirement securityRequirement 
			= new SecurityRequirement().addList(jwtSchemeName);
		
		// SecuritySchemes 등록
		Components components 
			= new Components().addSecuritySchemes(jwtSchemeName,
					new SecurityScheme()
							.name(jwtSchemeName)
							.type(SecurityScheme.Type.HTTP) // HTTP 방식
							.scheme("bearer")
							.bearerFormat("JWT")); // 토큰 형식을 지정하는 임의의 문자(Optional)

		return new OpenAPI().info(info)
							.addSecurityItem(securityRequirement)
							.components(components);
	}

}