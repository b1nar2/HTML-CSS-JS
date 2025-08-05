<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML>
<HTML lang="ko">
<HEAD>
<TITLE> out 객체 </TITLE>
</HEAD>
<BODY>

<%
int sum1=0;
int sum2=0;

for(int i=1; i<=10; i++){
	sum1 += i;
	out.print(sum1);
}
%>
<BR>
<%
for(int j=1; j<=10; j++){
	sum2 += j;
	out.println(sum2+"<br>"); // 개행 기능 안됨 ! -> \n(X) -> <br> 또는 <p> (O)
	// out.println(sum2); // 개행 기능 안됨 ! -> \n(X) -> <br> 또는 <p> (O)
}
%>

</BODY>
</HTML>