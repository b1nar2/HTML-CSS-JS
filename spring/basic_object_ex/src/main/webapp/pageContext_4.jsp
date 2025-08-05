<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML>
<HTML lang="ko">
<HEAD>
<TITLE> pageContext 객체 </TITLE>
</HEAD>
<BODY>

<%
pageContext.setAttribute("pageValue","pageContext Example");
Object code=pageContext.getAttribute("pageValue");

if (code != null){
          String strValue=(String)code;
          out.println("page Value=" + strValue + "<BR>");
}
else{
          out.println("Not found!!");
}
%>

</BODY>
</HTML>