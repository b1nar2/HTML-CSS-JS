1.(hr 계정) (동등 조인) 옥스포드(Oxford)시에 거주하는 직원의 이름을 오름차순으로 출력하십시오.

SELECT e.first_name || ' ' || e.last_name "이름"
FROM employees e, departments d, locations l
WHERE e.department_id = d.department_id  
  AND d.location_id = l.location_id
  AND l.city ='Oxford'
ORDER BY first_name ASC;


2. (hr 계정) (동등 조인) 퇴직일과 관계없이 2002년 1월 1일 이전에 근무하기 시작한 사람들의 
-- 아이디와 이름(성함을 합쳐서 출력함)
-- 을 화면에 인쇄하십시오.

SELECT DISTINCT e.employee_id,
                (e.first_name || ' ' || e.last_name) AS "이름"
FROM employees e, job_history j
WHERE e.employee_id = j.employee_id
    AND  j.start_date < '2002/01/01';
 

3. (hr 계정) (동등 조인) 시애틀에 사는 모든 사람들의 이름(last_name), 부서 명, 지역 ID 및 도시 명을
-- 조회합니다.
 

SELECT  last_name,department_name,
        l.location_id,city
FROM    employees e, departments d, locations l
WHERE   e. department_id = d.department_id
    AND     d.location_id = l.location_id
    AND     l.city = 'Seattle';
 

4. (hr 계정) (self 조인) 자신의 관리자(manager)보다 먼저 고용된 사원들의 이름(last_name) 및 
-- 고용일(hire_date)을 조회합니다.

SELECT e.last_name, e.employee_id, e.hire_date
FROM   employees e, employees m
WHERE  e.manager_id = m.employee_id
    AND     e.hire_date < m.hire_date
ORDER   BY e.last_name;

5. (hr 계정) (동등 조인) 부서 명, 위치 ID, 각 부서 별(로 묶어서) 총 사원수, 각 부서 별 평균 연봉(별칭 
-- "평균 연봉")을 조회합니다. 단, 평균 연봉은 소수점 2자리까지만 표현한다.

SELECT  department_name, location_id,
        COUNT(employee_id), ROUND(AVG(salary), 2) "평균 연봉"
FROM    employees e, departments d
WHERE   e.department_id = d.department_id
GROUP   BY department_name, location_id
ORDER   BY location_id;


6. (hr 계정) (동등 조인) 부서 번호가 30 이거나 90인 부서의 모든 직무 아이디 및 부서 아이디를 중복을 
-- 배제하여 직무 아이디 순으로 오름차순으로 정렬하여 조회하십시오.

SELECT  DISTINCT job_id, d.department_id
FROM    employees e, departments d
WHERE   e.department_id = d.department_id
    AND     d.department_id IN (30,90)
ORDER   BY job_id;