<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML>
<HTML lang="ko">
<HEAD>
<TITLE> request href(여러가지 get 방식 처리)</TITLE>
<script>

function jumpURL() {
	
	location.href="request_3.jsp?id=apple&pw=pineapple";	
}
</script>
</HEAD>
<BODY>

<!-- get 방식 전송 -->
<A HREF="request_3.jsp?id=apple&pw=pineapple">test1</A><br>
<A HREF="#" onClick="location.href='request_3.jsp?id=apple&pw=pineapple'">test2</A><br>
<A HREF="#" onClick="javascript:jumpURL()">test3</A><br>
<A HREF="#" onClick="jumpURL()">test4</A><br>

</BODY>
</HTML>