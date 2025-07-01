package com.javateam.action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.javateam.control.CommandAction;

public class Content1Action implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		String page = "content1.jsp";
		return page; // 이동할 페이지
	}

}
