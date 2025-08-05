<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML>
<HTML lang="ko">
<HEAD>
<TITLE> pageContext 객체 </TITLE>
</HEAD>
<BODY>

<% 
out.println("회원님 환영합니다.<BR>");
out.flush();
// pageContext.include("/pageContext_3.jsp");
// pageContext.include("./pageContext_3.jsp");
pageContext.include("pageContext_3.jsp");
out.println("저희 사이트를 애용해 주셔서 감사합니다.");
%>

</BODY>
</HTML>