<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko-kr">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String str = "jsp";
	pageContext.setAttribute("str", "servlet");
%>
<c:set var="str" value="spring" /> 
<!-- 변수값 재할당(대입) : setAttribute()생성한 것과 동일 변수로 간주 -->
<%
	pageContext.setAttribute("str", "jstl");
%>

str-1 : <%=str %><br>
str-2 : <%=pageContext.getAttribute("str") %><br>
str-1-2 : ${pageScope.str}<br>
str-1-3 : ${str}<br>

</body>
</html>