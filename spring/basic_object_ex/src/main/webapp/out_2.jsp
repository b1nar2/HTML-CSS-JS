<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML>
<HTML lang="ko">
<HEAD>
<TITLE> out 객체 </TITLE>
</HEAD>
<BODY>

<B> 현재 Buffer 상태 </B><BR><BR>

<%
int intTotal=out.getBufferSize();
int intRemain=out.getRemaining();

out.println("Buffer 전체 크기 : " + intTotal + "<BR>");
out.println("Buffer 현재 사용량 : " + intRemain);
%>

</BODY>
</HTML>