-- 1) 아래는 특정 사원번호에 대해 급여를 갱신하는 저장 프로시저입니다.
-- 실행을 위해 아래의 레코드를 임시로 삽입합니다.

SET SERVEROUTPUT ON
-- 콘솔(DB console) 출력 (DBMS_OUTPUT.PUT_LINE) 활성화

DROP TABLE emp_temp;
CREATE TABLE emp_temp AS SELECT * FROM emp;

INSERT INTO emp_temp
VALUES (9000, '홍길동', 'CLERK', 7839, sysdate, 10000, 0, 10);

CREATE OR REPLACE PROCEDURE emp_sal_update (
    p_empno IN emp.empno%TYPE, p_sal IN emp.sal%TYPE)
IS
BEGIN
    UPDATE emp_temp
    SET sal = p_sal
    WHERE empno = p_empno;
    
    IF SQL%NOTFOUND THEN
        DBMS_OUTPUT.PUT_LINE(TO_CHAR(p_empno) ||
        '는 없는 사원번호입니다.');
    ELSE
        DBMS_OUTPUT.PUT_LINE(TO_CHAR(SQL%ROWCOUNT) ||
        ' 명의 자료를 수정하였습니다.');
    END IF;
END emp_sal_update;
/

EXECUTE emp_sal_update(9000,20000)
-- 혹은
EXEC emp_sal_update(9000,30000)


---------------------------------------------------------------------

-- 2) EMP 테이블에 신입 사원의 정보를 이름,업무,관리자,급여를 입력받아
-- 등록(삽입)하는 저장 프로시저를 작성하십시오.
-- 단, 부서 번호는 관리자의 부서 번호와 동일하게 하고 보너스(상여금)는
-- SALESMAN은 0을 그 외 인원들은 NULL을 입력하십시오.

DROP TABLE emp_temp;
CREATE TABLE emp_temp AS SELECT * FROM emp;

CREATE OR REPLACE PROCEDURE emp_input (
--             인자
    v_empno IN emp_temp.ename%TYPE,
    v_name IN emp_temp.ename%TYPE,
    v_job IN emp_temp.job%TYPE,
    v_mgr IN emp_temp.mgr%TYPE,
    v_sal IN emp_temp.sal%TYPE)
IS
--           지역변수
    v_comm emp_temp.comm%TYPE;
    v_deptno emp_temp.deptno%TYPE;
    manager_error EXCEPTION;
BEGIN
    IF UPPER(v_job) NOT IN ('PRESIDENT','MANAGER','ANALYST',
        'SALESMAN','CLERK') THEN
        RAISE manager_error;
    ELSIF UPPER(v_job) = 'SALESMAN' THEN
        v_comm := 0;
    ELSE
        v_comm := NULL;
    END IF;
    
    SELECT deptno
    INTO v_deptno
    FROM emp_temp
    WHERE empno = v_mgr;
    
    INSERT INTO emp_temp
    VALUES (v_empno,v_name,UPPER(v_job),
    v_mgr,SYSDATE,v_sal,v_comm,v_deptno);
    
    EXCEPTION
        WHEN manager_error THEN
        DBMS_OUTPUT.PUT_LINE('담당 업무가 잘못 입력되었습니다.');
        WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('입력한 MANAGER는 없습니다.');
        WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('기타 에러입니다.');
END;
/

EXEC emp_input(9001, '강감찬','MANAGER',7788,30000)

---------------------------------------------------------------------

-- 3) 사원명을 입력받아 그 사원의 정보 중 부서명과 급여를 검색하는
-- 프로시저를 작성하십시오.

SET SERVEROUTPUT ON
-- 콘솔(DB console) 출력 (DBMS_OUTPUT.PUT_LINE) 활성화

DROP TABLE emp_temp;
CREATE TABLE emp_temp AS SELECT * FROM emp;

CREATE TABLE dept_temp AS SELECT * FROM dept;

CREATE OR REPLACE PROCEDURE dname_sal_disp_proc (
    v_ename IN emp_temp.ename%TYPE,
    v_dname OUT dept_temp.dname%TYPE,
    v_sal OUT emp_temp.sal%TYPE)
