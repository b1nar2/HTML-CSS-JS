-- 1. EMP 테이블에서 사원번호가 7521의 업무와 같고 급여가 7934보다 많은 
-- 사원의 사원번호,이름,담당업무,입사일자,급여를 출력하십시오.

     EMPNO ENAME      JOB       HIREDATE          SAL
---------- ---------- --------- ---------- ----------
      7499 ALLEN      SALESMAN  1981-02-20       1600
      7844 TURNER     SALESMAN  1981-09-08       1500

SELECT empno,ename,job,hiredate,sal
FROM emp
WHERE job = (SELECT job
  			 FROM emp
  			 WHERE empno = 7521)
  AND
  	     sal > (SELECT sal
  			    FROM emp
  			    WHERE empno = 7934);
                

-- 2. EMP 테이블에서 급여의 평균보다 적은 사원의 사원번호,이름,담당업무,급여,부서번호를 출력하십시오.

EMPNO ENAME      JOB              SAL     DEPTNO
---------- ---------- --------- ---------- ----------
      7654 MARTIN     SALESMAN        1250         30
      7499 ALLEN      SALESMAN        1600         30
      7844 TURNER     SALESMAN        1500         30
      7900 JAMES      CLERK            950         30
      7521 WARD       SALESMAN        1250         30
      7369 SMITH      CLERK            800         20
      7876 ADAMS      CLERK           1100         20
      7934 MILLER     CLERK           1300         10

8개 행이 선택되었습니다. 

SELECT empno,ename,job,sal,deptno
FROM emp
WHERE sal < (SELECT AVG(sal) FROM emp);


-- 3. EMP 테이블에서 20번 부서의 최소 급여보다 많은 모든 부서를 출력하십시오.

    DEPTNO   MIN(SAL)
---------- ----------
        30        950
        10       1300
        
SELECT deptno,MIN(sal)
FROM emp
GROUP BY deptno
HAVING MIN(sal) > (SELECT MIN(sal)
         		   FROM emp
                   WHERE deptno = 20);
                   

-- 4. EMP 테이블에서 업무별로 가장 적은 급여를 출력하십시오.

JOB         AVG(SAL)
--------- ----------
CLERK         1037.5

SELECT job,avg(sal)
FROM emp
GROUP BY job
HAVING AVG(sal) = (SELECT MIN(AVG(sal))
         		   FROM emp
                   GROUP BY job);
                   
                   
-- 5. EMP 테이블에서 업무별로 최소 급여를 받는 사원의 사원번호,이름,업무,입사일자,급여,부서번호를 출력하십시오.

EMPNO ENAME      JOB       HIREDATE          SAL     DEPTNO
---------- ---------- --------- ---------- ---------- ----------
      7839 KING       PRESIDENT 1981-11-17       5000         10
      7782 CLARK      MANAGER   1981-01-09       2450         10
      7654 MARTIN     SALESMAN  1981-09-28       1250         30
      7521 WARD       SALESMAN  1981-02-22       1250         30
      7902 FORD       ANALYST   1981-12-03       3000         20
      7369 SMITH      CLERK     1980-12-17        800         20
      7788 SCOTT      ANALYST   1982-12-09       3000         20

7개 행이 선택되었습니다. 

SELECT empno,ename,job,hiredate,sal,deptno
FROM emp
WHERE sal IN (SELECT MIN(sal)
              FROM emp
              GROUP BY job);


-- 6. EMP 테이블에서 30번 부서의 최소 급여를 받는 사원 보다 많은 급여를 받는 사원의 
-- 사원번호,이름,업무,입사일자,급여,부서번호를 출력하십시오. 단 출력시 30번은 제외합니다.

    EMPNO ENAME      JOB       HIREDATE          SAL     DEPTNO
---------- ---------- --------- ---------- ---------- ----------
      7839 KING       PRESIDENT 1981-11-17       5000         10
      7782 CLARK      MANAGER   1981-01-09       2450         10
      7566 JONES      MANAGER   1981-04-02       2975         20
      7902 FORD       ANALYST   1981-12-03       3000         20
      7788 SCOTT      ANALYST   1982-12-09       3000         20
      7876 ADAMS      CLERK     1983-01-12       1100         20
      7934 MILLER     CLERK     1982-01-23       1300         10

7개 행이 선택되었습니다. 

SELECT empno,ename,job,hiredate,sal,deptno
FROM emp
WHERE deptno != 30 AND sal > ANY (SELECT MIN(sal)
                                  FROM emp
                                  WHERE deptno = 30);


-- 7. EMP 테이블에서 30번 부서의 최고 급여를 받는 사원 보다 많은 급여를 받는 사원의 
-- 사원번호,이름,업무,입사일자,급여,부서번호를 출력하되 30번은 제외하여 출력합니다.

     EMPNO ENAME      JOB       HIREDATE          SAL     DEPTNO
