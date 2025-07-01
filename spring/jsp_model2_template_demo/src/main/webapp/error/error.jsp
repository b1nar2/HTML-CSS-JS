<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>에러 처리 페이지</title>
</head>
<body>
<%
   int errorCode = response.getStatus();
   String msg = "";
   
   switch (errorCode) {
   case 403 : msg = "JSP를 직접 돌릴 수 없습니다."; break;
   case 404 : msg = "페이지가 존재하지 않습니다."; break;
   case 500 : msg = "서버에 오류가 발생하였습니다."; break;
   default : msg = "에러가 발생하였습니다."; break;
   }   
   %> 
에러 메시지 : <%= msg %>   
</body>
</html>