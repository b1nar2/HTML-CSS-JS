<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page info="page 객체 예제" %>
<!DOCTYPE HTML>
<HTML lang="ko">
<HEAD>
<TITLE> page 객체 </TITLE>
</HEAD>
<BODY>

<% 
String strInfo=this.getServletInfo();
out.println("Info 정보 : " + strInfo);
%> 

</BODY>
</HTML>