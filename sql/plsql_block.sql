DECLARE
    x NUMBER;
    y NUMBER;
BEGIN
    x := 1;
    y := 2;
    -- 이 지점에서 x, y의 값은 x := 1, y := 2
DECLARE
    y NUMBER;
    z NUMBER;
BEGIN
    -- 이 지점에서 x, y, z의 값은 x := 1, y := NULL, z := NULL
    x := 3;
    y := 4;
    z := 5;
    -- 이 지점에서 x, y, z의 값은 x := 3, y := 4, z := 5
    END;
    -- 이 지점에서 x, y, z의 값은 x := 3, y := 2, z는 참조 불가능합니다.
END;
/

---------------------------------------------------------

CREATE TABLE emp_temp AS SELECT * FROM emp;

DECLARE
    v_deptno emp_temp.deptno%TYPE;
BEGIN
    SELECT deptno
        INTO v_deptno
        FROM emp_temp
        WHERE empno = 7788;
    IF v_deptno IN (10,20) THEN
        UPDATE emp_temp
            SET sal = 3500
            WHERE empno = 7788;
    ELSE
        UPDATE emp_temp
            SET sal = 2500
            WHERE empno = 7788;
    END IF;
END;
/

DROP TABLE emp_temp;