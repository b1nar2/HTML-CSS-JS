<%@ page language="java" contentType="text/html; charset=UTF-8" buffer="10kb" %>
<!DOCTYPE HTML>
<HTML lang="ko">
<HEAD>
<TITLE> response 객체 </TITLE>
</HEAD>
<BODY>
<!-- 
	웹브라우저에서 F12 클릭하여 개발창을 확인하시면 출력되어 있는 것을 확인할 수 있습니다. 
	- Chrome/Edge : Failed to load resource: the server responded with a status of 403 ()
	        혹은 GET http://localhost:8181/basic_object_ex/response_3.jsp 403
	- firefox :쿠키 “JSESSIONID”는 “sameSite” 속성이 “secure” 속성이 없이 “none”이나 유효하지 않은 값으로 ...(이하 생략)
	- IE11 : 별다른 반응 없음(?)
 -->
<%
 response.setStatus(response.SC_FORBIDDEN);
%>

</BODY>
</HTML>
