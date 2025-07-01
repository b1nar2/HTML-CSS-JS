package com.javateam.control;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface CommandAction {
	
	public String requestPro(HttpServletRequest request, 
							 HttpServletResponse response) throws Throwable;
	
}
