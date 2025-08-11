<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page session="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="elfunc" uri="/WEB-INF/tlds/el-functions.tld" %>
<%
	java.util.Date today = new java.util.Date();
	request.setAttribute("today", today);
	
	java.time.LocalDate today2 = java.time.LocalDate.now();
	request.setAttribute("today2", today2);
%>
<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>EL 함수 호출</title>
</head>

<body>

오늘은 ${elfunc:dateFormat(today)} 입니다. 
<hr>
오늘은 <fmt:formatDate value="${today}" pattern="yyyy년 M월 d일" />입니다.
<hr>
오늘은 ${today2} 입니다.
<hr>
<fmt:parseDate var="tmp" value="${today2}" pattern="yyyy-M-d"/>
오늘은 <fmt:formatDate value="${tmp}" pattern="yyyy년 M월 d일" />입니다.
<hr>
<!-- 아래의 경우는 에러 유발됨 -->
<%-- 오늘은 <fmt:formatDate value="${today2}" pattern="yyyy년 M월 d일" />입니다. --%>

</body>
</html>