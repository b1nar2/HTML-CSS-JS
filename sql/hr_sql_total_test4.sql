※ 아래의 모든 문항은 HR 계정에 있는 테이블들을 기준으로 수행하시면 되겠습니다.

/*
1. 아래는 사원명과 관리자명, 그리고 관리자의 관리자명을 인쇄하는 SQL문이다.

SELECT e.last_name 사원명,
m.last_name 관리자명, mm.last_name "관리자의 관리자명"
FROM employees e, employees m, employees mm
WHERE e.manager_id = m.employee_id
AND m.manager_id = mm.employee_id;

위와 같이 사원명, 관리자명, 그리고 관리자의 관리자명을 구하기 위해서는 3개의 테이블을 조인해야 됩니다.

만약 위와 같이 같은 조인 결과에서 상위 관리자가 없는 모든 사원의 이름까지 출력까지 하기 위해서는 Outer 조인을 이용해야되는데 아래의 조건과 같이 outer 조인을 작성하십시오.

조건)

1) 사원명(성)을 "사원명" 이라는 별칭으로, 또한 관리자명을 "관리자명"이라는 별칭으로 할당하고 또한 같은 이름을 "관리자의 관리자명"으로 별칭을 할당하여 조회합니다.

2) FROM절에서의 조인은 같은 사원 테이블을 각각 e, m, mm 이라는 별칭을 사용하여 할당합니다.

3) 조건절(WHERE)에서는 관리자 아이디를 중심으로 Left Outer Join을 수행합니다.
= 연산자를 기준으로 좌측에서는 e.관리자 아이디를 배치하고 우측에는 m.사원아이디를 (+) outer join 연산자와 더불어 할당합니다.

4) 또한 조건절에서 AND 연산자를 사용하여 m.관리자 아이디와 mm.사원 아이디를 배치하고 위와 같이 (+) outer join 연산자와 더불어 할당합니다.
*/

SELECT e.last_name 사원명,
       m.last_name 관리자명, mm.last_name "관리자의 관리자명"
FROM employees e, employees m, employees mm
WHERE e.manager_id = m.employee_id(+)
     AND m.manager_id = mm.employee_id(+);

/*
2. 사원 테이블과 부서 테이블에서 부서 번호가 90번인 사원의 이름(성)과 부서명, 부서 아이디를 구하는 SQL 구문을 자연조인(Natural Join)을 이용하여 작성하십시오.

조건)

1) 사원명(성), 부서명, 부서 아이디를 조회합니다.
2) 사원 테이블과 부서 테이블을 NATURAL JOIN 이라는 키워드로 연결합니다.
*/

SELECT e.last_name, department_name, department_id
FROM employees e NATURAL JOIN departments d
WHERE department_id = 90;

/*
3. 지난주 작성된 job_grades 테이블과 아울러서 사원 테이블, 부서 테이블 3개의 테이블을 inner 조인하여
사원명(성), 부서명, 사원 등급을 구하는 SQL을 작성하십시오.

조건)

1) 각각 해당되는 컬럼에 사원명, 부서명, 등급이라는 별칭을 할당합니다.
2) 먼저 사원 테이블에 e 라는 별칭을 할당하고 부서 테이블에는 d라는 별칭을 할당하여 두 테이블간에 inner join 키워드를 이용하여 조인 관계를 만듭니다.
3) 이때 on 조건절을 두 테이블간의 부서 아이디를 동등 조건(=)으로 연결하여 줍니다.
4) 3)까지 작성된 SQL 구문 뒤에 inner join 키워드를 사용하여 사원 등급 테이블을 g 라는 별칭을 할당하면서
조인 관계를 만듭니다.
5) 역시 3) 과정처럼 on 조건절을 활용하여 사원 테이블의 월급여가 등급 테이블의 급여 최소값과 최대값 사이라는 조건을 between and 로 구성하여 마무리 합니다.
*/

SELECT e.last_name 사원명, d.department_name 부서명, g.grade_level 등급
FROM employees e INNER JOIN departments d
ON e.department_id = d.department_id
                 INNER JOIN job_grades g
ON e.salary BETWEEN g.lowest_sal AND g.highest_sal; 

/*
4. 사원 테이블에서 부서 번호가 100인 사원들 중에서 월급여가 가장 많은 사원과 동일한 월급을 받는 사원의 이름(성)과 월급여를 출력하는 SQL문을 서브 쿼리(sub query)를 활용하여 작성하십시오.

조건)

1) 사원명(성), 월급여 필드를 조회하는 구문을 작성한다.
2) where 조건절에서 월급여 필드를 "=" 연산자를 이용하여 3)에서 작성될 SQL문과 비교 연결합니다.
3) 부서 테이블에서 사원번호가 100인 사원의 월급여의 최대값을 MAX 함수를 이용하여 조회하는 구문을 작성합니다.
*/

SELECT last_name, salary
FROM employees
WHERE salary = (SELECT MAX(salary)
                FROM employees
                WHERE department_id = 100);