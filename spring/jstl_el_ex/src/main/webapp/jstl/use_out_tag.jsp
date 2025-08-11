<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import = "java.io.IOException, java.io.FileReader" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>&lt;c:out&gt;</title>
</head>
<body>

<!-- xml 태그 및 & 특수문 자 표기에서의 차이 -->
<c:set var="temp" value="JSTL &amp; EL, <tag> " />
<!-- 만약 2), 3)의 경우에서 태그가 제대로 나오려면 아래의 구문으로 교체합니다. -->
<!--<c:set var="temp" value="JSTL &amp; EL, &lt;tag&gt; " /> -->
1) escapeXml="true" : <c:out value="${temp}" escapeXml="true" /><br>
2) escapeXml="false" : <c:out value="${temp}" escapeXml="false" /><br>
3) EL : ${temp} 
</body>
</html>