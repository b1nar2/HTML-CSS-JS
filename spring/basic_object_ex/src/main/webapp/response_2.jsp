<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML>
<HTML lang="ko">
<HEAD>
<TITLE> response 객체 </TITLE>
</HEAD>
<BODY>

<%
String strSite=request.getParameter("download");

switch (strSite)
// switch(strSite) // JSE 1.7 (X) -> tomcat 7.x (JSE 1.6)
{
	case "1":
		// response.sendRedirect("http://java.sun.com"); // js : location.href=""와 동일한 표현
	    out.println("<script>"+
	    					 "location.href='http://java.sun.com'"+	
	    					 "</script>");
		break;	
	case "2":
		response.sendRedirect("http://tomcat.apache.org");
		break;
	case "3":
		response.sendRedirect("http://www.eclipse.org");
		break;
	default:
		response.sendRedirect("http://www.oracle.com");
		break;
}
%>

</BODY>
</HTML>
