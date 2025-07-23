-- 1. LAST_NAME 이 DAVIES인 직원보다 늦게 고용된 사원들의 
-- LAST_NAME 및 HIRE_DATE 을 조회합니다.

SELECT  LAST_NAME, HIRE_DATE
FROM    EMPLOYEES
WHERE   HIRE_DATE >=  (
                        SELECT  HIRE_DATE
                        FROM    EMPLOYEES
                        WHERE   LAST_NAME = 'Davies'
                      )
ORDER   BY HIRE_DATE;

-- 2. 동일한 직업을 가진 사원들의 직무 아이디와 총 인원수를 조회합니다.

SELECT  JOB_ID, COUNT(EMPLOYEE_ID)
FROM    EMPLOYEES
GROUP   BY JOB_ID
ORDER   BY JOB_ID;

-- 3. 매니저(관리자)로 근무하는 사원들의 총 수를 조회합니다.

SELECT  COUNT(EMPLOYEE_ID)
FROM    EMPLOYEES
WHERE   EMPLOYEE_ID IN  (
                          SELECT  DISTINCT MANAGER_ID
                          FROM    EMPLOYEES
                          WHERE   MANAGER_ID IS NOT NULL
                        );
 
SELECT  COUNT(DISTINCT MANAGER_ID)
FROM    EMPLOYEES;

-- 4. 사내 최대 급여와 최저 급여의 차이를 구하여 아래와 같이 출력하십시오.

최고/최저 급여차액                                 
-------------------------------------------
21900 달러

SELECT  (MAX(SALARY) - MIN(SALARY) || ' 달러') "최고/최저 급여차액"
FROM  EMPLOYEES;

-- 5. 매니저(관리자)의 사번와 그 관리자 직속 사원들 중 최저 급여을 받는 사원의 
-- 급여를 아래의 조건에 맞게 조회합니다. 
-- 조건-1) 매니저(관리자)가 없는 사람들은 제외합니다.
-- 조건-2) 최저 급여가 6000 달러 미만인 경우는 제외합니다.
-- 조건-3) 최저 급여 기준으로 역순으로 출력합니다.

SELECT  MANAGER_ID, MIN(SALARY)
FROM    EMPLOYEES
WHERE   MANAGER_ID IS NOT NULL
GROUP   BY MANAGER_ID
HAVING  MIN(SALARY) >= 6000
ORDER   BY MIN(SALARY) DESC;