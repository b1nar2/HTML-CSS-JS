<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>timeZone 태그 사용</title>
</head>
<body>

<c:set var="now" value="<%= new java.util.Date() %>" />

<fmt:formatDate value="${now}" type="both" 
                dateStyle="full" timeStyle="full" />

<br>

<!-- 타임존(TimeZone) 상수값 : https://docs.oracle.com/javase/8/docs/api/java/time/ZoneId.html#SHORT_IDS -->

<fmt:timeZone value="America/Los_Angeles">

<fmt:formatDate value="${now}" type="both" 
                dateStyle="full" timeStyle="full" />
</fmt:timeZone>

</body>
</html>