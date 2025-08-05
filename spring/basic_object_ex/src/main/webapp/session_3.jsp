<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.text.*" %>
<%
	SimpleDateFormat f = new SimpleDateFormat("yyyy-M-d hh:mm:ss");
%>
<!DOCTYPE HTML>
<HTML lang="ko">
<HEAD>
<TITLE> session 객체 </TITLE>
</HEAD>
<BODY>

<%
long lastTime=session.getLastAccessedTime();
long createTime=session.getCreationTime();
long useTime=(lastTime - createTime) / 1000;
%>
<%=f.format(lastTime) %><br>
<%=f.format(createTime) %><br>

<%=useTime %>초 동안 사이트에 접속되어 있습니다.

</BODY>
</HTML>
