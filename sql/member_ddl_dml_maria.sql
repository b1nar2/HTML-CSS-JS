CREATE TABLE member_tbl (
id VARCHAR(20) PRIMARY KEY COMMENT '아이디',
pw VARCHAR(20) NOT NULL COMMENT '패쓰워드',
name VARCHAR(100) NOT NULL COMMENT '이름',
gender CHAR NOT NULL COMMENT '성별',
email VARCHAR(50) NOT NULL UNIQUE COMMENT '이메일',
mobile VARCHAR(13) NOT NULL UNIQUE COMMENT '연락처1(휴대폰)',
phone VARCHAR(13) NOT NULL COMMENT '연락처2(일반전화)',
zip CHAR(5) COMMENT '우편번호',
road_address VARCHAR(100) COMMENT '도로명 주소',
jibun_address VARCHAR(100) COMMENT '지번 주소',
detail_address VARCHAR(50) COMMENT '상세 주소',
birthday DATE COMMENT '생년월일',
joindate DATE DEFAULT CURRENT_TIMESTAMP() COMMENT '가입일'
-- joindate DATE DEFAULT NOW() COMMENT '가입일'
);

DROP TABLE member_tbl;

---------------------------------------------------------------

CREATE TABLE member_tbl (
id VARCHAR(20) PRIMARY KEY,
pw VARCHAR(20),
name VARCHAR(100),
gender CHAR,
email VARCHAR(50),
mobile VARCHAR(13),
phone VARCHAR(13),
zip CHAR(5),
road_address VARCHAR(100),
jibun_address VARCHAR(100),
detail_address VARCHAR(50),
birthday DATE,
joindate DATE
);

ALTER TABLE member_tbl MODIFY id VARCHAR(20) NOT NULL COMMENT '아이디';
ALTER TABLE member_tbl MODIFY pw VARCHAR(20) NOT NULL COMMENT '패쓰워드';
ALTER TABLE member_tbl MODIFY name VARCHAR(100) NOT NULL COMMENT '이름';
ALTER TABLE member_tbl MODIFY gender CHAR NOT NULL COMMENT '성별';
ALTER TABLE member_tbl MODIFY email VARCHAR(50) NOT NULL COMMENT '이메일';
ALTER TABLE member_tbl MODIFY mobile VARCHAR(13) NOT NULL COMMENT '연락처1(휴대폰)';
ALTER TABLE member_tbl MODIFY phone VARCHAR(13) NOT NULL COMMENT '연락처2(일반전화)';
ALTER TABLE member_tbl MODIFY zip CHAR(5) COMMENT '우편번호';
ALTER TABLE member_tbl MODIFY road_address VARCHAR(100) COMMENT '도로명 주소';
ALTER TABLE member_tbl MODIFY jibun_address VARCHAR(100) COMMENT '지번 주소';
ALTER TABLE member_tbl MODIFY detail_address VARCHAR(50) COMMENT '상세 주소';
ALTER TABLE member_tbl MODIFY birthday DATE COMMENT '생년월일';
ALTER TABLE member_tbl MODIFY joindate DATE DEFAULT CURRENT_TIMESTAMP() COMMENT '가입일';
 
DROP TABLE member_tbl;

----------------------------------------------------------------------------------------

-- constraint : https://mariadb.com/docs/server/reference/sql-statements/data-definition/constraint

CREATE TABLE member_tbl (
id VARCHAR(20) COMMENT '아이디',
pw VARCHAR(20) COMMENT '패쓰워드',
name VARCHAR(100) COMMENT '이름',
gender CHAR COMMENT '성별',
email VARCHAR(50) COMMENT '이메일',
mobile VARCHAR(13) COMMENT '연락처1(휴대폰)',
phone VARCHAR(13) COMMENT '연락처2(일반전화)',
zip CHAR(5) COMMENT '우편번호',
road_address VARCHAR(100) COMMENT '도로명 주소',
jibun_address VARCHAR(100) COMMENT '지번 주소',
detail_address VARCHAR(50) COMMENT '상세 주소',
birthday DATE COMMENT '생년월일',
joindate DATE DEFAULT NOW() COMMENT '가입일',
CONSTRAINT member_tbl_pw_nn CHECK (pw IS NOT NULL),
CONSTRAINT member_tbl_name_nn CHECK (name IS NOT NULL),
CONSTRAINT member_tbl_gender_nn CHECK (gender IS NOT NULL), 
CONSTRAINT member_tbl_email_nn CHECK (email IS NOT NULL), 
CONSTRAINT member_tbl_mobile_nn CHECK (email IS NOT NULL),
CONSTRAINT member_tbl_phone_nn CHECK (phone IS NOT NULL),  
CONSTRAINT member_tbl_id_pk PRIMARY KEY(id),
CONSTRAINT member_tbl_email_u UNIQUE(email),
CONSTRAINT member_tbl_mobile_u UNIQUE(mobile)
);

DROP TABLE member_tbl;

----------------------------------------------------------------------------------------

