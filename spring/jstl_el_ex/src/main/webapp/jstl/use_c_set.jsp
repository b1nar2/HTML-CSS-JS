<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="vo.Member" %>
<%@ page import="java.util.HashMap" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	Member member = new Member();
	HashMap<String, String> pref = new HashMap<String, String>();
%>
<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
</head>
<body>
<c:set var="member" value="<%= member %>" />
<c:set target="${member}" property="name" value="JSP" />

<c:set var="pref" value="<%= pref %>" />
<c:set var="favoriateColor" value="#{pref.color}" />

회원 이름: ${member.name},
회원 이름: ${member.getName()},
좋아하는 색: ${favoriateColor}

<br />
<c:set target="${pref}" property="color" value="red" />

설정 이후 좋아하는 색: ${favoriateColor}

</body>
</html>