<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML>
<HTML lang="ko">
<HEAD>
<TITLE> application 객체  </TITLE>
</HEAD>
<BODY>

<%
application.setAttribute("Name", "홍길동");
application.setAttribute("Age", "20세");
application.setAttribute("Email", "abcd@abcd.com");
application.setAttribute("Job", "Programmer");
application.setAttribute("Hobby", "독서");
%>

이 름  : <%=application.getAttribute("Name") %> <BR>
나 이  : <%=application.getAttribute("Age") %> <BR>
이메일 : <%=application.getAttribute("Email") %> <BR>
직 업  : <%=application.getAttribute("Job") %> <BR>
취 미  : <%=application.getAttribute("Hobby") %>

</BODY>
</HTML>
