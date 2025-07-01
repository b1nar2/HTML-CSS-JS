<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
java : ${java}<br>
java : ${requestScope.java}<br>
java : <%=request.getAttribute("java") %><br>
java : <%
			out.println(request.getAttribute("java"));
		%>
</body>
</html>