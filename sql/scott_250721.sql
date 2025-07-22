-- < INSTR : 문자열 데이터 안에 특정 문자나 문자열이 어디에 포함되는지 찾는 함수 >
/*
INSTR ( 대상 문자열 데이터[필수],
        찾으려는 문자[필수],
        위치 찾기를 시작할 대상 문자열 데이터 위치[선택, 기본값 : 1],
        시작 위치에서 찾으려는 문자가 몇 번째인지 지정[선택, 기본값 : 1] )

*/
SELECT INSTR('HELLO, ORACLE!', 'L') AS INSTR_1,         -- 3
       INSTR('HELLO, ORACLAE!', 'L', 5) AS INSTR_2,     -- 12
       INSTR('HELLO, ORACALE!', 'L', 3, 3) AS INSTR_3   -- 13
/*
 1   2   3   4   5   6   7   8   9  10  11  12  13  14
[H] [E] [L] [L] [0] [,] [ ] [O] [R] [A] [C] [L] [E] [!]
*/       
FROM DUAL;


-- < REPLACE : 특정 문자열 데이터에 포함된 문자를 다른 문자로 대체할 때 사용하는 함수 >
/*
REPLACE ( 문자열 데이터 또는 열 이름[필수],
          찾는 문자[필수],
          대체할 문자[선택])
          
        대체할 문자를 입력하지 않으면 '찾는 문자' 삭제됨
*/

SELECT REPLACE('010-1234-5678', '-'),        -- 01012345678
       REPLACE('010-1111-2222', '-', ' '),   -- 010 1111 2222
       REPLACE('010 1212 2323', ' ', '-')    -- 010-1212-2323

FROM DUAL;


-- < LPAD, RPAD : 데이터의 왼쪽, 오른쪽 공간을 특정 문자로 채우는 함수 >
/*
LPAD ( 문자열 데이터 또는 열 이름[필수],
       데이터의 자릿수[필수],
       공간에 채울 문자[선택])
       
       대체할 문자를 입력하지 않으면 공백문자로 처리됨
*/

SELECT

    LPAD ('HAPPYDAY', 15),
    LPAD ('HAPPYDAY', 15, '*'),
    RPAD ('HAPPYDAY', 15),
    RPAD ('HAPPYDAY', 15, '!'),
    
    RPAD ('010-1234-', 13, '*')
    
FROM DUAL;

-- < CONCAT : 2개 이상의 문자열 데이터를 하나로 연결하는 함수>
    /* || 연산자와 비슷한 기능 */
SELECT
    CONCAT (ENAME, EMPNO),
    CONCAT (ENAME, CONCAT( ' : ',EMPNO)),
/*  CONCAT (ENAME, CONCAT( " * ", EMPNO), CONCAT(" * ", JOB)) -- 오라클에서는 사용 불가. 2개까지만 인정 됨*/

FROM EMP
WHERE ENAME = 'SCOTT';

SELECT
    ENAME ||' : '|| EMPNO 
    ENAME ||' * '|| EMPNO || ' * ' || JOB
FROM EMP
WHERE ENAME ='SCOTT';


-- < TRIM, LTRIM, RTRIM : 문자열 데이터 내에서 특정 문자를 지울 때 사용하는 함수 >
/*
TRIM (삭제옵션[선택] 삭제할 문자[선택]
FROM 원본 문자열 데이터[필수])

삭제할 문자를 생략하면 기본적으로 공백을 제거함
삭제할 문자는 한 글자만 지정 가능
*/
SELECT '[' || TRIM(' _ _Oracle_ _ ') || ']',
       '[' || TRIM(LEADING FROM ' _ _Oracle_ _ ') || ']',
       '[' || TRIM(TRAILING FROM ' _ _Oracle_ _ ') || ']',
       '[' || TRIM(BOTH FROM ' _ _Oracle_ _ ') || ']',
       TRIM ('Y' FROM 'HAPPYDAY')
FROM DUAL;


-- < 숫자 데이터를 연산,수치 조정하는 숫자 함수 >
/*
1) ROUND(숫자[필수], 반올림 위치[선택]) -- 지정된 숫자의 특정 위치에서 반올림한 값을 반환.(지정하지 않으면 소수 첫 째 자리에서 반환)
2) TRUNC(숫자[필수], 버릴 수 위치[선택]) -- 지정된 숫자의 특정 위치에서 버림 한 값을 반환.(지정하지 않으면 소수 첫 째 자리에서 반환)
3) CEIL (숫자[필수]) -- 지정된 숫자보다 큰 정수 중 가장 적은 정수를 반환.
4) FLOOR (숫자[필수]) -- 지정된 숫자보다 작은 정수 중 가장 큰 정수를 반환.
5) MOD (나머지를 구할 숫자[필수], 나눌 숫자[필수] -- 지정된 숫자를 나눈 나머지를 반환.
*/

-- 1)ROUND
SELECT ROUND(1234.5678),
       ROUND(1234.5678, 0),
       ROUND(1234.5678, 1),
       ROUND(1234.5678, 2),
       ROUND(1234.5678, -1),
       ROUND(1234.5678, -2)
FROM DUAL;

-- 2)TRUNC
SELECT TRUNC(1234.5678),
       TRUNC(1234.5678, 0),
       TRUNC(1234.5678, 1),
       TRUNC(1234.5678, 2),
       TRUNC(1234.5678, -1),
       TRUNC(1234.5678, -2)
FROM DUAL;

-- 3)CEIL, FLOOR
SELECT CEIL(3.14),
       FLOOR(3.14),
       CEIL(-3.14),
       FLOOR(-3.14)
FROM DUAL;

-- 4)MOD
SELECT MOD(15, 6),
/*2로 나눈 결괏값을 이용해 짝수/홀수 구별 가능*/
       MOD(10, 2),
       MOD(11, 2)
FROM DUAL;