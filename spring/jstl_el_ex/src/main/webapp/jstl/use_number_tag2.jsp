<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>numberFormat 태그 사용</title>
</head>
<body>

<!-- 언어 분류 표준화 명명 => ISO-639 
  : https://ko.wikipedia.org/wiki/ISO_639-1_%EC%BD%94%EB%93%9C_%EB%AA%A9%EB%A1%9D -->

<!-- 세계의 나라와 부속 영토, 국가의 주요 구성 단위의 명칭에 고유 부호 => ISO-3166-1 
  : https://ko.wikipedia.org/wiki/ISO_3166-1 -->

<!-- locale value : ISO-639 + "_" + ISO-3166-1 -->

한국  : <fmt:setLocale value="ko_kr"/>
금액(원(圓)) : <fmt:formatNumber value="100000" type="currency" /><br>

미국 : <fmt:setLocale value="en_us"/>
금액 (달러): <fmt:formatNumber value="100000" type="currency" /><br>

프랑스 : <fmt:setLocale value="fr_fr"/>
금액 (유로) : <fmt:formatNumber value="100000" type="currency" /><br>

영국 : <fmt:setLocale value="en_gb"/>
금액(파운드) : <fmt:formatNumber value="100000" type="currency" /><br>

중국 : <fmt:setLocale value="zh_cn"/>
금액(위안,元) : <fmt:formatNumber value="100000" type="currency" /><br>

일본 : <fmt:setLocale value="ja_jp"/>
금액 (엔,円(圓)): <fmt:formatNumber value="100000" type="currency" /><br>

</body>
</html>