-- < JOIN : 2개 이상의 테이블을 연결하여 하나의 테이블처럼 출력할 때 사용하는 방식>
/*
집합 연산자/조인 차이
- 집합 연산자 : 2개 이상의 SELECT문의 결괏값을 세로로 연결한 것
- 조인 : 2개 이상의 테이블 데이터를 가로로 연결한 것
*/

-- JOIN 조건이 없으면 데이터가 모든 경우의 수만큼 출력됨 (데카르트 곱 현상)
SELECT *
    FROM EMP, DEPT
    ORDER BY EMPNO;


-- 열 이름을 비교하며 JOIN (테이블 이름. 열 이름)
SELECT *
    FROM EMP, DEPT
   WHERE EMP.DEPTNO = DEPT.DEPTNO
ORDER BY EMPNO;

-- 테이블의 별칭 설정 (FROM 테이블 이름1 별칭1, 테이블 이름2, 별칭2)

SELECT *
    FROM EMP E, DEPT D
   WHERE E.DEPTNO = D.DEPTNO
ORDER BY EMPNO;

SELECT D.DNAME, E.EMPNO
FROM EMP E, DEPT D
WHERE E.DEPTNO = D.DEPTNO
ORDER BY EMPNO;

-- < 등가 조인 : equi join >
-- 테이블을 연결한 후 각 테이블의 특정 열과 일치하는 데이터를 기준으로 출력 행을 선정하는 방식

/* EMP, DEPT 테이블 내 DEPTNO 중복: 오류 발생 */
SELECT EMPNO, ENAME, DEPTNO, DNAME, LOC
    FROM EMP E, DEPT D
    WHERE E.DEPTNO = D.DEPTNO;

/* 테이블에 알리아스 지정, 열 이름에 테이블명을 같이 명시*/
SELECT E.EMPNO, E.ENAME, D.DEPTNO, D.DNAME, D.LOC
  FROM EMP E, DEPT D
 WHERE E.DEPTNO = D.DEPTNO
   AND SAL >= 3000;  -- 조건식 추가, 출력할 행 제한

-- < 비등가 조인(non-equi join) >
SELECT *
  FROM EMP E, SALGRADE S
 WHERE E.SAL BETWEEN S.LOSAL AND S.HISAL;   ---> 
 
 -- < 자체 조인 : FROM절에 같은 테이블을 여러번 지정, 테이블의 별칭만 다르게 지정하는 방식, 큰 범위에서는 등가 조인 >
SELECT E1.EMPNO, E1.ENAME, E1.MGR,
       E2.EMPNO AS MGR_EMPNO,
       E2.ENAME AS MGR_ENAME
  FROM EMP E1, EMP E2           -- 같은 테이블 두 번 지정
 WHERE E1.MGR = E2.EMPNO;
 
-- < 외부 조인(outer join) : 조인 기준 열의 한쪽이 NULL 이어도 강제로 출력하는 방식 >

-- 왼쪽 외부 조인 