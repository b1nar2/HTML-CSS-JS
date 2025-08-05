<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>
<%
     request.setCharacterEncoding("UTF-8"); // 한글처리(POST 방식에 한함)
	// 추후의 경우 filter/servlet를 통해서 처리
%>
<!DOCTYPE HTML>
<HTML lang="ko">
<HEAD>
<TITLE> request 객체 </TITLE>
</HEAD>
<BODY>

<%
String id=request.getParameter("id");
// 아래의 get방식 인코(encoding) 처리는 예전 방식(아직도 이러한 방식을 사용하는 곳 있을 수 있습니다.)
// id=new String(id.getBytes("ISO-8859-1"), "UTF-8");
// Tomcat 8 이상에서는 get방식 인자 한글 변환 처리됨
String pw=request.getParameter("pw");

out.println("아이디 : " + id + "<BR>");
out.println("비밀번호 : " + pw);
%><BR>
아이디 : <%=id %><BR>
</BODY>
</HTML>