<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="date" class="java.util.Date"/>

<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>JSTL fmt 라이브러리 사용 예제</title>
</head>

<body>

<%-- <fmt:setLocale value="en_US"/> --%>
<fmt:setLocale value="ko_KR"/>

<!-- 기본적으로 basename은 test.properties 메시지 파일을 의미합니다.
     locale(국제화) 정보에 따라  한국이면 test_ko.properties를 검색하고
           미국이면 test_en.properties를 검색합니다.
           만약 test_en.properties이 없다면 test_en.properties에서 메시지를 검색합니다. 
           파일은 자바 소스가 들어가는 패키지(package)에 위치시킵니다.-->
           
<fmt:bundle basename="msg.test"> 
	<fmt:message key="name"/><br>
	<fmt:message key="say"/><br>
	<fmt:message key="say2">
		<fmt:param value="고길동"/>
	</fmt:message>
</fmt:bundle>

<p>
<fmt:formatNumber value="50000" type="currency"/><br>
<fmt:formatNumber value="0.15" type="percent"/><br>
<fmt:formatNumber value="500567300" pattern="###,###,###"/><p>

<fmt:formatDate value="${date}" type="date"/><br>
<fmt:formatDate value="${date}" type="time"/><br>
<fmt:formatDate value="${date}" type="both"/><p>

<fmt:formatDate value="${date}" type="both" timeStyle="short" dateStyle="short"/><br>
<fmt:formatDate value="${date}" type="both" timeStyle="long" dateStyle="long"/><br>

</body>
</html>