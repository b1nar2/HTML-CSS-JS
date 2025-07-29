-- 임의 회원정보 생성

-- 참고)
-- 임의의 수(random) : 1 => 'm', 2 => 'f'
  
/*
SELECT ROUND(DBMS_RANDOM.VALUE(1,2),0) FROM DUAL;
SELECT DECODE((SELECT ROUND(DBMS_RANDOM.VALUE(1,2),0) FROM DUAL), 1, 'm', 2, 'f') FROM DUAL;  
*/

-- DELETE FROM MEMBER_TBL;

DECLARE
    
    temp_random_gender NUMBER(1); 
    v_gender CHAR(1);
    
BEGIN
 
    FOR i IN 1..100 LOOP
    
        -- 성별 생성
        temp_random_gender := round(DBMS_RANDOM.VALUE(1, 2), 0);        
        SELECT DECODE(temp_random_gender, 1, 'm', 2, 'f') INTO v_gender FROM dual; 
    
        INSERT INTO member_tbl VALUES
        ('green' || (1000+i),
         '#Abcd1234',
         '김' || (100+i),
          v_gender,
         'green' || i || '@abcd.com',
         '010-1234-' || (1000+i),
         '031-712-7447',
         '08290',  
         '경기도 성남시 분당구 돌마로 47 (금곡동)',         
         '경기도 성남시 분당구 금곡동 166 이코노샤르망',
         '4층 그린컴퓨터아카데미 별관',
         '2000-01-01',
         SYSDATE);

     END LOOP;
 
    COMMIT;    
END;
/

-------------------------------------------------------------------------

-- 회원정보 테이블 회원명(이름) 현실화

SET SERVEROUTPUT ON; -- 실행 결과 화면 출력

-- 회원 정보 테이블 회원명(이름) 현실화

DECLARE
	TYPE first_name_array      IS VARRAY(50) OF NVARCHAR2(4);  
	TYPE middle_name_array      IS VARRAY(20) OF NVARCHAR2(1);  
	TYPE last_name_array      IS VARRAY(20) OF NVARCHAR2(1);  
	first_names   first_name_array;  
	middle_names   middle_name_array;  
	last_names   last_name_array;        
	v_first_name NVARCHAR2(4);  
	v_middle_name NVARCHAR2(1);  
	v_last_name NVARCHAR2(1);  
	total_name NVARCHAR2(6);  
	temp_num NUMBER(2); 
      
BEGIN  
	first_names := first_name_array('김','이','박','최','정','강','조','윤','장','임','한','오','서','신','권','황','안','송','류','전','홍','고','문','양','손','배','허','유','남','심','노','하','곽','성','차','주','우','구','도','추','민','소','남궁','독고','사공','제갈','선우','서문','동방','황보');  
	middle_names := middle_name_array('숙','갑','영','순','선','원','우','이','운','성','정','희','민','승','강','구','남','나','만','상');  
	last_names := last_name_array('영','수','희','빈','민','정','순','주','연','영','철','석','인','섭','훈','재','제','한','준','환');  
       
	FOR i IN 1..100 LOOP  
         
        temp_num := round(DBMS_RANDOM.VALUE(1,50),0);  
        v_first_name :=  first_names(temp_num);  
        temp_num := round(DBMS_RANDOM.VALUE(1,20),0);  
        v_middle_name :=  middle_names(temp_num);  
        temp_num := round(DBMS_RANDOM.VALUE(1,20),0);  
        v_last_name :=  last_names(temp_num);  
        total_name := v_first_name || v_middle_name || v_last_name;  
                         
        UPDATE member_tbl SET name = total_name  
        WHERE id = 'green' || (1000+i);       
        
	END LOOP;  

	COMMIT;         
      
END;  
/  

-------------------------------------------------------------------------

-- 생일(생년월일) 현실화

DECLARE 
    v_year CHAR(4);
    v_month CHAR(2);
    v_date CHAR(2);   
    temp_birthday CHAR(10);    
    temp_month NUMBER(2);
    temp_date NUMBER(2);
