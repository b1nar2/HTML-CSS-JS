<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>페이지 이동</title>
</head>
<body>

<c:redirect url="https://search.naver.com/search.naver">
	<c:param name="sm" value="top_hty" />
	<c:param name="fbm" value="0" />
	<c:param name="ie" value="utf8" />
	<c:param name="query" value="서울숲" />
</c:redirect>

</body>
</html>