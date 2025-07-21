-- 금일 날짜 구하기
/*금일 날짜 구하기*/
SELECT sysdate FROM dual;

SELECT ABS(-15) "absolute" FROM DUAL;

-- 연결 연산자
SELECT 'abcd' || 'defg' FROM DUAL;
SELECT CONCAT('abcd', 'defg') FROM DUAL;

-- 1)세션 시간대 변경/ 2)날짜 출력 형식 설정/ 3)현재 세션의 시간대, 날짜 조회

-- 1)
ALTER SESSION SET TIME_ZONE = '-5:0';
-- 2)
ALTER SESSION SET NLS_DATE_FORMAT='RRRR-MM-DD';
-- 3)
SELECT SESSIONTIMEZONE, CURRENT_DATE FROM DUAL;

--------------------------------------------------------------------------
/*연습*/

-- 문자열을 비교할 땐 알파벳 순서대로 대소를 비교.
SELECT * FROM EMP WHERE ENAME >= 'F';


-- 결과값 unknown => SAL > NULL : unknown
SELECT * FROM EMP WHERE SAL > NULL AND COMM IS NULL;


-- SAL > NULL : unknown, COMM IS NULL 출력됨
SELECT * FROM EMP WHERE SAL > NULL OR COMM IS NULL;


-- < UNION : 합집합. 중복 제거가 기본값, 모든 데이터를 출력하려면 UNION ALL 사용 >
-- 1), 2) 출력되는 데이터 같음. BUT UNION은 서로 다른 테이블,데이터 조회에도 활용 가능

-- 1)
SELECT EMPNO, ENAME, SAL, DEPTNO FROM EMP WHERE DEPTNO = 10
UNION
SELECT EMPNO, ENAME, SAL, DEPTNO FROM EMP WHERE DEPTNO = 20
ORDER BY SAL ASC;

-- 2)
SELECT EMPNO, ENAME, SAL, DEPTNO FROM EMP WHERE DEPTNO IN ('10','20') ORDER BY SAL ASC;


-- < MINUS : 차집합 >
SELECT EMPNO, ENAME, SAL, DEPTNO FROM EMP WHERE DEPTNO = 10
MINUS
SELECT EMPNO, ENAME, SAL, DEPTNO FROM EMP WHERE DEPTNO = 20;




-- <UPPER(대문자), LOWER(소문자), INITCAP(첫 글자 대문자, 이후 소문자) : 데이터를 대/소문자로 바꿔주는 함수 >
-- 대/소문자 구분 없이 특정 문자열의 데이터를 검색하고 싶을 때- > 해당 문자열을 대문자(소문자)로 변환하여 검색
-- 1)
SELECT ENAME FROM EMP WHERE LOWER(ENAME)LIKE '%m%i%';
/*작성자의 패턴('%m%i%')입력 실수 방지, 명확한 검색 가능*/
SELECT ENAME FROM EMP WHERE LOWER(ENAME)LIKE LOWER('%m%i%');

-- 2) 순서 상관 없이 조회
SELECT ENAME
FROM EMP
WHERE LOWER(ENAME) LIKE LOWER ('%m%')
AND LOWER(ENAME) LIKE LOWER ('%i%');


-- < LENGTH : 문자열 길이를 구하는 함수 >
-- n자 이상의 문자열 검색 추출
SELECT ENAME, LENGTH(ENAME) /*LENGTH(ENAME)문자의 개수 표기*/
FROM EMP
WHERE LENGTH(ENAME) >= 5;


-- < SUBSTR : 문자열 일부를 추출하는 함수 >
-- SUBSTR(문자열 데이터, 시작 위치[, 추출 길이])
SELECT JOB, SUBSTR(JOB,1,2), SUBSTR(JOB,3,2), SUBSTR(JOB,5) FROM EMP;


-- < SUBSTR + LENGTH >
SELECT JOB, SUBSTR(JOB, -LENGTH(JOB)), SUBSTR(JOB, -LENGTH(JOB),2), SUBSTR(JOB, -3) FROM EMP;
