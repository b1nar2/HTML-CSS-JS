package com.javateam.springBootInterceptorValidator.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.javateam.springBootInterceptorValidator.service.DemoInterceptor;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
	
//	@Bean
//    public MessageSource messageSource() {
//        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//        messageSource.setBasename("classpath:/message/message");
//    	messageSource.setDefaultEncoding("UTF-8");
//    	return messageSource;
//	}

	@Bean
	public MessageSource validationMessageSource() {
	    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	    messageSource.setBasename("classpath:/message/book_validation_message");
	    messageSource.setDefaultEncoding("UTF-8");
	    return messageSource;
	}
	
	 @Override
     public Validator getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(validationMessageSource());
        return bean;
     }
	
	// 정적 자원(css, js, image 등) 등록
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		// CSS 경로 등록
		// registry.addResourceHandler("/css/**")
		//	    .addResourceLocations("classpath:/static/css/");
		
		// 모든 정적 자원(js, css, image 등) 경로 등록
		registry.addResourceHandler("/**")
	    		.addResourceLocations("classpath:/static/");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(new DemoInterceptor())
				.addPathPatterns("/**");
				// .order(1);
	} //

}
