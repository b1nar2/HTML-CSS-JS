<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML>
<HTML lang="ko">
<HEAD>
<TITLE> out 객체 </TITLE>
</HEAD>
<BODY>

<P> 저희 사이트에 방문해 주셔서 감사합니다. </P>
<% out.clear();%>
<P> 고객님께서는 저희 사이트의 정회원입니다.</P>
<P> 고객님께 마일리지 점수 100점을 지급합니다.</P>
<% out.close(); %>
<!-- out 객체를 폐쇄한 이후에는 출력되지 않음 -->
<P> 저희 사이트를 앞으로도 계속 이용해주시면 감사하겠습니다.</P>

</BODY>
</HTML>