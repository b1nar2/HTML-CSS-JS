<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML>
<HTML lang="ko">
<HEAD>
<TITLE> application 객체 </TITLE>
</HEAD>
<BODY>

<TABLE border=1>
<TR>
	<TD><B> Server Information </B></TD>
    	<TD><B> 값 </B></TD>
</TR>

<% 
out.println ("<TR><TD>서버정보</TD><TD>" + application.getServerInfo() + "</TD></TR>");
out.println ("<TR><TD>MIME 타입</TD><TD>" + application.getMimeType("application_1.jsp") + "</TD></TR>");
out.println ("<TR><TD>URL 정보</TD><TD>" + application.getResource("/") + "</TD></TR>");
out.println ("<TR><TD>로컬경로</TD><TD>" + application.getRealPath("/") + "</TD></TR>");
out.println ("<TR><TD>컨텍스트정보</TD><TD>" + application.getContext("/") + "</TD></TR>");
%>
</TABLE>

</BODY>
</HTML>
