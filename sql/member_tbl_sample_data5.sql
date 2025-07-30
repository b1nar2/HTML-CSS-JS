-- 전화번호(집/사무실 전화) 자동 생성(갱신)

SET SERVEROUTPUT ON;

DECLARE
    TYPE seoul_guro_phone_array IS VARRAY(3) OF VARCHAR2(29); 
    seoul_guro_phones seoul_guro_phone_array;
    v_basic_address NVARCHAR2(100);
    v_phone VARCHAR2(29);
    temp_phone1 VARCHAR2(3);
    temp_phone2 VARCHAR2(4);
    temp_phone3 CHAR(4);
BEGIN

    FOR i IN 1..100 LOOP        
    
        -- 연락처(집/사무실번호) 형식 : 지역국번-숫자3~4자리(문자열)-숫자4자리(문자열)
        -- ex) 0310-782-5678
        
        -- 첫자리 : 서울특별시(02), 경기도 성남시(031)
        -- 둘째자리 : 서울특별시 => 구로구(), 영등포구(), 관악구(), 성남시 => 분당구() : 미금 한정

        -- 참고) 서울시 지역별 국번체제 : https://namu.wiki/w/%EA%B5%AD%EB%B2%88/%EC%84%9C%EC%9A%B8%ED%8A%B9%EB%B3%84%EC%8B%9C
        -- 2620~2624, 2626~2629 : 구로구 구로동(1호선 이북지역 제외), 가리봉동 전역, 금천구 가산동 전역, 독산동 북부, 관악구 신림동 서부[66]
        -- 2625 : 구로구 서부 및 광명시 철산동, 광명동, 학온동, 시흥시 과림동 동부
        -- 2600번대[54][55][56][57] : 강서구[58], 양천구, 구로구[59], 금천구 가산동 및 관공서[60], 영등포구 서부(당산동·문래동·양평동·영등포동·여의도동 일대[61] 및 관공서), 관악구 신림동 서부[62], 광명시 철산동·광명동·노온사동·가학동·옥길동 및 관공서[63], 시흥시 과림동(무지내동 제외), 논곡동(가학동 인접 길에서만)[64], 부천시 역곡동, 옥길동 일부[65]
        -- 800번대[85][86] : 동작구(동작동과 사당동 일부 제외.)[87][88], 영등포구 동부[89] 및 남부(신길동·대림동·도림동·여의도동 일부, 도림동과 그 외 지역(문래동, 영등포동 등등)은 26XX국번을 혼용한다.)[90], 금천구(관공서 제외)[91], 구로구 구로동 일부·가리봉동[92][93], 관악구(남현동 일부 제외)[94][95], 광명시 하안동·소하동·일직동 (관공서 제외)[96], 강남구 신분당선 신논현 이북(새서울철도) 구간[97]
        -- 818 : 구로구 가리봉동 전역, 구로동 중 경인선 이남지역, 금천구 가산동 전역, 독산동 북부, 관악구 신림동 서부
        -- 3281~3283 : 관악구 신림동 서부, 구로구 구로동(경인선 이북지역 제외), 가리봉동, 금천구 가산동 전역, 독산동 북부
        -- 3284, 3289 : 동작구 신대방동 서부, 영등포구 대림동, 도림동, 신길동 전역, 영등포동 중 경부선 이남지역
        
        -- 성남시 국번체제) https://namu.wiki/w/%EA%B5%AD%EB%B2%88/%EA%B2%BD%EA%B8%B0%EB%8F%84
        -- 701~719,724~728,780~789 : 성남시 분당구, 광주시 능평동, 신현동, 오포1 ~ 2동[40]
        -- ex) 중앙공원을 기준으로 이북(야탑, 이매, 서현)은 701~709, 724~725, 780~781, 783~785, 788~789국번을, 
        -- 이남(수내, 정자, 미금, 구미)은 710~719, 726~728, 782~787, 789국번을 주로 사용한다. 
        -- 미금 : 782~787
        
        -- 도로명 주소 조회 추출
        SELECT road_address INTO v_basic_address
        FROM (
            SELECT id, road_address, ROW_NUMBER() OVER (ORDER BY rowid) AS row_num
            FROM MEMBER_TBL
            ORDER BY id
        )
        WHERE row_num = i; 
        
        -- 시/도 구분
        IF INSTR(v_basic_address, '서울특별시') = 1 THEN
           temp_phone1 := '02';
           -- DBMS_OUTPUT.put_line('서울시 : ' || v_basic_address); 
           
           -- 구/동 구분
           IF INSTR(v_basic_address, '구로') = 1 THEN            
               SELECT round(DBMS_RANDOM.VALUE(2610, 2689)) INTO temp_phone2 FROM dual;                              
           ELSIF INSTR(v_basic_address, '관악') = 1 THEN            
               SELECT round(DBMS_RANDOM.VALUE(3281, 3283)) INTO temp_phone2 FROM dual;                    
           ELSIF INSTR(v_basic_address, '영등포구') = 1 THEN            
               temp_phone2 := 3284;
           ELSE -- 기본값 구로
               SELECT round(DBMS_RANDOM.VALUE(2610, 2689)) INTO temp_phone2 FROM dual;   
           END IF;    
            
        ELSE
           temp_phone1 := '031';           
           -- 분당구 구미동 : 782~787
           temp_phone2 := round(DBMS_RANDOM.VALUE(782, 787));
           -- DBMS_OUTPUT.put_line('경기도 : ' || v_basic_address);  
        END IF;   
        
        -- 연락처 끝자리 임의의 수(난수) 문자열 생성
        SELECT LPAD(TRUNC(DBMS_RANDOM.VALUE(0, 10000)), 4, '0') INTO temp_phone3 FROM dual;
        
        v_phone := temp_phone1 || '-' || temp_phone2 || '-' || temp_phone3;
        
        DBMS_OUTPUT.put_line('연락처(집/사무실번호) ' || v_phone);
        
        -- 연락처(집/사무실번호) 형식 : 지역국번-숫자3~4자리(문자열)-숫자4자리(문자열)
        -- ex) 0310-782-5678
        UPDATE MEMBER_TBL 
        SET phone = v_phone
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