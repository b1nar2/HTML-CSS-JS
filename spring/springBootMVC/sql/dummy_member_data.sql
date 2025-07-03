USE express;

DELIMITER $$

CREATE OR REPLACE PROCEDURE test_members_gen_proc()  
BEGIN  
      
      SET @first_names  = '["김","이","박","최","주","임","엄","성","남궁","독고","황","황보","송","오","유","류","윤","장","정","추"]';  
      SET @middle_names = '["종","춘","영","순","선","원","우","이","운","성", "정", "산", "의", "석", "아", "자", "남", "나", "동", "승"]';  
    	SET @last_names =   '["영","수","희","빈","문","정","순","주","연","열", "철", "민", "환", "현", "준", "찬", "숙", "천", "강", "인"]';  

		# 기본주소 : 3종     
      SET @basic_address1 = '경기도 성남시 분당구 돌마로 47';      
		SET @basic_address2 = '경기도 성남시 분당구 구미동 211 무지개마을'; 
		SET @basic_address3 = '경기도 성남시 분당구 미금로 177 (구미동, 까치마을)'; 
		
      # 상세주소 : 3종
      SET @detail_address1 = '이코노 샤르망 4층 그린아카데미별관 410호';
      
		# 무지개 마을 : 101 ~ 111동, 1 ~ 15층, 01 ~ 04호
      SET @detail_address2 = CONCAT(CAST(101 + FLOOR(RAND()*12) AS CHAR(3)), '동 ', CAST(1 + FLOOR(RAND()*15) AS CHAR(2)), LPAD(CAST(1 + FLOOR(RAND()*4) AS CHAR(2)), 2, 0), '호'); 		
      
		# 까치 마을 : 101 ~ 116동, 1 ~ 19층, 01 ~ 04호
      SET @detail_address3 = CONCAT(CAST(101 + FLOOR(RAND()*17) AS CHAR(3)), '동 ', CAST(1 + FLOOR(RAND()*19) AS CHAR(2)), LPAD(CAST(1 + FLOOR(RAND()*4) AS CHAR(2)), 2, 0), '호'); 		 

      ##################################################################################
      
      # for문의 한계치를 조절하여 생성 인원수를 조절할 수 있습니다.
		FOR i IN 1..100 DO   
		
		   # 임의 아이디 생성	
		   # 97('a') ~ 122('z')
		   # 문자열은 CONCAT 함수를 활용하여 결합, 		   
			# RAND : 0 ~ 1(미만) 사이의 임의의 실수 발생,
			# CHR : 숫자를 문자로 변환
			# FLOOR : 소숫점 내림처리
			# CAST : 특정 자료형으로 형변환
		   SET @id = CONCAT(CHR(97+FLOOR(RAND()*26)),CHR(97+FLOOR(RAND()*26)),CHR(97+FLOOR(RAND()*26)),CHR(97+FLOOR(RAND()*26)),CAST(FLOOR(RAND() * 10000) AS CHAR));
						
			# 임의 이름 생성
			SET @temp_num = FLOOR(0 + (RAND() * 20));  # 20개의 성(姓)중에서 임의 선정	

         SET @v_firstName = JSON_VALUE(@first_names, CONCAT('$[',@temp_num,']'));  
     
   	   SET @temp_num = FLOOR(0 + (RAND() * 20)); # 이름 중간자를 선정
         SET @v_middleName = JSON_VALUE(@middle_names, CONCAT('$[',@temp_num,']'));  
        
         SET @temp_num = FLOOR(0 + (RAND() * 20)); # 이름 끝자를 선정        
         SET @v_lastName = JSON_VALUE(@last_names, CONCAT('$[',@temp_num,']'));  
        
         SET @totalName = CONCAT(@v_firstName, @v_middleName, @v_lastName);  # 이름 요소들 병합(이름 생성)
                 
         # 주소는 위의 3개의 주소항목에서 임의 선정 : 아파트의 경우는 동/호 임의 선정됨
         SET @address_num = FLOOR(1 + RAND() * 3); # 0 ~ 2까지 임의의 수 선정
         
	      # 기본주소 선정
     		SET @basic_address = case when @address_num = 1 then @basic_address1	when @address_num = 2 then @basic_address2  when @address_num = 3 then @basic_address3	END;   
     		
     		# 상세주소 선정
	      SET @detail_address = case when @address_num = 1 then @detail_address1	when @address_num = 2 then @detail_address2  when @address_num = 3 then @detail_address3	END;
	      
	      SET @address = CONCAT(@basic_address, @detail_address);
          			
         INSERT INTO test_tbl (id, name, address) VALUES (@id, @totalName, @address);
			
		END FOR;				
		
		COMMIT;

END $$

DELIMITER ;

CALL test_members_gen_proc();

-- SHOW CREATE PROCEDURE test_members_gen_proc;