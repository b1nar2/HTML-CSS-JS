<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML>
<HTML lang="ko">
<HEAD>
<TITLE> config 객체 </TITLE>
</HEAD>
<BODY>

<% 
out.println("서블릿 이름 : " + config.getServletName()+"<BR>"); 
ServletContext context=config.getServletContext(); 
out.println("서버 메이저 버전 : "+context.getMajorVersion() + "<BR>");
out.println("서버 마이너 버전 : "+context.getMinorVersion());  
%> 

</BODY>
</HTML>