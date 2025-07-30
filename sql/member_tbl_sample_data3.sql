-- 이메일 자동 생성(갱신)

SET SERVEROUTPUT ON;

DECLARE
    TYPE email_sites_array IS VARRAY(3) OF VARCHAR2(29); -- 아이디(20 + @(1) + 29 = 50 byte)
    v_email_site VARCHAR2(29);
    v_portal_email_sites email_sites_array;
    temp_random_index NUMBER(1,0);
    
BEGIN

    -- 포털 이메일 사이트 대입
    v_portal_email_sites := email_sites_array('naver.com', 'google.com', 'daum.net');
    
    FOR i IN 1..100 LOOP    
        
        -- 이메일 형식 : 아이디 + "@" + 포털 메일사이트(naver.com, goolge.com, daum.net)
        temp_random_index := ROUND(DBMS_RANDOM.VALUE(1, 3), 0); -- 반올림 => 1 ~ 3       
        DBMS_OUTPUT.put_line('index : ' || temp_random_index);
        
        v_email_site := v_portal_email_sites(temp_random_index);        
        -- DBMS_OUTPUT.put_line('이메일 사이트 : ' || v_email_site);
        
        -- 이메일 형식 : 아이디 + '@' + 포털 메일사이트(naver.com, goolge.com, daum.net)
        -- id || '@' || v_email_site
        UPDATE MEMBER_TBL 
        SET email = id || '@' || v_email_site
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