BEGIN

    FOR i IN 1..100 LOOP
    
        v_year := TO_CHAR(round(DBMS_RANDOM.VALUE(1900, 2024),0));
        
        temp_month := round(DBMS_RANDOM.VALUE(1,12),0);
        temp_date := round(DBMS_RANDOM.VALUE(1,30),0);
        
        -- 2월
        IF temp_month = 2 THEN
            temp_date := round(DBMS_RANDOM.VALUE(1,27),0);
        END IF;
                
        IF temp_month < 10 THEN
            v_month := '0' || TO_CHAR(temp_month); 
        ELSE
            v_month := TO_CHAR(temp_month); 
        END IF;
            
        IF temp_date < 10 THEN
            v_date :=  '0' || TO_CHAR(temp_date);
        ELSE 
            v_date := TO_CHAR(temp_date);
        END IF;    
        
        temp_birthday := TRIM(v_year || '-' || v_month || '-' || v_date);
                    
        UPDATE member_tbl SET birthday = TO_DATE(temp_birthday)    
        WHERE id = 'green' || (1000+i);
        
        COMMIT;
          
    END LOOP;

END;
/


-------------------------------------------------------------------------

-- 가입일 현실화

SET SERVEROUTPUT ON

DECLARE
    v_year CHAR(4);
    v_month CHAR(2);
    v_date CHAR(2);   
    temp_joindate CHAR(10);    
    temp_month NUMBER(2);
    temp_date NUMBER(2);
    curr_year NUMBER(4);
    curr_month NUMBER(2);
    curr_date NUMBER(2);
    
BEGIN

    FOR i IN 1..100 LOOP
    
        -- 사이트 개설일이 2015년이라는 전제로 2015년 부터 생성.
        v_year := TO_CHAR(round(DBMS_RANDOM.VALUE(2015,2025),0));
        
        -- 년도가 2025년도(올해)일 경우는 현재 날짜보다 이전으로 생성될 수 있도록 조치. 
        -- 단, 사이트 개설일 부터 2024년까지의 다른 해에 대한 월/일 기본값은 모든 월/일이 포함하도록 조치.
        
        -- 현재 날짜에 대한 년/월/일 조회
        -- select TO_CHAR(sysdate, 'YYYY') from dual;
        -- select REPLACE(TO_CHAR(sysdate, 'MM'), '0', '') from dual; -- 08 => 8 변환
        -- select REPLACE(TO_CHAR(sysdate, 'DD'), '0', '') from dual; -- 09 => 9 변환
                
        select TO_CHAR(sysdate, 'YYYY') into curr_year from dual;        
        select REPLACE(TO_CHAR(sysdate, 'MM'), '0', '') into curr_month from dual;
        select REPLACE(TO_CHAR(sysdate, 'DD'), '0', '') into curr_date from dual;
        
        DBMS_OUTPUT.put_line('번호 : ' || i);
        DBMS_OUTPUT.put_line('올해 : ' || curr_year);
        DBMS_OUTPUT.put_line('이번 달 : ' || curr_month);
        DBMS_OUTPUT.put_line('오늘 : ' || curr_date);
        
        -- 추출된 년도가 올해인 경우
        IF v_year = curr_year THEN
            temp_month := round(DBMS_RANDOM.VALUE(1,curr_month),0);
        -- 다른 해의 경우
        ELSE        
            temp_month := round(DBMS_RANDOM.VALUE(1,12),0);
        END IF;
        
        -- 올해 이번 달인 경우는 오늘까지만 가입일로 할당
        IF v_year = curr_year AND v_month = curr_month THEN
            temp_date := round(DBMS_RANDOM.VALUE(1,curr_date),0);
        ELSE    
            temp_date := round(DBMS_RANDOM.VALUE(1,30),0); -- 31일 배제(좀더 세부적으로 적용 위해서는 세부적인 조건문 필요) 
        END IF;    
        
        -- 2월
        IF temp_month = 2 THEN
            temp_date := round(DBMS_RANDOM.VALUE(1,27),0);
        END IF;
                
        IF temp_month < 10 THEN
            v_month := '0' || TO_CHAR(temp_month); 
        ELSE
            v_month := TO_CHAR(temp_month); 
        END IF;
        
        -- 10월 보다 적으면 '0' 부착 처리        
        IF temp_month < 10 THEN
            v_month := '0' || TO_CHAR(temp_month); 
        ELSE
            v_month := TO_CHAR(temp_month); 
        END IF;
            
        IF temp_date < 10 THEN
            v_date :=  '0' || TO_CHAR(temp_date);
        ELSE 
            v_date := TO_CHAR(temp_date);
        END IF;    
        
        temp_joindate := TRIM(v_year || '-' || v_month || '-' || v_date);
            
        DBMS_OUTPUT.put_line(temp_joindate);
        
        UPDATE member_tbl SET joindate = temp_joindate
        WHERE id = 'green' || (1000+i);
        
        COMMIT;
          
    END LOOP;

END;
/
