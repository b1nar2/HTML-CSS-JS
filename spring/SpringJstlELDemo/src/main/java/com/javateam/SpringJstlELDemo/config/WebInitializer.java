package com.javateam.SpringJstlELDemo.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
		implements WebApplicationInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {

		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		
		return new Class[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		
		return new String[] { "/" };
   	    // ex) return new String[] { "*.html", "*.json", "*.do" };
	}

}
