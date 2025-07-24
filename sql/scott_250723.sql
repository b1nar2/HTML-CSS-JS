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
       SUM(DISTINCT SAL) AS "중복된 값은 한 번만 사용"
    FROM EMP;


-- 2) COUNT
SELECT COUNT(SAL),
       COUNT(ALL SAL),
       COUNT(DISTINCT SAL) AS "중복된 값은 한 번만 사용"
    FROM EMP;
    
-- 3) MAX/MIN

-- 부서 번호가 10인 사원의 최대 급여 출력
SELECT MAX(SAL)
  FROM EMP
 WHERE DEPTNO = 10;
 
-- 부서 번호가 20인 사원의 입사일 중 가장 최근 입사일 출력
SELECT MAX(HIREDATE)
  FROM EMP
 WHERE DEPTNO = 20;
 
-- 부서 번호가 10인 사원의 최소 급여 출력
SELECT MIN(SAL)
  FROM EMP
 WHERE DEPTNO = 10;
 
-- 부서 번호가 20인 사원의 입사일 중 가장 오래된 입사일 출력
SELECT MIN(HIREDATE)
  FROM EMP
 WHERE DEPTNO = 20;
 
--4) AVG
-- 부서 번호가 30인 사원의 평균 급여 출력
SELECT AVG(SAL)
  FROM EMP
 WHERE DEPTNO = 30;
 
-- 중복 제거한 급여 열의 평균 급여 출력
SELECT AVG(DISTINCT SAL)
  FROM EMP
 WHERE DEPTNO = 30;
 

-- < GROUP BY절 : 결괏값을 원하는 열로 묶어 출력 >
/*
SELECT      조회할 열1 이름, 열1이름, ... , 열n 이름
FROM        조회할 테이블 이름
WHERE       조회할 행을 선별하는 조건식
GROUP BY    그룹화할 열을 지정(여러개 지정 가능)
ORDER BY    정렬할 열 지정

GROUP BY절을 사용할 때 그룹화한 열 외의 일반 열은 SELECT절에 지정할 수 없음
*/


-- 평균 값을 '하나의 결괏값만' 출력 (부서번호 10/20/30값 따로 하나씩 출력됨)
SELECT AVG(SAL) FROM EMP WHERE DEPTNO = 10;
SELECT AVG(SAL) FROM EMP WHERE DEPTNO = 20;
SELECT AVG(SAL) FROM EMP WHERE DEPTNO = 30;

-- 부서번호 10/20/30의 평균 값이 한번에 출력됨 [하드코딩]
/* '10' AS DEPTNO => 구분해서 보기 위해 별칭 사용 */
SELECT AVG(SAL), '10' AS DEPTNO FROM EMP WHERE DEPTNO = 10
UNION ALL
SELECT AVG(SAL), '20' AS DEPTNO FROM EMP WHERE DEPTNO = 20
UNION ALL
SELECT AVG(SAL), '30' AS DEPTNO FROM EMP WHERE DEPTNO = 30;

-- GROUP BY절 사용
-- 부서별 평균 급여 출력
SELECT AVG(SAL), DEPTNO
FROM EMP
GROUP BY DEPTNO
ORDER BY DEPTNO ASC;    /*부서번호 오름차순으로 정렬, ASC 생략 가능(ORDER BY 기본값이 ASC) */

-- 부서 번호와 직책별 평균 급여로 정렬
SELECT DEPTNO, JOB, AVG(SAL)
FROM EMP
GROUP BY DEPTNO, JOB
ORDER BY DEPTNO, JOB;


-- < HAVING 절 : 만든 그룹을 조건별로 출력할 때 사용 >
-- HAVING 절은 SELECT문에 GROUP BY 절이 있을 때만 사용 가능.
-- GROUP BY절로 그룹화한 결괏값의 범위를 제한하는 데 사용.
-- WHERE 절은 GROUP BY, HAVING 절을 사용한 데이터 그룹화보다 먼저 출력대상 행을 제한함.
/*
SELECT      조회할 열1이름, 열2 이름, ... , 열n 이름
FROM        조회할 테이블 이름
WHRER       조회할 행을 선별하는 조건식
GROUP BY    그룹화할 열 지정(여러개 지정 가능)
HAVING      출력 그룹을 제한하는 조건식
ORDER BY    정렬할 열 지정

*/
SELECT DEPTNO, JOB, AVG(SAL)
  FROM EMP
GROUP BY DEPTNO, JOB
  HAVING AVG(SAL) >= 2000
ORDER BY DEPTNO, JOB;


-------------------------------------------------------------------------------

-- 교재 200p 되새김 문제 풀이

/* Q1)
EMP 테이블을 이용하여 부서번호(DEPTNO), 평균 급여(AVG_SAL), 최고 급여(MAX_SAL), 최저급여(MIN_SAL),
사원 수(CNT)를 출력합니다. 단 평균 급여를 출력할 때 소수는 제외하고 부서 번호별로 출력하세요.*/

--결과는 같으나 코드가 다름
SELECT DEPTNO,
       TRUNC(AVG(SAL),0) AS AVG_SAL,
       MAX(SAL) AS MAX_SAL,
       MIN(SAL) AS MIN_SAL,
       COUNT(SAL) AS CNT
    FROM EMP
GROUP BY DEPTNO;

-- 정답
SELECT DEPTNO,
       TRUNC(AVG(SAL)) AS AVG_SAL,
       MAX(SAL) AS MAX_SAL,
       MIN(SAL) AS MIN_SAL,
       COUNT(*) AS CNT
  FROM EMP
GROUP BY DEPTNO; 

/* Q2)
같은 직책(JOB)에 종사하는 사원이 3명 이상인 직책과 인원 수를 출력하세요.*/

--인원 수 출력X
SELECT JOB
  FROM EMP
  GROUP BY JOB  
  HAVING COUNT(JOB) >=3;

-- 정답
SELECT JOB,
       COUNT(*)
  FROM EMP
GROUP BY JOB
HAVING COUNT(*) >= 3; 

/* Q3)
사원의 입사 연도(HIRE_YEAR)를 기준으로 부서별로 몇 명씩 입사했는지 출력하세요.*/

-- X
SELECT SUBSTR(TO_CHAR(HIREDATE, 'YYYY/MM/DD'), 1,4),
       DEPTNO,
       COUNT(*) AS CNT
FROM EMP
GROUP BY HIREDATE DEPTNO;

-- 정답
SELECT TO_CHAR(HIREDATE, 'YYYY') AS HIRE_YEAR,
       DEPTNO,
       COUNT(*) AS CNT
  FROM EMP
GROUP BY TO_CHAR(HIREDATE, 'YYYY'), DEPTNO; 

/* Q4)
추가수당(COMM)을 받는 사원 수와 받지 않는 사원 수를 출력하세요*/

-- X COMM 값마다 따로 출력 됨
SELECT NVL2(COMM, 'O','X') AS EXIST_COMM,
       COUNT(*) AS CNT
  FROM EMP
GROUP BY COMM;

-- 정답
SELECT NVL2(COMM, 'O', 'X') AS EXIST_COMM,
       COUNT(*) AS CNT
  FROM EMP
GROUP BY NVL2(COMM, 'O', 'X'); 