-- 전화번호(휴대폰) 자동 생성(갱신)

SET SERVEROUTPUT ON;

DECLARE
    v_mobile CHAR(13);
    temp_mobile1 CHAR(4);
    temp_mobile2 CHAR(4);
BEGIN

    FOR i IN 1..100 LOOP    
    
        -- 휴대폰 중간자리/끝자리 임의의 수(난수) 문자열 생성
        SELECT LPAD(TRUNC(DBMS_RANDOM.VALUE(0, 10000)), 4, '0') INTO temp_mobile1 FROM dual;
        SELECT LPAD(TRUNC(DBMS_RANDOM.VALUE(0, 10000)), 4, '0') INTO temp_mobile2 FROM dual;
        
        v_mobile := '010' || '-' || temp_mobile1 || '-' || temp_mobile2;
        
        DBMS_OUTPUT.put_line('연락처(휴대폰 ) ' || v_mobile);
        
        -- 연락처(휴대폰) 형식 : 010-숫자4자리(문자열)-숫자4자리(문자열)
        -- 010-1234-5678
        UPDATE MEMBER_TBL 
        SET mobile = v_mobile
        WHERE id = (
            SELECT id
            FROM (
                SELECT id, ROW_NUMBER() OVER (ORDER BY rowid) AS row_num
                FROM MEMBER_TBL
                ORDER BY id
            )
            WHERE row_num = i
        );
        
    END LOOP;
       
    COMMIT;   
    
END;
/