IS
    v_deptno emp_temp.deptno%TYPE;
BEGIN
    SELECT sal, deptno
    INTO v_sal, v_deptno
    FROM emp_temp
    WHERE ename = UPPER(v_ename);
    
    SELECT dname
    INTO v_dname
    FROM dept_temp
    WHERE deptno = v_deptno;
    
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('입력한 관리자는 없습니다.');
        WHEN TOO_MANY_ROWS THEN
        DBMS_OUTPUT.PUT_LINE('자료가 2건 이상입니다.');
        WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('기타 에러입니다.');
END;
/

VAR g_dname VARCHAR2(20)
-- 혹은
-- VARIABLE g_dname VARCHAR2(20)

VAR g_sal NUMBER
EXECUTE dname_sal_disp_proc('SCOTT',:g_dname,:g_sal);
PRINT g_dname
PRINT g_sal

DROP TABLE dept_temp;

---------------------------------------------------------------------

-- 4) EMP 테이블에서 사원명으로 부서 번호를 검색하는 함수를 작성하십시오.

SET SERVEROUTPUT ON
-- 콘솔(DB console) 출력 (DBMS_OUTPUT.PUT_LINE) 활성화

CREATE TABLE emp_temp AS SELECT * FROM emp;

CREATE OR REPLACE FUNCTION ename_deptno_func (
    v_ename IN emp_temp.ename%TYPE)
RETURN NUMBER
IS
    v_deptno emp_temp.deptno%TYPE;
BEGIN
    SELECT deptno
    INTO v_deptno
    FROM emp_temp
    WHERE ename = UPPER(v_ename);
    
    DBMS_OUTPUT.PUT_LINE('부서번호 : ' || TO_CHAR(v_deptno));
    RETURN v_deptno;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('입력한 관리자는 없습니다.');
        WHEN TOO_MANY_ROWS THEN
        DBMS_OUTPUT.PUT_LINE('자료가 2건 이상입니다.');
        WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('기타 에러입니다.');
END;
/

VAR g_deptno NUMBER
EXECUTE :g_deptno := ename_deptno_func('SCOTT')
PRINT g_deptno

--------------------------------------------------------------------------

-- 5) EMP 테이블에서 사원명을 입력 받아 부서번호, 부서명, 급여를 검색하고
-- 부서번호를 리턴하는 함수를 작성하십시오.

SET SERVEROUTPUT ON
-- 콘솔(DB console) 출력 (DBMS_OUTPUT.PUT_LINE) 활성화

DROP TABLE emp_temp;
CREATE TABLE emp_temp AS SELECT * FROM emp;

DROP TABLE dept_temp;
CREATE TABLE dept_temp AS SELECT * FROM dept;

CREATE OR REPLACE FUNCTION emp_disp_func (
    v_ename IN emp_temp.ename%TYPE,
    v_dname OUT dept_temp.dname%TYPE,
    v_sal OUT emp_temp.sal%TYPE)
RETURN NUMBER
IS
    v_deptno emp_temp.deptno%TYPE;
    v_dname_temp dept_temp.dname%TYPE;
    v_sal_temp emp_temp.sal%TYPE;
BEGIN
    SELECT sal,deptno
    INTO v_sal_temp,v_deptno
    FROM emp_temp
    WHERE ename = UPPER(v_ename);
    
    SELECT dname
    INTO v_dname_temp
    FROM dept_temp
    WHERE deptno = v_deptno;
    
    v_dname := v_dname_temp;
    v_sal := v_sal_temp;
    
    DBMS_OUTPUT.PUT_LINE('성 명 : ' || v_ename);
    DBMS_OUTPUT.PUT_LINE('부서번호 : ' || TO_CHAR(v_deptno));
    DBMS_OUTPUT.PUT_LINE('부 서 명 : ' || v_dname_temp);
    DBMS_OUTPUT.PUT_LINE('급 여 : ' || TO_CHAR(v_sal_temp,'$999,999'));
    
    RETURN v_deptno;
    
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('입력한 관리자는 없습니다.');
        WHEN TOO_MANY_ROWS THEN
        DBMS_OUTPUT.PUT_LINE('자료가 2건 이상입니다.');
        WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('기타 에러입니다.');
