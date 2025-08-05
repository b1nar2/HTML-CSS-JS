<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML>
<HTML lang="ko">
<HEAD>
<TITLE> Request 객체 </TITLE>
</HEAD>
<BODY>

<TABLE border=1>
<TR>
	<TD><B> Client &amp; Server Information </B></TD>
    	<TD><B> 값 </B></TD>
</TR>

<% 
out.println ("<TR><TD>인코딩 방식</TD><TD>" + request.getCharacterEncoding() + "</TD></TR>");
out.println ("<TR><TD>MIME 타입</TD><TD>" + request.getContentType() + "</TD></TR>");
out.println ("<TR><TD>요청 정보 길이</TD><TD>" + request.getContentLength() + "</TD></TR>");
out.println ("<TR><TD>Header 이름</TD><TD>" + request.getHeaderNames()+ "</TD></TR>");
out.println ("<TR><TD>웹 전달 경로</TD><TD>" + request.getPathInfo() + "</TD></TR>");
out.println ("<TR><TD>클라이언트 이름</TD><TD>" + request.getRemoteHost() + "</TD></TR>");
out.println ("<TR><TD>클라이언트 IP 주소</TD><TD>" + request.getRemoteAddr()+ "</TD></TR>");
out.println ("<TR><TD>클라이언트 URL 경로</TD><TD>" + request.getRequestURL()+ "</TD></TR>");
out.println ("<TR><TD>전송 방식</TD><TD>" + request.getMethod() + "</TD></TR>");
out.println ("<TR><TD>프로토콜 이름</TD><TD>" + request.getProtocol() + "</TD></TR>");
out.println ("<TR><TD>서버 이름</TD><TD>" + request.getServerName() + "</TD></TR>");
out.println ("<TR><TD>서버 포트번호</TD><TD>" + request.getServerPort()+ "</TD></TR>");
%>

</TABLE>

</BODY>
</HTML>