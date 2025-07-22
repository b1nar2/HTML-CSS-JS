-- < 날짜 함수 >
/*
1) SYSDATE: 입력 데이터 없이 현재 날짜와 시간정보를 출력하는 함수 
2) ADD_MONTHS(날짜 데이터[필수], 더할 개월 수[정수,필수]) -- 특정 날짜에 지정한 개월 수 이후의 날짜 데이터를 반환하는 함수
3) MONTHS_BETWEEN(날짜 데이터[필수1], 날짜 데이터2[필수] -- 두 날짜 간의 개월 수 차이를 구하는 함수
4) NEXT_DAY(날짜 데이터[필수], 요일 문자[필수]) -- 특정 날짜를 기준으로 돌아오는 요일의 날짜를 출력하는 함수
5) LAST_DAY(날짜 데이터[필수]) -- 특정 날짜가 속한 달의 마지막 날짜를 출력하는 함수
6) ROUND, TRUNC : 날짜를 반올림, 버림 하는 함수
<숫자 데이터>                            <날짜 데이터>
ROUND(숫자[필수], 반올림 위치)            ROUND(날짜 데이터[필수], 반올림 기준 포맷)
TRUNC{숫자[필수], 버림 위치)              TRUNC(날짜 데이터[필수], 버림 기준 포맷)

*/

-- 1) SYSDATE
SELECT SYSDATE AS NOW,
       SYSDATE-1 AS TESTERDAY,
       SYSDATE+1 AS TOMORROW
    FROM DUAL;

-- 2) ADD_MONTHS
SELECT SYSDATE,
       ADD_MONTHS(SYSDATE, 5)
    FROM DUAL;

/*3년 후 계산*/
SELECT EMPNO, ENAME, HIREDATE,
       ADD_MONTHS(HIREDATE, 36)
    FROM EMP;

-- 3) MONTHS_BETWEEN
SELECT EMPNO, ENAME, HIREDATE, SYSDATE,
       MONTHS_BETWEEN(HIREDATE, SYSDATE) AS MONTH1,
       MONTHS_BETWEEN(SYSDATE, HIREDATE) AS MONTH2,
       TRUNC(MONTHS_BETWEEN(SYSDATE,HIREDATE)) AS "정수로 출력"
    FROM EMP;

-- 4) NEXT_DAY, LAST_DAY
SELECT SYSDATE,
       NEXT_DAY(SYSDATE,'월요일'),
       LAST_DAY(SYSDATE)
    FROM DUAL;
    
-- 5) ROUND
SELECT SYSDATE,
       ROUND(SYSDATE, 'CC') AS FORMAT_CC,
       ROUND(SYSDATE, 'YYYY') AS FORMAT_YYYY,
       ROUND(SYSDATE, 'Q') AS FORMAT_Q,
       ROUND(SYSDATE, 'DDD') AS FORMAT_DDD,
       ROUND(SYSDATE, 'HH') AS FORMAT_HH
    FROM DUAL;

-- 6) TRUNC
SELECT SYSDATE,
       TRUNC(SYSDATE, 'CC') AS FORMAT_CC,
       TRUNC(SYSDATE, 'YYYY') AS FORMAT_YYYY,
       TRUNC(SYSDATE, 'Q') AS FORMAT_Q,
       TRUNC(SYSDATE, 'DDD') AS FORMAT_DDD,
       TRUNC(SYSDATE, 'HH') AS FORMAT_HH
    FROM DUAL;
    

-------------------------------------------------------------------------
/*문제풀이*/

SELECT TO_CHAR(SYSDATE, 'YYYY/MM/DD HH24:MI:SS')
    FROM DUAL;


    
SELECT EMPNO, ENAME, JOB, SAL,
  CASE JOB
       WHEN 'MANAGER' THEN SAL*1.1
       WHEN 'SALESMAN' THEN SAL*1.05
       WHEN 'ANALYST'  THEN SAL
       ELSE SAL*1.03
    END AS UPSAL
    FROM EMP;


-- 교재 179p 되새김 문제 풀이

--Q1) X
-- 오답
SELECT EMPNO,
       RPAD(EMPNO,2, '*') AS MASKING_EMPNO,
       ENAME,
       RPAD(ENAME, 1, '*') AS MASKING_ENAME   
  FROM EMP
    WHERE LENGTH(ENAME) BETWEEN 5 AND 5;

-- 정답
SELECT EMPNO,
       RPAD(SUBSTR(EMPNO, 1, 2), 4, '*') AS MASKING_EMPNO,
       ENAME,
       RPAD(SUBSTR(ENAME, 1, 1), LENGTH(ENAME), '*') AS MASKING_ENAME
 FROM  EMP
 WHERE LENGTH(ENAME) >= 5
   AND LENGTH(ENAME) < 6; 
   
-- Q2) O
SELECT EMPNO, ENAME, SAL,
       TRUNC(SAL/21.5, 2) AS DAY_PAY,
       ROUND(SAL/21.5/8, 1) AS TIME_PAY
  FROM EMP;

   
-- Q3) X
-- 오답
SELECT EMPNO, ENAME,
       TO_CHAR(HIREDATE, 'YY/MM/DD') AS HIREDATE,
       ADD_MONTHS(HIREDATE, 3) AS R_JOB,
       NVL(COMM,'N/A')
  FROM EMP;
  
-- 정답
SELECT EMPNO, ENAME, HIREDATE,
       TO_CHAR(NEXT_DAY(ADD_MONTHS(HIREDATE, 3), '월요일'), 'YYYY-MM-DD') AS R_JOB,
       NVL(TO_CHAR(COMM), 'N/A') AS COMM
  FROM EMP;

-- Q4) X
-- 오답
SELECT EMPNO, ENAME, MGR,
    CASE MGR
       WHER MGR=NULL THEN '0000'
       WHEN(SUBSTR(MGR,1,2)=75) THEN '5555'
       WHEN(SUBSTR(MGR,1,2)=76) THEN '6666'
       WHEN(SUBSTR(MGR,1,2)=77) THEN '7777'
       WHEN(SUBSTR(MGR,1,2)=78) THEN '8888'
       ELSE MGR
     END AS CHG_MGR
  FROM EMP;
  
-- 정답  
SELECT EMPNO, ENAME, MGR,
       CASE
          WHEN MGR IS NULL THEN '0000'
          WHEN SUBSTR(MGR, 1, 2) = '78' THEN '8888'
          WHEN SUBSTR(MGR, 1, 2) = '77' THEN '7777'
          WHEN SUBSTR(MGR, 1, 2) = '76' THEN '6666'
          WHEN SUBSTR(MGR, 1, 2) = '75' THEN '5555'
          ELSE TO_CHAR(MGR)
       END AS CHG_MGR
  FROM EMP;