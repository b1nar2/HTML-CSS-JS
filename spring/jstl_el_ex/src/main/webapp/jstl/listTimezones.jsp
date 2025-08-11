<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>시간대 목록</title>
</head>
<body>

<c:forEach var="id" items="<%= java.util.TimeZone.getAvailableIDs() %>">
    ${id}<br />
</c:forEach>

</body>
</html>
