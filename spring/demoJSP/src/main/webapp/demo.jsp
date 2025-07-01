<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%!
	// 선언부(declaration)
	public void demo() {
		System.out.println("demo !");
		// system.out.println("demo !");
		// page fault(에러) => 소스 노출 => 보안적으로 위험!
	}

	public double rand(int num) {
		return Math.random() * num;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
rand : <%=rand(30) %> <br> <!-- expression(표현식) -->
1~5까지의 합 : ${1+2+3+4+5}<br> <!-- EL(표현언어) -->
<%
	request.setAttribute("java", 21); // 인자 생성
	// request : JSP의 Http 요청 내장(기본) 객체
%>
java :  <%=request.getAttribute("java") %><br>
java : ${java}<br> <!-- EL(표현언어) -->
java : ${requestScope.java}<br> <!-- EL(표현언어) -->
<!-- requestScope : EL의 Http 요청 내장(기본) 객체 -->

java : <%
		out.println(request.getAttribute("java") + "<br>"); // 기본개행 없음. <br>로 개행기능 추가
		out.println(request.getAttribute("java"));
	// out : JSP 웹브라우저 출력에 관한 기본 객체
		%><br>
<%
	// 스크립트릿(scriptlet)
	demo();
%>
</body>
</html>