END;
/

VAR g_deptno NUMBER
VAR g_dname VARCHAR2(20)
VAR g_sal NUMBER
EXECUTE :g_deptno := emp_disp_func('scott',:g_dname,:g_sal)
PRINT g_deptno

-----------------------------------------------------------------

-- 6) EMP 테이블에서 급여를 수정시 현재의 값보다 적게 수정할 수 없으며
-- 현재의 값보다 10% 이상 높게 수정할 수 없습니다.
-- 이러한 조건을 만족하는 트리거를 작성하십시오.

CREATE OR REPLACE TRIGGER emp_sal_chk_trigger
BEFORE UPDATE OF sal ON emp
    FOR EACH ROW WHEN (NEW.sal < OLD.sal
    OR NEW.sal > OLD.sal * 1.1)
BEGIN
    RAISE_APPLICATION_ERROR(-20502,
    'May not decrease salary. Increase must be < 10%');
END;
/

UPDATE emp SET sal = 6000;

-----------------------------------------------------------

-- 7) EMP 테이블을 사용할 수 있는 시간은 월요일부터 금요일까지
-- 18시부터 24시까지만 사용할 수 있도록 하는 트리거를 작성하십시오.

CREATE OR REPLACE TRIGGER emp_resource_trigger
    BEFORE INSERT OR UPDATE OR DELETE ON emp
BEGIN
    IF TO_CHAR(SYSDATE,'DY') IN ('SAT','SUN')
        OR TO_NUMBER(TO_CHAR(SYSDATE,'HH24'))
    NOT BETWEEN 18 AND 24 THEN
        RAISE_APPLICATION_ERROR(-20502,
        '작업할 수 없는 시간 입니다.');
END IF;
END;
/

SELECT to_char(sysdate,'hh24') FROM dual;

UPDATE emp SET deptno = 50
WHERE ename='scott';

--------------------------------------------------

-- package 예시

SET SERVEROUTPUT ON
-- 콘솔(DB console) 출력 (DBMS_OUTPUT.PUT_LINE) 활성화

CREATE OR REPLACE PACKAGE pkg_overload
IS
   PROCEDURE pro_emp(in_empno IN EMP.EMPNO%TYPE);
   PROCEDURE pro_emp(in_ename IN EMP.ENAME%TYPE);
END;
/

CREATE OR REPLACE PACKAGE BODY pkg_overload
IS
   PROCEDURE pro_emp(in_empno IN EMP.EMPNO%TYPE)
      IS
         out_ename EMP.ENAME%TYPE;
         out_sal EMP.SAL%TYPE;
      BEGIN
         SELECT ENAME, SAL INTO out_ename, out_sal
           FROM EMP
          WHERE EMPNO = in_empno;

         DBMS_OUTPUT.PUT_LINE('ENAME : ' || out_ename);
         DBMS_OUTPUT.PUT_LINE('SAL : ' || out_sal);
      END pro_emp;

   PROCEDURE pro_emp(in_ename IN EMP.ENAME%TYPE)
      IS
         out_ename EMP.ENAME%TYPE;
         out_sal EMP.SAL%TYPE;
      BEGIN
         SELECT ENAME, SAL INTO out_ename, out_sal
           FROM EMP
          WHERE ENAME = in_ename;

         DBMS_OUTPUT.PUT_LINE('ENAME : ' || out_ename);
         DBMS_OUTPUT.PUT_LINE('SAL : ' || out_sal);
      END pro_emp;

END;
/


BEGIN  
   DBMS_OUTPUT.PUT_LINE('패키지 프로시저 호출 : pkg_overload.pro_emp(7788)' );
   pkg_overload.pro_emp(7788);

   DBMS_OUTPUT.PUT_LINE('패키지 프로시저 호출 : pkg_overload.pro_emp(''SCOTT'')' );
   pkg_overload.pro_emp('SCOTT');
END;
/