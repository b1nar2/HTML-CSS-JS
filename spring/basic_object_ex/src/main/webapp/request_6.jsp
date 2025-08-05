<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML>
<HTML lang="ko">
<HEAD>
<TITLE> request 객체 </TITLE>
</HEAD>
<BODY>

<!-- 
	action을  request_7.jsp/request_8.jsp 두가지로 시도하여 차이를 확인합니다.
	결론적으로 POST 방식은 인자 전송후 반드시 encoding 처리 함수를 실행해야 합니다.  
-->
<FORM action="request_7.jsp" Method="post">
<!-- FORM action="request_8.jsp" Method="post"-->

이름   : <input type="text" name="strName"> <BR>
주소 : <input type="text" name="strAddr"> <BR><BR>

<input type="submit" Value="전송">
<input type="reset" Value="취소">
</Form>

</BODY>
</HTML>
