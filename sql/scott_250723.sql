-- < 다중햄 함수>
/*
1) SUM : 합계
2) COUNT : 개수
3) MAX : 최댓값
4) MIN : 최솟값
5) AVG : 평균

    함수(DISTINCT 혹은 ALL 중 하나를 선택하거나 아무 값도 지정하지 않음[선택]
        1~5를 구할 열이나 연산자 혹은 함수를 사용한 데이터[필수])
        OVER(분석할 때 사용할 여러 문법 지정[선택])
*/

-- 1) SUM
SELECT SUM(SAL),
       SUM(ALL SAL),
       SUM(DISTINCT SAL)
    FROM EMP;
    