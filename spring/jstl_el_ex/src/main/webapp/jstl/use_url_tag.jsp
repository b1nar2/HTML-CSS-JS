<%@ page contentType="text/html; charset=UTF-8" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
</head>
<body>
<c:url value="http://search.daum.net/search" var="searchUrl">
	<c:param name="w" value="blog" />
	<c:param name="q" value="공원" />
</c:url>

contextPath : <%=request.getContextPath() %><br>
contextPath : ${pageContext.request.contextPath}<br>
contextPath : ${requestScope.contextPath}<br> <!-- X -->

<ul>
	<li><a href="${searchUrl}">링크1</a></li>
	<li><a href="<c:url value="/jstl/use_if_tag.jsp" />">링크2</a></li>
	<li><a href="<%=request.getContextPath() %>/jstl/use_if_tag.jsp">링크2-1</a></li>
	<li><a href="<c:url value="./use_if_tag.jsp" />">링크3</a></li>
	<li><a href="<c:url value="../use_if_tag.jsp" />">링크4</a></li>
	<!-- 상대 경로 : 상위 경로(..), 자신의 경로(.) -->
</ul>

</body>
</html>