CREATE TABLE member_tbl (
id VARCHAR(20) COMMENT '아이디',
pw VARCHAR(20) COMMENT '패쓰워드',
name VARCHAR(100) COMMENT '이름',
gender CHAR COMMENT '성별',
email VARCHAR(50) COMMENT '이메일',
mobile VARCHAR(13) COMMENT '연락처1(휴대폰)',
phone VARCHAR(13) COMMENT '연락처2(일반전화)',
zip CHAR(5) COMMENT '우편번호',
road_address VARCHAR(100) COMMENT '도로명 주소',
jibun_address VARCHAR(100) COMMENT '지번 주소',
detail_address VARCHAR(50) COMMENT '상세 주소',
birthday DATE COMMENT '생년월일',
joindate DATE DEFAULT NOW() COMMENT '가입일'
);

-- 참고) NOT NULL 제약 조건
ALTER TABLE member_tbl MODIFY pw VARCHAR(20) NOT NULL; -- 테이블명세에 바로 표시됨
ALTER TABLE member_tbl ADD CONSTRAINT member_tbl_pw_nn CHECK (pw IS NOT NULL);
-- 테이블 명세에 표시되지 않고 제약조건확인에 표시됨

ALTER TABLE member_tbl ADD CONSTRAINT member_tbl_pw_nn CHECK (pw IS NOT NULL);
ALTER TABLE member_tbl ADD CONSTRAINT member_tbl_name_nn CHECK (name IS NOT NULL);
ALTER TABLE member_tbl ADD CONSTRAINT member_tbl_gender_nn CHECK (gender IS NOT NULL); 
ALTER TABLE member_tbl ADD CONSTRAINT member_tbl_email_nn CHECK (email IS NOT NULL); 
ALTER TABLE member_tbl ADD CONSTRAINT member_tbl_mobile_nn CHECK (email IS NOT NULL);
ALTER TABLE member_tbl ADD CONSTRAINT member_tbl_phone_nn CHECK (phone IS NOT NULL);  
ALTER TABLE member_tbl ADD CONSTRAINT member_tbl_id_pk PRIMARY KEY(id);
ALTER TABLE member_tbl ADD CONSTRAINT member_tbl_email_u UNIQUE(email);
ALTER TABLE member_tbl ADD CONSTRAINT member_tbl_mobile_u UNIQUE(mobile);

DROP TABLE member_tbl;

-------------------------------------------------------------------------------

-- 제약조건(constraint) 삭제 예시
 
ALTER TABLE member_tbl MODIFY pw VARCHAR(20) NOT NULL; -- NOT NULL 설정
ALTER TABLE member_tbl MODIFY pw VARCHAR(20); -- NOT NULL 해제

ALTER TABLE member_tbl DROP CONSTRAINT member_tbl_email_u;
ALTER TABLE member_tbl DROP CONSTRAINT member_tbl_mobile_u;

-- PK(기본키) 제약조건 해제
ALTER TABLE member_tbl DROP CONSTRAINT member_tbl_id_pk; -- 실행 안됨!
ALTER TABLE member_tbl DROP PRIMARY KEY; -- 실행!

-- 제약 조건 활성화/비활성화
-- 아래의 두 구문은MARIADB에서는실행되지 않음.
ALTER TABLE member_tbl ENABLE CONSTRAINT member_tbl_email_u; 
ALTER TABLE member_tbl DISABLE CONSTRAINT member_tbl_email_u;

-- 키(기본키, 외래키) 제약 조건 활성화/비활성화
ALTER TABLE member_tbl ENABLE KEYS;
ALTER TABLE member_tbl DISABLE KEYS;

-- 성별필드(gender) 에 대한 CHECK 제약조건추가
ALTER TABLE temp ADD CONSTRAINT member_tbl_gender_ck CHECK (gender IN ('m', 'f'));
ALTER TABLE temp ADD CONSTRAINT member_tbl_gender_ck CHECK (gender = 'm' OR gender = 'f');

-------------------------------------------------------------------------------------------

INSERT INTO member_tbl VALUES
('abcd1111',
 '#Abcd1234',
 '홍길동',
 'm',
 'abcd1111@abcd.com',
 '010-1111-3333',
 '02-1111-2222',
 '08290',
 '경기도 성남시 분당구 돌마로 47 (금곡동)',
 '경기도 성남시 분당구 금곡동 166 이코노샤르망',
 '4층 그린컴퓨터아카데미 별관',
 '2000-01-01',
  NOW());
  
  
INSERT INTO member_tbl VALUES
('abcd2222',
 '#Abcd1234',
 '류관순',
 'f',
 'abcd2222@efgh.com',
 '010-2222-7777',
 '02-1111-2222',
 '08290',
 '경기도 성남시 분당구 돌마로 47 (금곡동)',
 '경기도 성남시 분당구 금곡동 166 이코노샤르망',
 '6층 이젠 컴퓨터 아카데미',
 '2000-01-01',
  STR_TO_DATE('Wednesday, June 2, 2014', '%W, %M %e, %Y')
);           

 UPDATE member_tbl SET
 pw='#Abcd1234',
 email='java@djkangnam.com',
 zip='08290',
 jibun_address='경기도 성남시 분당구 구미동 7-2',
 detail_address='광천빌딩 5층 그린컴퓨터아카데미'
 WHERE id='abcd1111';
 
 SELECT * FROM member_tbl;
 
 SELECT * FROM member_tbl
 WHERE id='abcd1111';
 
 DELETE FROM member_tbl;
 
 DELETE FROM member_tbl
 WHERE id='abcd1111';