---------- ---------- --------- ---------- ---------- ----------
      7566 JONES      MANAGER   1981-04-02       2975         20
      7788 SCOTT      ANALYST   1982-12-09       3000         20
      7902 FORD       ANALYST   1981-12-03       3000         20
      7839 KING       PRESIDENT 1981-11-17       5000         10

SELECT empno,ename,job,hiredate,sal,deptno
FROM emp
WHERE deptno != 30 AND sal > ALL (SELECT sal
                                  FROM emp
                                  WHERE deptno = 30);


-- 8. EMP 테이블에서 적어도 한명의 사원으로부터 보고를 받을 수 있는 사원의 사원번호,이름,업무,입사일자,급여를 출력하십시오. 
-- 단, 사원번호 순으로 오름차순 정렬하여 출력합니다.

EMPNO ENAME      JOB       HIREDATE          SAL     DEPTNO
---------- ---------- --------- ---------- ---------- ----------
      7839 KING       PRESIDENT 1981-11-17       5000         10
      7698 BLAKE      MANAGER   1981-05-01       2850         30
      7566 JONES      MANAGER   1981-04-02       2975         20
      7902 FORD       ANALYST   1981-12-03       3000         20
      7788 SCOTT      ANALYST   1982-12-09       3000         20
      7782 CLARK      MANAGER   1981-01-09       2450         10

6개 행이 선택되었습니다. 

SELECT empno,ename,job,hiredate,sal,deptno
FROM emp e
WHERE EXISTS (SELECT * 
              FROM emp
              WHERE e.empno = mgr);


-- 9. EMP 테이블에서 급여와 보너스가, 부서 30에 있는 어떤 사원의 보너스, 급여와 일치하는 
-- 사원의 이름, 부서번호, 급여, 보너스를 출력하십시오.

ENAME          DEPTNO        SAL       COMM
---------- ---------- ---------- ----------
BLAKE              30       2850           
MARTIN             30       1250       1400
WARD               30       1250        500
ALLEN              30       1600        300
MILLER             10       1500        300
TURNER             30       1500          0
JAMES              30        950      

7개 행이 선택되었습니다. 

-- 전제 조건) 아래의 업데이트 수행후 실행합니다
UPDATE emp
SET sal = 1500, comm = 300
WHERE empno = 7934;

SELECT ename,deptno,sal,comm
FROM emp
WHERE sal IN (SELECT sal
			  FROM emp
 			  WHERE deptno = 30)
AND NVL(comm,-1) IN (SELECT NVL(comm,-1)
                     FROM emp
    			     WHERE deptno = 30);
   
                     
-- 10. EMP 테이블에서 부서 30에 있는 어떤 사원의 보너스와 급여와 일치하는 
-- 사원의 이름,부서번호,급여,보너스를 출력하십시오.

ENAME          DEPTNO        SAL       COMM
---------- ---------- ---------- ----------
BLAKE              30       2850           
MARTIN             30       1250       1400
ALLEN              30       1600        300
TURNER             30       1500          0
JAMES              30        950           
WARD               30       1250        500

6개 행이 선택되었습니다. 

SELECT ename,deptno,sal,comm
FROM emp
WHERE (sal,NVL(comm,-1)) IN (SELECT sal,NVL(comm,-1)
                             FROM emp
                             WHERE deptno = 30);

-- 11. 업무별로 최소 급여를 받는 사원의 사원번호,이름,업무,부서번호를 
-- 업무별로 오름차순으로 정렬하여 출력하십시오.

EMPNO ENAME      JOB              SAL     DEPTNO
---------- ---------- --------- ---------- ----------
      7902 FORD       ANALYST         3000         20
      7788 SCOTT      ANALYST         3000         20
      7369 SMITH      CLERK            800         20
      7782 CLARK      MANAGER         2450         10
      7839 KING       PRESIDENT       5000         10
      7654 MARTIN     SALESMAN        1250         30
      7521 WARD       SALESMAN        1250         30

7개 행이 선택되었습니다. 

SELECT empno,ename,job,sal,deptno
FROM emp
WHERE (job,sal) IN (SELECT job,MIN(sal)
                     FROM emp
                     GROUP BY job)
ORDER BY job;


-- 12. EMP과 DEPT 테이블에서 업무가 MANAGER인 사원의 이름,업무,부서명,근무지를 출력하십시오.

ENAME      JOB       DNAME          LOC          
---------- --------- -------------- -------------
BLAKE      MANAGER   SALES          CHICAGO      
CLARK      MANAGER   ACCOUNTING     NEW YORK     
JONES      MANAGER   RESEARCH       DALLAS    

SELECT e.ename,e.job,d.dname,d.loc
FROM dept d,emp e
WHERE job = 'MANAGER' AND e.deptno = d.deptno;

-- 참고로 아래는 인라인 뷰(inline-view) 방식의 중첩 쿼리 적용

SELECT e.ename,e.job,d.dname,d.loc
FROM (SELECT ename,job,deptno
      FROM emp
      WHERE job = 'MANAGER') e, dept d
WHERE e.deptno = d.deptno;
