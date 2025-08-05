<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML>
<HTML lang="ko">
<HEAD>
<TITLE> response 객체 </TITLE>
</HEAD>
<BODY>

JSP 환경 설정을 위한 다운로드 페이지 입니다.

<FORM action="response_2.jsp" method="get">

<select name="download">
	<option selected value="1">JDK</option>
	<option value="2">Tomcat</option>
	<option value="3">eclipse </option>
	<option value="4">oracle</option>
</select>

<input type="submit" value="이동">

</form>

</BODY>
</HTML>
