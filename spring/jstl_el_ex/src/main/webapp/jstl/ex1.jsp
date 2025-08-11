<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko-kr">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%  // 인자 생성
	pageContext.setAttribute("test", "&lt;Hello&nbsp;&copy;&reg;&nbsp;&nbsp;JSTL!&gt;");
	// request.setAttribute("test", "Hello JSTL!");	
	
// 	out.println("test-1 : " + request.getAttribute("test") + "<br>");
	out.println("test-1 : " + pageContext.getAttribute("test") + "<br>");
%>
test-2 : <%= request.getAttribute("test") %><br>
test-2-2 : <%= pageContext.getAttribute("test") %><br>
test-3 : ${requestScope.test}<br>
test-3-2 : ${pageScope.test}<br>
test-4 : ${test}<br> <!-- 공용(4개의 Scope 내장객체 공용) -->
test-5 : <c:out value='<%=pageContext.getAttribute("test") %>' escapeXml="true"/><br>
test-6 : <c:out value="${pageScope.test}" escapeXml="false"/><br>

</body>
</html>