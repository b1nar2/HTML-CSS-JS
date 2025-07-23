-- ※ 아래의 모든 문항은 HR 계정에 있는 테이블들을 기준으로 수행하시면 되겠습니다.

/*
1. 부서별로 2004년 이전에 입사한 직원들의 부서명, 인원수를 출력하는 SQL문을 작성하십시오.
단, 각 결과에 해당되는 필드의 별칭은 "부서명", "인원수"로 지정합니다.

조건)

1) 사원 테이블과 부서 테이블에서 공통되는 같은 키필드를 동등 조인을 이용하여 조건절(where)을 지정합니다.
2) 갯수를 집계하는 함수를 사용할 경우 부서명을 기준으로 하여 그룹핑을 합니다.
3) 2004년 보다 적은(이하) 이라는 조건을 지정할 경우 문자열 변환 함수(TO_CHAR)와 년도 포맷인 'YYYY'를 사용하여 비교합니다.
*/

SELECT d.department_name 부서명, COUNT(e.employee_id) 인원수
FROM employees e, departments d
WHERE e.department_id = d.department_id
  AND TO_CHAR(hire_date, 'YYYY') <= 2004
GROUP BY d.department_name;

/*
2. 아래와 같이 먼저 급여에 따른 직업 등급 테이블(job_grades)를 생성하고 'A'~'F'까지를 기본키로하는 데이터를 삽입합니다. 또한 각 컬럼들은 각각 grade_level은 '월급에 대한 등급', lowest_sal은 '최소 월급여' 그리고 highest_sal은 '최대 월급여'를 지칭합니다.

위와 같은 전제 조건을 만든 후 사원들의 이름(함자), 월급여, 부서명, 사원들의 급여 등급를 조회할 수 있는 SQL을 조건과 같이 작성합니다.
*/

CREATE TABLE job_grades
(
grade_level varchar2(3) CONSTRAINT job_gra_level_pk PRIMARY KEY,
lowest_sal NUMBER,
highest_sal NUMBER
);

INSERT INTO job_grades VALUES ('A', 1000, 2999);
INSERT INTO job_grades VALUES ('B', 3000, 5999);
INSERT INTO job_grades VALUES ('C', 6000, 9999);
INSERT INTO job_grades VALUES ('D', 10000, 14999);
INSERT INTO job_grades VALUES ('E', 15000, 24999);
INSERT INTO job_grades VALUES ('F', 25000, 40000);

COMMIT;

/*
조건)

1) 사원 테이블, 부서 테이블, 급여 등급 테이블 3개의 테이블을 조인(join)합니다.
2) 위의 테이블들의 각각의 별칭(alias)을 각각 e, d, g로 할당합니다.
3) 조건절(where)에서는 부서 아이디를 중심으로 동등(equi) 조인조건을 주고 아울러서 급여 컬럼을 중심으로 급여 등급 테이블에 있는 최소 급여와 최대 급여에서(non equi join) 검색할 수 있도록 조건을 할당한 후 AND 조건으로 묶어서 조건절을 구성합니다.
*/

SELECT first_name, salary, department_name, grade_level
FROM employees e, departments d, job_grades g
WHERE e.department_id = d.department_id
AND e.salary BETWEEN g.lowest_sal AND g.highest_sal;

/*
3. 사원 테이블에서 사원의 이름과 담당 관리자의 사원명을 조회하려고 합니다. 그러나 만약 사원의 이름과 담당 관리자의 사원번호가 아닌 담당 관리자의 이름을 조회하고자 할 때는 사원 테이블 하나로는 불가능하므로 셀프 조인(self join)을 이용하여 위의 구문을 작성합니다.

조건)

1) 사원 테이블을 각각 e, m 이라는 별칭을 할당합니다.
2) 조건절에서 담당 관리자 아이디를 동등 조인과 같이 담당자 아이디를 중심으로 할당합니다.
3) 사원 이름(함자 : first_name) "사원명"이라는 별칭을, 또한 관리자명(함자)은 "관리자명"이라는 별칭을 할당합니다.
*/

SELECT e.first_name 사원명, m.first_name 관리자명
FROM employees e, employees m
WHERE e.manager_id = m.employee_id;