
1. (scott 계정) 업무가 SALESMAN인 사원만 사원번호,이름,급여,부서명,근무지를 출력하십시오.


﻿SELECT e.empno,e.ename,e.sal,d.dname,d.loc
FROM dept d,emp e
WHERE d.deptno = e.deptno AND e.job = 'SALESMAN';

SELECT e.empno,e.ename,e.sal,d.dname,d.loc
FROM dept d JOIN emp e
USING(deptno)
WHERE e.job = 'SALESMAN';

SELECT e.empno,e.ename,e.sal,d.dname,d.loc
FROM dept d NATURAL JOIN emp e
WHERE e.job = 'SALESMAN';

SELECT e.empno,e.ename,e.sal,d.dname,d.loc
FROM dept d INNER JOIN emp e
ON d.deptno = e.deptno
WHERE e.job = 'SALESMAN';

SELECT e.empno,e.ename,e.sal,d.dname,d.loc
FROM dept d JOIN emp e
ON d.deptno = e.deptno
WHERE e.job = 'SALESMAN';
내용을 입력하세요.

2. (hr 계정) 부서, 회원, 지역 테이블에서 아래와 같이 사원번호, 이름(별칭), 직무 아이디, 부서 아이디, 부서명, 지역(별칭)을 동등 조인을 활용하여 조회하십시오.


﻿SELECT e.employee_id,
e.first_name || ' ' || e.last_name "이름",
e.job_id,
e.department_id,
d.department_name,
l.city || ' ' || l.state_province "지역"
FROM departments d, employees e, locations l
WHERE d.department_id = e.department_id
ORDER BY d.department_id;


SELECT e.employee_id,
e.first_name || ' ' || e.last_name "이름",
e.job_id,
e.department_id,
d.department_name,
l.city || ' ' || l.state_province "지역"
FROM departments d INNER JOIN employees e
ON d.department_id = e.department_id
INNER JOIN locations l
ON d.location_id = l.location_id
ORDER BY d.department_id;


SELECT e.employee_id,
e.first_name || ' ' || e.last_name "이름",
e.job_id,
e.department_id,
d.department_name,
l.city || ' ' || l.state_province "지역"
FROM departments d JOIN employees e
ON d.department_id = e.department_id
JOIN locations l
ON d.location_id = l.location_id
ORDER BY d.department_id;


SELECT e.employee_id,
e.first_name || ' ' || e.last_name "이름",
e.job_id,
department_id,
d.department_name,
l.city || ' ' || l.state_province "지역"
FROM departments d JOIN employees e
USING(department_id)
JOIN locations l
USING(location_id)
ORDER BY department_id;


-- 주의) 아래의 경우는 문법은 맞지만 유독 Natural Join의 결과는 기존과 다름

SELECT e.employee_id,
e.first_name || ' ' || e.last_name "이름",
e.job_id,
department_id,
d.department_name,
l.city || ' ' || l.state_province "지역"
FROM departments d NATURAL JOIN employees e
NATURAL JOIN locations l
ORDER BY department_id;﻿
내용을 입력하세요.

3. (hr 계정) 2번의 검색 조건에 직무 아이디가 IT_PROG인 조건을 추가하여 동등 조인으로 검색하십시오.


﻿SELECT employee_id,
first_name || ' ' || last_name "이름",
job_id,
e.department_id,
department_name,
l.city || ' ' || l.state_province "지역"
FROM departments d, employees e, locations l
WHERE d.department_id = e.department_id
AND e.job_id = 'IT_PROG'
ORDER BY d.department_id;


-- 주의사항) 위의 동등 조인의 경우는 3개의 테이블의 공통키가 department_id가 성립되지 않기 때문에
location 테이블과의 관계는 원하는 동등 조인의 조건대로 출력되지 않고 중복된 행이 출력된다.
이러한 경우는 department테이블과 location 테이블의 공통 키를 아래와 같이 where에 추가하면 해결된다.


SELECT e.employee_id,
e.first_name || ' ' || e.last_name "이름",
e.job_id,
e.department_id,
d.department_name,
l.city || ' ' || l.state_province "지역"
FROM departments d, employees e, locations l
WHERE d.department_id = e.department_id
AND d.location_id = l.location_id
AND e.job_id = 'IT_PROG'
ORDER BY d.department_id;


SELECT e.employee_id,
e.first_name || ' ' || e.last_name "이름",
e.job_id,
e.department_id,
d.department_name,
l.city || ' ' || l.state_province "지역"
FROM departments d INNER JOIN employees e
ON d.department_id = e.department_id and e.job_id = 'IT_PROG'
INNER JOIN locations l
ON d.location_id = l.location_id and e.job_id = 'IT_PROG'
ORDER BY d.department_id;


SELECT e.employee_id,
e.first_name || ' ' || e.last_name "이름",
e.job_id,
e.department_id,
d.department_name,
l.city || ' ' || l.state_province "지역"
FROM departments d INNER JOIN employees e
ON d.department_id = e.department_id
INNER JOIN locations l
ON d.location_id = l.location_id
WHERE e.job_id = 'IT_PROG'
ORDER BY d.department_id;


SELECT e.employee_id,
e.first_name || ' ' || e.last_name "이름",
e.job_id,
e.department_id,
d.department_name,
l.city || ' ' || l.state_province "지역"
FROM departments d JOIN employees e
ON d.department_id = e.department_id
JOIN locations l
ON d.location_id = l.location_id
WHERE e.job_id = 'IT_PROG'
ORDER BY d.department_id;


SELECT e.employee_id,
e.first_name || ' ' || e.last_name "이름",
e.job_id,
department_id,
d.department_name,
l.city || ' ' || l.state_province "지역"
FROM departments d JOIN employees e
USING(department_id)
JOIN locations l
USING(location_id)
WHERE e.job_id = 'IT_PROG'
ORDER BY department_id;


-- 주의) 아래의 경우는 문법은 맞지만 유독 Natural Join의 결과는 기존과 다름


SELECT e.employee_id,
e.first_name || ' ' || e.last_name "이름",
e.job_id,
department_id,
d.department_name,
l.city || ' ' || l.state_province "지역"
FROM departments d NATURAL JOIN employees e
NATURAL JOIN locations l
WHERE e.job_id = 'IT_PROG'
ORDER BY department_id;﻿
내용을 입력하세요.

﻿