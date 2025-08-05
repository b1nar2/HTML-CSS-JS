<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML>
<HTML lang="ko">
<HEAD>
<TITLE> response 객체 </TITLE>
</HEAD>
<BODY>

<%
String strTemp="response_5.jsp?id=apple&pw=pineapple";

out.println(strTemp + "<BR>");
out.println("encodeURL() : " + response.encodeURL(strTemp) + "<BR>");
out.println("encodeRedirect() : " + response.encodeRedirectURL(strTemp) + "<BR>");
%>

</BODY>
</HTML>