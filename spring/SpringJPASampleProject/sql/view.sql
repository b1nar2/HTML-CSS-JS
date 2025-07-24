-- 사원 테이블(emp)과 부서 테이블(dept)의 조인(join)의 결과

SELECT e.empno, 
	   e.ename,
	   e.job,
	   e.mgr,
	   e.hiredate,	   
	   e.sal,
	   e.comm,
	   d.deptno,
	   d.dname,
	   d.loc
  FROM dept d, emp e
 WHERE d.deptno = e.deptno;
   
   
-- 위의 SQL구문에 대한 View 생성

-- oracle join 
CREATE OR REPLACE VIEW view_emp_dept_job 
AS
	SELECT e.empno, 
		   e.ename,
		   e.job,
		   e.mgr,
		   e.hiredate,	   
		   e.sal,
		   e.comm,
		   d.deptno,
		   d.dname,
		   d.loc
	  FROM dept d, emp e
	 WHERE d.deptno = e.deptno;

-- ANSI join 
CREATE OR REPLACE VIEW view_emp_dept_job 
AS
	SELECT e.empno, 
		   e.ename,
		   e.job,
		   e.mgr,
		   e.hiredate,	   
		   e.sal,
		   e.comm,
		   d.deptno,
		   d.dname,
		   d.loc
	  FROM dept d INNER JOIN
	       emp e
	    ON d.deptno = e.deptno;
	    
----------------------------------------------	    

-- USING 사용시 PK 필드(deptno)에 주의 
CREATE OR REPLACE VIEW view_emp_dept_job 
AS
	SELECT e.empno, 
		   e.ename,
		   e.job,
		   e.mgr,
		   e.hiredate,	   
		   e.sal,
		   e.comm,
		   deptno, -- alias 부착 금지 !
		   d.dname,
		   d.loc
	  FROM dept d JOIN emp e
     USING (deptno);	    
	 
	 
-- 조회 구문	 
SELECT * FROM view_emp_dept_job WHERE empno = 7839;	 
	 
SELECT * FROM view_emp_dept_job WHERE job = 'SALESMAN';