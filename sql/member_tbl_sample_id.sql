-- 임의 아이디 생성	
-- 97('a') ~ 122('z')
-- 문자열은 CONCAT 함수를 활용하여 결합, 		   
-- DBMS_RANDOM.VALUE(m, n) : m ~ n(미만) 사이의 임의의 실수(난수) 발생,
-- CHR : 숫자를 문자로 변환
-- FLOOR : 소숫점 내림처리
   
SELECT CHR(97+FLOOR(DBMS_RANDOM.VALUE(0, 26))) ||
       CHR(97+FLOOR(DBMS_RANDOM.VALUE(0, 26))) ||
       CHR(97+FLOOR(DBMS_RANDOM.VALUE(0, 26))) ||
       CHR(97+FLOOR(DBMS_RANDOM.VALUE(0, 26))) ||
       CHR(FLOOR(DBMS_RANDOM.VALUE(48, 57))) || 
       CHR(FLOOR(DBMS_RANDOM.VALUE(48, 57))) || 
       CHR(FLOOR(DBMS_RANDOM.VALUE(48, 57))) || 
       CHR(FLOOR(DBMS_RANDOM.VALUE(48, 57))) as rand_id
FROM DUAL;

SET SERVEROUTPUT ON; 

-- 기존 아이디 갱신

DECLARE
    v_id VARCHAR2(20);
BEGIN
    FOR i IN 1..100 LOOP
        
        SELECT CHR(97+FLOOR(DBMS_RANDOM.VALUE(0, 26))) ||
               CHR(97+FLOOR(DBMS_RANDOM.VALUE(0, 26))) ||
               CHR(97+FLOOR(DBMS_RANDOM.VALUE(0, 26))) ||
               CHR(97+FLOOR(DBMS_RANDOM.VALUE(0, 26))) ||
               CHR(FLOOR(DBMS_RANDOM.VALUE(48, 57))) || 
               CHR(FLOOR(DBMS_RANDOM.VALUE(48, 57))) || 
               CHR(FLOOR(DBMS_RANDOM.VALUE(48, 57))) || 
               CHR(FLOOR(DBMS_RANDOM.VALUE(48, 57))) 
               INTO v_id 
        FROM DUAL;
        
        DBMS_OUTPUT.put_line('id : ' || v_id);
        DBMS_OUTPUT.put_line('i : ' || i);
        
        UPDATE MEMBER_TBL 
        SET id = v_id
        WHERE id = (
            SELECT id
            FROM (
                SELECT id, ROW_NUMBER() OVER (ORDER BY rowid) AS row_num
                FROM MEMBER_TBL
                ORDER BY id
            )
            WHERE row_num = i
        );
        
        COMMIT;
        
    END LOOP;   

END;
/  