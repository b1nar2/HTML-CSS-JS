<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>

<% request.setCharacterEncoding("utf-8");%>
<%
    String id = request.getParameter("id");
	String passwd  = request.getParameter("passwd");
	
	Map<String, String> members = new HashMap<>();
	members.put("abcd", "1234");
	members.put("java", "1111");
	members.put("python", "5678");
	
	String msg = "";
	
	// 회원 아이디가 존재하면...
	if (members.containsKey(id) == true) {
		
		if (members.get(id).equals(passwd)) { // 패쓰워드가 맞으면...
			
			Cookie cookie = new Cookie("id", id);
			cookie.setMaxAge(20*60);
			response.addCookie(cookie);
			response.sendRedirect("cookieMain.jsp");
			
		} else {
			
			out.println("<script>" 
					  + " alert('패쓰워드가 맞지 않습니다.');"
				      + " history.go(-1);"
					  + "</script>");
		}
		
	} else { // 회원 아이디가 존재하지 않으면...
		
		out.println("<script>" 
				  + " alert('아이디가 존재하지 않습니다.');"
			      + " history.go(-1);"
				  + "</script>");
	} //
%>	