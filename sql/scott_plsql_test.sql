-- PL/SQL에서의 트랜잭션 적용
-- 아래와 같이 트랜잭션 적용을 위해 임시 레코드를 작성합니다.

-- emp의 임시 복사 테이블(emp_temp) 작성
CREATE TABLE emp_temp as SELECT * FROM emp;

INSERT INTO emp_temp 
VALUES (9000, '홍길동', 'CLERK', 7839, sysdate, 10000, 0, 10);

DECLARE
	v_empno	emp.empno%TYPE := 9000;
BEGIN
	DELETE emp_temp
		WHERE empno = v_empno;
         COMMIT;
END;
/
-- sql*plus 혹은 PL/SQL 실행

-- SQL Cursor 속성 활용
-- 아래의 문장을 실행하기 위해 간단한 테이블 및 레코드들을 작성.
-- 사용후 scott 샘플 계정 보호 차원에서 테이블을 삭제할 것.

CREATE TABLE item (
    ord_id NUMBER(5,0) PRIMARY KEY,
    ord_name VARCHAR2(50) NOT NULL,
    ord_product VARCHAR2(100) NOT NULL,
    ord_quant NUMBER(5) NOT NULL,
    ord_date DATE DEFAULT sysdate
);

INSERT INTO item VALUES (100, '권율', '게임마우스', 50, sysdate);
INSERT INTO item VALUES (200, '이순신', '일반마우스', 10, sysdate);
INSERT INTO item VALUES (300, '강감찬', '마우스패드', 100, sysdate);

VARIABLE rows_del VARCHAR2(60)

DECLARE
	v_ord_id NUMBER := 100;
BEGIN
	DELETE FROM item
		WHERE  ord_id = v_ord_id;
	IF SQL%FOUND THEN
		:rows_del := SQL%ROWCOUNT || ' 행이 삭제됨.';
	ELSE
		:rows_del := '삭제된 자료가 없습니다.';
	END IF;
END;
/
-- sql*plus 혹은 PL/SQL 실행

PRINT rows_del

DROP TABLE item;

-----------------------------------------------------------------

-- IF 조건문

-- 1) 아래에 제시된 조건을 참고하여 이름,급여,부서번호를 입력받아 
-- EMP 테이블에 자료를 등록하는 SCRIPT를 작성하십시오.
-- 단, 10번 부서일 경우 입력한 급여의 20%를 추가하고 초기값이 9000부터 9999까지 
-- 1씩 증가하는 empno_sequence라는 시퀀스(SEQUENCE)를 작성하여 사용하십시오.

-- 이    름: 홍길동
-- 급    여: 2000
-- 부서번호: 10

-- 시퀀스 작성 (기존에 작성된 것이 있다면 그대로 사용하고 그렇지 않다면 아래와 같이 작성합니다.)
-- 아래의 모듈을 사용후 scott 샘플 계정 보호 차원에서 삽입된 레코드를 삭제하도록 합시다.

CREATE SEQUENCE empno_sequence
START WITH 9000
MAXVALUE 9999;

SET VERIFY OFF 
-- 명령어나 PL/SQL에서 "&" 기호를 활용한 치환변수 사용시 치환되기 전/후의 값을 표시할 지 여부를 결정
-- 기본값 ON

-- 인자를 입력받도록 프롬프트(prompt)로 표시
ACCEPT  p_name   PROMPT  ' 이    름: '
ACCEPT  p_sal    PROMPT  ' 급    여: '
ACCEPT  p_deptno PROMPT  ' 부서번호: '
DECLARE
	v_name		VARCHAR2(10) := UPPER('&p_name');
	v_sal		NUMBER(7,2) := &p_sal;
	v_deptno	NUMBER(2) := &p_deptno;
BEGIN
	IF v_deptno = 10 THEN
		v_sal := v_sal * 1.2;
	END IF;
	INSERT INTO emp_temp (empno,ename,sal,deptno)
		VALUES (empno_sequence.NEXTVAL,v_name,v_sal,v_deptno);
	COMMIT;
END;
/ 
-- sql*plus 혹은 PL/SQL 실행

SET VERIFY ON
-- 명령어나 PL/SQL에서 "&" 기호를 활용한 치환변수 사용시 치환되기 전/후의 값을 표시할 지 여부를 결정
-- 기본값 ON

-- 삽입된 임시 레코드 삭제
DELETE emp_temp WHERE ename='홍길동';

--------------------------------------------------------------------
