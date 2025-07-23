-- 아래는 hr 계정용 테스트 예시입니다.

-- 1) 부서 번호가 20 번 혹은 50 번 부서에 근무하며, 연봉이 5000 ~ 12,000 달러 사이인 사원들의 LAST_NAME 및 연봉을 조회합니다.

SELECT  last_name
        , salary
FROM    employees
WHERE   department_id IN ( 20, 50 )
AND     salary >= 5000
AND     salary <= 12000;

-- 2) 1994년도에 고용된 모든 사람들의 LAST_NAME 및 고용일을 조회합니다.

SELECT  last_name
        , hire_date
FROM    employees
WHERE   hire_date LIKE '1994%';


-- 3) 담당 매니저(관리자)가 없는 사원들의 LAST_NAME 및 JOB_ID 를 검색합니다.

SELECT  last_name,
        job_id,
        manager_id
FROM    employees
WHERE   manager_id IS NULL
OR      manager_id = '';

-- 4) 담당 매니저(관리자)가 있는 사원들의 LAST_NAME 및 JOB_ID 를 조회합니다.

SELECT  last_name,
        job_id,
        manager_id
FROM    employees
WHERE   manager_id IS NOT NULL
OR      manager_id != '';

-- 5) LAST_NAME 의 네번째 글자가 'a' 인 사원들의 LAST_NAME 을 조회합니다.

SELECT  last_name
FROM    employees
WHERE   last_name LIKE '___a%';

-- 6) LAST_NAME 에 'a'  또는 'e' 글자가 포함되어 있는 사원들의 LAST_NAME 을 조회합니다.

SELECT  last_name
FROM    employees
WHERE   last_name LIKE '%a%'
OR      last_name LIKE '%e%';

-- 7) 연봉이 2500, 3500, 7000 달러가 아니며, 직업이 SA_REP 혹은 ST_CLERK 인 사원들의 
-- LAST_NAME, 직업 아이디, 급여를 조회합니다.

SELECT  last_name,
        job_id,
        salary
FROM    employees
WHERE   salary NOT IN (2500, 3500, 7000)
AND     job_id IN ('SA_REP', 'ST_CLERK');

-- 8) 직업이 AD_PRESS인 사원은 A등급, ST_MAN인 사원은 B등급, IT_PROG인 사원은 C등급,
-- SA_REP인 사원은 D등급, ST_CLERK인 사원은 E등급을 기타는 0을 부여(할당)한 후,
-- 별칭(alias)를 JOB_GRADE로 할당하여 사원 아이디, FIRST_NAME, LAST_NAME, 등급(JOB_GRADE)을
-- 조회하는 구문을 작성합니다.

SELECT  employee_id,
        first_name,
        last_name,
        decode(job_id
            , 'AD_PRESS', 'A'
            , 'ST_MAN', 'B'
            , 'IT_PROG', 'C'
            , 'SA_REP', 'D'
            , 'ST_CLERK', 'E'
            , '0') job_grade
FROM    employees;


SELECT  *
FROM    (
            SELECT  employee_id,
                    first_name,
                    last_name,
                    decode(job_id
                        , 'AD_PRESS', 'A'
                        , 'ST_MAN', 'B'
                        , 'IT_PROG', 'C'
                        , 'SA_REP', 'D'
                        , 'ST_CLERK', 'E'
                        , '0') job_grade
            FROM    employees
        );
        
-- 참고) 특정 등급 검색
-- WHERE JOB_GRADE = 'B'; 추가

-- CASE WHEN 조건절 활용

SELECT  employee_id
        , first_name
        , last_name
        , CASE job_id
            WHEN 'AD_PRESS' THEN
                'A'
            WHEN 'ST_MAN' THEN
                'B'
            WHEN 'IT_PROG' THEN
                'C'
            WHEN 'SA_REP' THEN
                'D'
            WHEN 'ST_CLERK' THEN
                'E'
            ELSE '0'
        END grade
FROM    employees;