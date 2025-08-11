<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>
<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>페이지 삽입 : &lt;c:import&gt;</title>
</head>
<body>

<p style="background:cyan">페이지(상단)</p>
<hr>

<c:import url="https://search.naver.com/search.naver">
	<c:param name="sm" value="top_hty" />
	<c:param name="fbm" value="0" />
	<c:param name="ie" value="utf8" />
	<c:param name="query" value="서울숲" />
</c:import>

<hr>
<p style="background:pink">페이지(하단)</p>

</body>
</html>