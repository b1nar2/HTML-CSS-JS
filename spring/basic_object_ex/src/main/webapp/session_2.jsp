<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML>
<HTML lang="ko">
<HEAD>
<TITLE> session 객체 </TITLE>
</HEAD>
<BODY>

<%
String ID=request.getParameter("strID");
if (session.getAttribute("sessID") == null)
	session.setAttribute("sessID", ID); // 세션 변수화
	
session.setMaxInactiveInterval(5);	
	
String sessID=(String)session.getAttribute("sessID"); // 세션 변수화

String strSessionID=session.getId();
int intTime=session.getMaxInactiveInterval();
%>

<B> <%=sessID %> 님 환영합니다. </B> <P> 
세션 ID : <%=strSessionID %> <BR>
세션 시간 : <%=intTime %>

</BODY>
</HTML>