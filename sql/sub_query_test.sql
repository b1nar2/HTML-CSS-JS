

-- 결과 참고) Nancy  Greenberg 12008

-- Hint) 풀이 요령 : Where 조건문에서의 Hint

-- 부서 테이블에서 Finance 에 해당하는 부서 아이디를 구하는 select문을 작성한다.
-- 위의 구문을 활용하여 사원 테이블에서 부서의 최고 연봉을 구하는 select문을 작성한다.
-- 위의 조건만으로는 값이 "결과 참고"와 같이 출력되지 않고 중복될 수 있다.
-- 이것을 막기 위해서는 아래의 조건을 "동시에(AND)" 만족하도록 위의 조건들과 더불어
-- 부서 테이블에서 Finance 에 해당하는 부서 아이디를 구하는 select문을 재활용한다.

SELECT (first_name || ' ' || last_name) "사원명",
         salary "급여"
FROM employees
WHERE  department_id =( SELECT department_id
                        FROM departments
                        WHERE department_name='Finance' )
  AND  salary = (
                 SELECT MAX(salary)
                 FROM employees
                 WHERE department_id = ( SELECT department_id
                                         FROM departments
                                         WHERE department_name ='Finance')  
                );    

-- 2. 옥스포드(Oxford) 시에 거주하는 직원의 이름을 오름차순으로 출력하십시오. 

SELECT first_name || ' ' || last_name
FROM employees
WHERE department_id = (
                       SELECT d.department_id FROM departments d, locations l
                       WHERE d.location_id = l.location_id
                       AND l.city ='Oxford'
                      )
ORDER BY first_name;
         
--------------------------------------------------------------------------------

SELECT e.first_name || ' ' || e.last_name
FROM employees e, departments d, locations l
WHERE
      e.department_id = d.department_id  
  AND d.location_id = l.location_id
  AND l.city ='Oxford'
ORDER BY first_name;


-- 3. 퇴직일과 관계없이 2002년 1월 1일 이전에 근무하기 시작한 사람들의 아이디와 이름(성함을 합쳐서 출력함)
-- 을 화면에 인쇄하십시오. 단, hr 계정에 있는 테이블에서 작성하되 작성시 동등 조인(equi-join) 및 서브쿼리(sub 
-- query)을 이용하여 각각 작성하도록 합니다.

-- Hint) 풀이 요령 

-- 1) 조인을 이용하여 작성할 경우
-- 동등조건의 키워드는 사원 번호를 사용한다.
-- 근무 시작일 이라는 뜻을 가진 테이블과 사원 정보를 가진 테이블의 조인(join)이라고 볼 수 있다.
-- 이름은 두가지의 필드로 구성되었다.

-- 2) 서브 쿼리를 이용하여 작성할 경우
-- 전체적인 select 구문의 구조는 지난번 조인(Join) 구문과 동일하다.
-- 단, 조인이 아니기 때문에 검색 테이블이 두개 이상 나올 필요가 없다.

-- where 조건절에서의 비교 대상은 사원 번호로써 결과가 다수의 행이 결과로 출력되므로 동등 조건 검색이 
-- 아닌, IN 조건을 사용하여 검색하도록 한다.

-- IN 조건의 서브쿼리 내부는 근무 시작일을 where 조건절로 하여 사원 번호를 검색하는 서브쿼리를 작성한다.

SELECT DISTINCT e.employee_id,
                (e.first_name || ' ' || e.last_name) AS "이름"
FROM employees e, job_history j
WHERE e.employee_id = j.employee_id
AND  j.start_date < '2002/01/01';
 
--------------------------------------------------------------- 
   
SELECT DISTINCT e.employee_id,
               (e.first_name || ' ' || e.last_name) AS "이름"
FROM employees e
WHERE e.employee_id IN ( SELECT j.employee_id
                         FROM job_history j
                         WHERE j.start_date < '2002/01/01' );  


-- 4. 2005년 4월 1일 당일에 근무중이었으며, 미국 시애틀(Seattle)에 위치한 부서에서 근무했던 근로자들의 이름을 
-- 오름차순으로 출력하도록 조치하십시오. 중첩 쿼리(inner query)를 사용하여 풀이하도록 합니다.

-- Hint) 풀이 요령

-- 1) "지역" 테이블에서 시애틀에 해당되는 지역 ID 성분을 검색하는 구문을 작성한다.

-- 2) 이 구문을 where 조건으로 사용하여 "부서" 테이블에서 해당 부서 ID를 검색하는 구문을 작성한다.

-- 3) 이 구문을 where 조건으로 사용하여 "사원" 테이블에서 해당되는 이름을 검색하는 구문을 작성하되,
-- 우선적으로 where(최종 where 조건) 에 기입한다.

-- 4) 3)의 구문과 and 연산으로 묶을 쿼리(SQL)를 아래와 같이 작성한다.

-- 5) "업무 연혁(job_history)" 테이블에서 "업무 개시일"과 "업무 종료일"을 2005년 4월 1일에 근무하였다는
-- 기준에 의거하여 where 조건으로 위의 필드들과 부등호 등으로 연산(해당 일자 근무 여부)하여 
-- 해당되는 사원의 아이디를 구하는 구문을 작성한다.

-- 6) 3)까지 구한 쿼리와 5)에서 구한 쿼리를 4)의 과정에서 언급된 것과 같이 최종적으로 사원명을 구하는
-- 쿼리를 완성한다.

SELECT employee_id, (first_name || ' ' || last_name) "이름"
FROM employees e
WHERE employee_id IN (SELECT employee_id
                      FROM job_history j
                      WHERE j.department_id IN (SELECT department_id
                                                FROM job_history
                                                WHERE start_date <= '2005/04/01'
                                                AND end_date >= '2005/04/01')
                       AND department_id IN (SELECT department_id
                                             FROM departments d
                                             WHERE d.location_id IN (SELECT location_id
                                                                     FROM locations
                                                                     WHERE city = 'Seattle')
                      )
                 )                                                                
ORDER BY "이름";

---------------------------------------------------------------------------------------------

SELECT employee_id, first_name ||' '|| last_name "이름"
FROM employees e
WHERE employee_id IN (SELECT j.employee_id
                      FROM job_history j
                      WHERE (j.start_date < '2005/04/01'
                      AND j.end_date > '2005/04/01')
                   
AND j.department_id IN (SELECT d.department_id
                        FROM departments d
                        WHERE d.location_id = (SELECT l.location_id
                                               FROM locations l
                                               WHERE l.city = 'Seattle'))
                        )
ORDER BY "이름";