-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE account_tbl (
    account_id       NUMBER NOT NULL,
    member_id        VARCHAR2(20) NOT NULL,
    account_bank     VARCHAR2(50) NOT NULL,
    account_number   VARCHAR2(20) NOT NULL,
    account_main     CHAR(1) DEFAULT 'N' NOT NULL,
    account_reg_date DATE DEFAULT SYSDATE
);

COMMENT ON COLUMN account_tbl.account_id IS
    '회원 계좌 고유번호 (PK)';

COMMENT ON COLUMN account_tbl.member_id IS
    '계좌 소유자 회원 ID (FK)';

COMMENT ON COLUMN account_tbl.account_bank IS
    '계좌 은행명 (예: 국민은행, 카카오뱅크 등)';

COMMENT ON COLUMN account_tbl.account_number IS
    '실제 계좌번호';

COMMENT ON COLUMN account_tbl.account_main IS
    '대표 계좌 여부 (Y/N)';

COMMENT ON COLUMN account_tbl.account_reg_date IS
    '계좌 등록일';

ALTER TABLE account_tbl
    ADD CONSTRAINT account_main_ch
        CHECK (account_main IN('Y', 'N') );

ALTER TABLE account_tbl ADD CONSTRAINT account_tbl_pk PRIMARY KEY ( account_id );


CREATE TABLE board_tbl (
    board_id       NUMBER NOT NULL,
    board_title    VARCHAR2(50) NOT NULL,
    board_content  VARCHAR2(100) NOT NULL,
    board_use      CHAR(1) DEFAULT 'Y' NOT NULL,
    board_reg_date DATE DEFAULT sysdate NOT NULL,
    board_mod_date DATE,
    member_id      VARCHAR2(20) NOT NULL
);

COMMENT ON COLUMN board_tbl.board_title IS
    '게시판 이름(공지사항 등)';

COMMENT ON COLUMN board_tbl.board_content IS
    '게시판 상단내용';

COMMENT ON COLUMN board_tbl.board_use IS
    '사용여부(활성화/비활성화)';

COMMENT ON COLUMN board_tbl.board_reg_date IS
    '생성일자(기본값 SYSDATE)';

COMMENT ON COLUMN board_tbl.board_mod_date IS
    '수정일자(수정시 갱신)';

COMMENT ON COLUMN board_tbl.member_id IS
    '회원id';

ALTER TABLE board_tbl
    ADD CONSTRAINT board_use_ch CHECK ( board_use IN ( 'Y', 'N' ) );

ALTER TABLE board_tbl ADD CONSTRAINT board_tbl_pk PRIMARY KEY ( board_id );

CREATE TABLE card_tbl (
    card_id       NUMBER NOT NULL,
    member_id     VARCHAR2(20) NOT NULL,
    card_bank     VARCHAR2(50) NOT NULL,
    card_number   VARCHAR2(20) NOT NULL,
    card_approval VARCHAR2(20),
    card_main     CHAR(1) DEFAULT 'N' NOT NULL,
    card_reg_date DATE DEFAULT sysdate
);

COMMENT ON COLUMN card_tbl.card_id IS
    '카드 고유번호 (PK)';

COMMENT ON COLUMN card_tbl.member_id IS
    '계좌 소유자 회원 ID (FK)';

COMMENT ON COLUMN card_tbl.card_bank IS
    '카드사명 (신한, 현대 등)';

COMMENT ON COLUMN card_tbl.card_number IS
    '카드번호';

COMMENT ON COLUMN card_tbl.card_approval IS
    '카드 승인번호';

COMMENT ON COLUMN card_tbl.card_main IS
    '대표 계좌 여부 (Y/N)';

COMMENT ON COLUMN card_tbl.card_reg_date IS
    '카드 등록일';

ALTER TABLE card_tbl
    ADD CONSTRAINT card_main_CH
        CHECK (card_main IN('Y', 'N') );

ALTER TABLE card_tbl ADD CONSTRAINT account_tblv1_pk PRIMARY KEY ( card_id );

--  카드번호 방지
ALTER TABLE card_tbl ADD CONSTRAINT card_number_un UNIQUE ( card_number );

CREATE TABLE closed_day_tbl (
    closed_id   NUMBER NOT NULL,
    facility_id NUMBER NOT NULL,
    closed_date DATE NOT NULL,
    reason      VARCHAR2(200)
);

COMMENT ON COLUMN closed_day_tbl.closed_id IS
    '휴무일 고유 번호';

COMMENT ON COLUMN closed_day_tbl.facility_id IS
    '대상 시설 ID';

COMMENT ON COLUMN closed_day_tbl.closed_date IS
    '휴무일 날짜';

COMMENT ON COLUMN closed_day_tbl.reason IS
    '휴무 사유';

ALTER TABLE closed_day_tbl ADD CONSTRAINT closed_day_tbl_pk PRIMARY KEY ( closed_id );

--  동일 시설 중복 방지
ALTER TABLE closed_day_tbl ADD CONSTRAINT closed_day_total_un UNIQUE ( facility_id,
                                                                       closed_date );

CREATE TABLE contents_tbl (
    content_id       NUMBER NOT NULL,
    content_title    VARCHAR2(100) NOT NULL,
    content_content  CLOB,
    member_id        VARCHAR2(20) NOT NULL,
    content_reg_date DATE DEFAULT sysdate,
    content_mod_date DATE,
    content_use      CHAR(1)
     DEFAULT 'Y' NOT NULL
);

COMMENT ON COLUMN contents_tbl.content_id IS
    '콘텐츠 고유 ID';

COMMENT ON COLUMN contents_tbl.content_title IS
    '콘텐츠 제목';

COMMENT ON COLUMN contents_tbl.member_id IS
    '회원ID';

COMMENT ON COLUMN contents_tbl.content_reg_date IS
    '작성일';

COMMENT ON COLUMN contents_tbl.content_mod_date IS
    '수정일';

COMMENT ON COLUMN contents_tbl.content_use IS
    '사용여부(활성화/비활성화)';

ALTER TABLE contents_tbl
    ADD CONSTRAINT content_use_ch CHECK ( content_use IN ( 'Y', 'N' ) );

ALTER TABLE contents_tbl ADD CONSTRAINT board_tblv1_pk PRIMARY KEY ( content_id );

CREATE TABLE facility_tbl (
    facility_id           NUMBER NOT NULL,
    facility_name         VARCHAR2(100) NOT NULL,
    member_id             VARCHAR2(20) NOT NULL,
    facility_phone        VARCHAR2(20),
    facility_content      CLOB,
    facility_image_path   VARCHAR2(200),
    facility_person_max   NUMBER,
    facility_person_min   NUMBER,
    facility_use          CHAR(1) DEFAULT 'Y',
    facility_reg_date     DATE DEFAULT SYSDATE NOT NULL,
    facility_mod_date     DATE,
    facility_open_time    DATE,
    facility_close_time   DATE
);

COMMENT ON COLUMN facility_tbl.facility_id IS
    '시설 고유 번호';

COMMENT ON COLUMN facility_tbl.facility_name IS
    '시설명';

COMMENT ON COLUMN facility_tbl.member_id IS
    '강사ID(관리자ID)';

COMMENT ON COLUMN facility_tbl.facility_phone IS
    '시설 연락처';

COMMENT ON COLUMN facility_tbl.facility_content IS
    '설명 HTML 내용';

COMMENT ON COLUMN facility_tbl.facility_image_path IS
    '이미지 경로';

COMMENT ON COLUMN facility_tbl.facility_person_max IS
    '최대인원';

COMMENT ON COLUMN facility_tbl.facility_person_min IS
    '최소인원';

COMMENT ON COLUMN facility_tbl.facility_use IS
    '사용 가능 여부';

COMMENT ON COLUMN facility_tbl.facility_reg_date IS
    '시설 등록일';

COMMENT ON COLUMN facility_tbl.facility_mod_date IS
    '시설 수정일';

COMMENT ON COLUMN facility_tbl.facility_open_time IS
    '운영 시작 시간';

COMMENT ON COLUMN facility_tbl.facility_close_time IS
    '운영 종료 시간';

ALTER TABLE facility_tbl
    ADD CONSTRAINT facility_use_ch CHECK ( facility_use IN ( 'Y', 'N' ) );

ALTER TABLE facility_tbl
    ADD CONSTRAINT facility_person_ch CHECK (facility_person_max >= facility_person_min );

ALTER TABLE facility_tbl ADD CONSTRAINT facility_tbl_pk PRIMARY KEY ( facility_id );

CREATE TABLE file_tbl (
    file_id              NUMBER NOT NULL,
    file_target_type     VARCHAR2(20) NOT NULL,
    file_target_id       VARCHAR2(20) NOT NULL,
    file_name            VARCHAR2(200) NOT NULL,
    file_path            VARCHAR2(500) NOT NULL,
    file_type            VARCHAR2(50) DEFAULT '본문' NOT NULL,
    file_ext             VARCHAR2(20),
    file_size            NUMBER,
    file_reg_date        DATE DEFAULT sysdate
);

COMMENT ON COLUMN file_tbl.file_id IS
    '파일 고유번호';

COMMENT ON COLUMN file_tbl.file_target_type IS
    '어느 테이블에 속한 파일인지(게시판, 콘텐츠, 시설 등등)';

COMMENT ON COLUMN file_tbl.file_target_id IS
    '어느 게시물/시설의 파일인지 (PK/FK는 아님, 문자열 ID)';

COMMENT ON COLUMN file_tbl.file_name IS
    '실제 업로드된 원본 파일명';

COMMENT ON COLUMN file_tbl.file_path IS
    '서버 저장 경로 (URL 또는 물리경로)';

COMMENT ON COLUMN file_tbl.file_type IS
    '파일 용도 (본문 이미지, 썸네일 등)';

COMMENT ON COLUMN file_tbl.file_ext IS
    '파일 확장자';

COMMENT ON COLUMN file_tbl.file_size IS
    '파일 크기 (byte 단위)';

COMMENT ON COLUMN file_tbl.file_reg_date IS
    '파일 등록일';

ALTER TABLE file_tbl
    ADD CONSTRAINT file_type_ch CHECK ( file_type IN ( '썸네일', '본문' ) );

ALTER TABLE file_tbl ADD CONSTRAINT member_tblv1_pk PRIMARY KEY ( file_id );


CREATE TABLE member_tbl (
    member_id       VARCHAR2(20) NOT NULL,
    member_pw       VARCHAR2(20) NOT NULL,
    member_name     VARCHAR2(100) NOT NULL,
    member_gender   CHAR(1) NOT NULL,
    member_email    VARCHAR2(50) NOT NULL,
    member_mobile   VARCHAR2(13) NOT NULL,
    member_phone    VARCHAR2(13),
    zip             CHAR(5),
    road_address    NVARCHAR2(50),
    jibun_address   NVARCHAR2(50),
    detail_address  NVARCHAR2(50),
    member_birthday DATE,
    member_joindate DATE DEFAULT sysdate NOT NULL,
    member_role     VARCHAR2(10),
    admin_type      VARCHAR2(20) DEFAULT '관리자'
);

COMMENT ON COLUMN member_tbl.member_pw IS
    '회원 비번';

COMMENT ON COLUMN member_tbl.member_name IS
    '사용자명';

COMMENT ON COLUMN member_tbl.member_gender IS
    '성별';

COMMENT ON COLUMN member_tbl.member_email IS
    '이메일';

COMMENT ON COLUMN member_tbl.member_mobile IS
    '휴대폰번호';

COMMENT ON COLUMN member_tbl.member_phone IS
    '그냥 전화 ';

COMMENT ON COLUMN member_tbl.zip IS
    '우편번호';

COMMENT ON COLUMN member_tbl.road_address IS
    '도로명주소 ';

COMMENT ON COLUMN member_tbl.jibun_address IS
    '지번 주소 ';

COMMENT ON COLUMN member_tbl.detail_address IS
    '상세주소 ';

COMMENT ON COLUMN member_tbl.member_birthday IS
    '생년월일 ';

COMMENT ON COLUMN member_tbl.member_joindate IS
    '가입일 ';

COMMENT ON COLUMN member_tbl.member_role IS
    '권한 ';

COMMENT ON COLUMN member_tbl.admin_type IS
    '관리자 종류(책임자, 관리자, 강사)';

ALTER TABLE member_tbl
    ADD CONSTRAINT member_gender_ch CHECK ( member_gender IN ( 'm', 'f' ) );

ALTER TABLE member_tbl
    ADD CONSTRAINT member_role_ch CHECK ( member_role IN ( 'user', 'admin' ) );

ALTER TABLE member_tbl
  ADD CONSTRAINT member_admin_type_ch
  CHECK (
    member_role != 'admin' OR admin_type IN ('책임자', '관리자', '강사')
  );

ALTER TABLE member_tbl ADD CONSTRAINT member_joindate_default PRIMARY KEY ( member_id );

--  이메일 DB값 중복 방지용 
ALTER TABLE member_tbl ADD CONSTRAINT member_email_un UNIQUE ( member_email );

--  휴대폰 중복값 방지용 
ALTER TABLE member_tbl ADD CONSTRAINT member_mobile_un UNIQUE ( member_mobile );

CREATE TABLE message_tbl (
    message_id      NUMBER NOT NULL,
    member_id       VARCHAR2(20) NOT NULL,
    resv_id         NUMBER NOT NULL,
    closed_id       NUMBER NOT NULL,
    message_type    VARCHAR2(20) NOT NULL,
    message_content CLOB,
    message_date    DATE DEFAULT sysdate NOT NULL
);

COMMENT ON COLUMN message_tbl.message_id IS
    '문자 고유 이력 ID';

COMMENT ON COLUMN message_tbl.member_id IS
    '문자 수신자 ID';

COMMENT ON COLUMN message_tbl.resv_id IS
    '관련 예약 ID';

COMMENT ON COLUMN message_tbl.closed_id IS
    '관련 휴관일 ID';

COMMENT ON COLUMN message_tbl.message_type IS
    '문자 분류 유형';

COMMENT ON COLUMN message_tbl.message_content IS
    '실제 발송된 문자 내역';

COMMENT ON COLUMN message_tbl.message_date IS
    '문자 발송 일시';

ALTER TABLE message_tbl
    ADD CONSTRAINT message_type_ch
        CHECK ( message_type IN('예약확인', '예약취소', '휴관공지') );

ALTER TABLE message_tbl ADD CONSTRAINT message_tbl_pk PRIMARY KEY ( message_id );

--  같은 시간에 같은 유형 문자 중복 방지
ALTER TABLE message_tbl
    ADD CONSTRAINT message_total_un UNIQUE ( member_id,
                                             message_type,
                                             message_date );

CREATE TABLE paylog_tbl (
    paylog_id            NUMBER NOT NULL,
    payment_id           NUMBER NOT NULL,
    member_id            VARCHAR2(20) NOT NULL,
    card_id              NUMBER NOT NULL,
    account_id           NUMBER NOT NULL,
    resv_id              NUMBER NOT NULL,
    paylog_type          VARCHAR2(20) DEFAULT '결제' NOT NULL,
    paylog_before_status VARCHAR2(20),
    paylog_after_status  VARCHAR2(20),
    paylog_money         NUMBER,
    paylog_method        VARCHAR2(20),
    paylog_manager       VARCHAR2(20),
    paylog_history       DATE DEFAULT sysdate NOT NULL,
    paylog_memo          VARCHAR2(1000)
);

COMMENT ON COLUMN paylog_tbl.paylog_id IS
    '결제 로그 고유번호(PK)';

COMMENT ON COLUMN paylog_tbl.payment_id IS
    '결제 PK(FK)';

COMMENT ON COLUMN paylog_tbl.member_id IS
    '결제자 회원 ID(FK)';

COMMENT ON COLUMN paylog_tbl.card_id IS
    '결제에 사용된 카드 ID (FK)';

COMMENT ON COLUMN paylog_tbl.account_id IS
    '결제에 사용된 카드 ID (FK)';

COMMENT ON COLUMN paylog_tbl.resv_id IS
    '예약 ID(예약과 연동 시)';

COMMENT ON COLUMN paylog_tbl.paylog_type IS
    '결제 동작 유형(결제/취소 등)';

COMMENT ON COLUMN paylog_tbl.paylog_before_status IS
    '변경 전 결제 상태';

COMMENT ON COLUMN paylog_tbl.paylog_after_status IS
    '변경 후 결제 상태';

COMMENT ON COLUMN paylog_tbl.paylog_money IS
    '결제 금액';

COMMENT ON COLUMN paylog_tbl.paylog_method IS
    '결제 방식(카드/계좌)';

COMMENT ON COLUMN paylog_tbl.paylog_manager IS
    '담당자(작업자 ID)';

COMMENT ON COLUMN paylog_tbl.paylog_history IS
    '결제 로그 기록 시각';

COMMENT ON COLUMN paylog_tbl.paylog_memo IS
    '기타사항 입력1(00자 이내)';

ALTER TABLE paylog_tbl
    ADD CONSTRAINT paylog_type_ch
        CHECK (paylog_type IN('결제', '취소', '환불', '실패', '수정',
                                     '삭제') );

ALTER TABLE paylog_tbl ADD CONSTRAINT paylog_tbl_pk PRIMARY KEY ( paylog_id );


CREATE TABLE payment_tbl (
    payment_id     NUMBER NOT NULL,
    member_id      VARCHAR2(20) NOT NULL,
    account_id     NUMBER NOT NULL,
    card_id        NUMBER NOT NULL,
    resv_id        NUMBER NOT NULL,
    payment_money  NUMBER NOT NULL,
    payment_method VARCHAR2(20) DEFAULT '계좌' NOT NULL,
    payment_status VARCHAR2(20) DEFAULT '예약중' NOT NULL,
    payment_date   DATE DEFAULT sysdate
);

COMMENT ON COLUMN payment_tbl.payment_id IS
    '결제 고유번호 (PK)';

COMMENT ON COLUMN payment_tbl.member_id IS
    '계좌 소유자 회원 ID (FK)';

COMMENT ON COLUMN payment_tbl.account_id IS
    '사용한 계좌 ID (FK)';

COMMENT ON COLUMN payment_tbl.card_id IS
    '사용한 카드 ID (FK)';

COMMENT ON COLUMN payment_tbl.resv_id IS
    '연결된 예약 ID (FK)';

COMMENT ON COLUMN payment_tbl.payment_money IS
    '결제금액';

COMMENT ON COLUMN payment_tbl.payment_method IS
    '결제 방식(계좌/카드 중 선택)';

COMMENT ON COLUMN payment_tbl.payment_status IS
    '결제 상태(''완료'', ''예약중'', ''취소'', ''실패'')';

COMMENT ON COLUMN payment_tbl.payment_date IS
    '결제일시';

ALTER TABLE payment_tbl 
    ADD CONSTRAINT payment_status_chk 
    CHECK ( payment_status IN ('완료', '예약중', '취소', '실패') );


ALTER TABLE payment_tbl 
    ADD CONSTRAINT payment_method_ch 
    CHECK (payment_method IN ('카드', '계좌', '현금'));
    
ALTER TABLE payment_tbl ADD CONSTRAINT card_tblv1_pk PRIMARY KEY ( payment_id );


--  예약 id 중복값 방지
ALTER TABLE payment_tbl ADD CONSTRAINT resv_id_un UNIQUE ( resv_id );

CREATE TABLE post_tbl (
    post_id         NUMBER NOT NULL,
    board_id        NUMBER NOT NULL,
    post_title      VARCHAR2(200) NOT NULL,
    post_content    CLOB NOT NULL,
    member_id       VARCHAR2(20) NOT NULL,
    post_reg_date   DATE DEFAULT sysdate NOT NULL,
    post_mod_date   DATE,
    post_view_count NUMBER DEFAULT 0,
    post_notice     CHAR(1) DEFAULT 'N',
    post_type       VARCHAR2(20) DEFAULT '일반' NOT NULL
);

COMMENT ON COLUMN post_tbl.post_id IS
    '게시글 고유번호(PK)';

COMMENT ON COLUMN post_tbl.board_id IS
    '게시판 ID(FK)';

COMMENT ON COLUMN post_tbl.post_title IS
    '게시글 제목';

COMMENT ON COLUMN post_tbl.post_content IS
    '게시글 내용(HTML 가능)';

COMMENT ON COLUMN post_tbl.member_id IS
    '작성자 id(member_tbl)';

COMMENT ON COLUMN post_tbl.post_reg_date IS
    '등록일(기본값 SYSDATE)';

COMMENT ON COLUMN post_tbl.post_mod_date IS
    '수정일(수정시 갱신)';

COMMENT ON COLUMN post_tbl.post_view_count IS
    '조회수(기본값 0)';

COMMENT ON COLUMN post_tbl.post_notice IS
    '공지글 여부(기본값 ''N'')';

COMMENT ON COLUMN post_tbl.post_type IS
    '게시글 유형(공지/일반 등 구분)';

ALTER TABLE post_tbl
    ADD CONSTRAINT post_notice_ch CHECK ( post_notice IN ( 'Y', 'N' ) );

ALTER TABLE post_tbl
    ADD CONSTRAINT post_type_ch CHECK ( post_type IN ( '공지', '일반' ) );

ALTER TABLE post_tbl ADD CONSTRAINT board_tblv1_pkv1 PRIMARY KEY ( post_id );

CREATE TABLE reservation_tbl (
    resv_id           NUMBER NOT NULL,
    member_id         VARCHAR2(20) NOT NULL,
    facility_id       NUMBER NOT NULL,
    resv_content      VARCHAR2(500),
    want_date         DATE NOT NULL,
    resv_date         DATE DEFAULT sysdate,
    resv_log_time     DATE DEFAULT sysdate,
    resv_person_count NUMBER,
    resv_status       VARCHAR2(20) DEFAULT '완료'
);

COMMENT ON COLUMN reservation_tbl.resv_id IS
    '예약 고유 번호 (결제 시스템과 연동)';

COMMENT ON COLUMN reservation_tbl.member_id IS
    '신청자 ID';

COMMENT ON COLUMN reservation_tbl.facility_id IS
    '예약 대상 시설';

COMMENT ON COLUMN reservation_tbl.resv_content IS
    '신청시 요구 사항(단순 텍스트)';

COMMENT ON COLUMN reservation_tbl.want_date IS
    '예약 희망일';

COMMENT ON COLUMN reservation_tbl.resv_date IS
    '예약 신청일 (프론트 화면에 나오는 용도)';

COMMENT ON COLUMN reservation_tbl.resv_log_time IS
    'resv_date의 확장판 - 사용자가 신청한 등록한 로그 추적용(log4-j2)';

COMMENT ON COLUMN reservation_tbl.resv_person_count IS
    '신청 인원 수';

COMMENT ON COLUMN reservation_tbl.resv_status IS
    '예약 상태';

ALTER TABLE reservation_tbl
    ADD CONSTRAINT resv_status_ch
        CHECK ( resv_status IN('완료', '취소', '대기') );

ALTER TABLE reservation_tbl ADD CONSTRAINT reservation_tbl_pk PRIMARY KEY ( resv_id );

ALTER TABLE account_tbl
    ADD CONSTRAINT account_member_fk FOREIGN KEY ( member_id )
        REFERENCES member_tbl ( member_id );

ALTER TABLE board_tbl
    ADD CONSTRAINT board_member_fk FOREIGN KEY ( member_id )
        REFERENCES member_tbl ( member_id );

ALTER TABLE card_tbl
    ADD CONSTRAINT card_member_fk FOREIGN KEY ( member_id )
        REFERENCES member_tbl ( member_id );

ALTER TABLE payment_tbl
    ADD CONSTRAINT card_member_fk FOREIGN KEY ( member_id )
        REFERENCES member_tbl ( member_id );

ALTER TABLE closed_day_tbl
    ADD CONSTRAINT closed_day_facility_fk FOREIGN KEY ( facility_id )
        REFERENCES facility_tbl ( facility_id );

ALTER TABLE contents_tbl
    ADD CONSTRAINT contents_member_fk FOREIGN KEY ( member_id )
        REFERENCES member_tbl ( member_id );

ALTER TABLE facility_tbl
    ADD CONSTRAINT facility_member_fk FOREIGN KEY ( member_id )
        REFERENCES member_tbl ( member_id );

ALTER TABLE message_tbl
    ADD CONSTRAINT message_closed_day_fk FOREIGN KEY ( closed_id )
        REFERENCES closed_day_tbl ( closed_id );

ALTER TABLE message_tbl
    ADD CONSTRAINT message_member_fk FOREIGN KEY ( member_id )
        REFERENCES member_tbl ( member_id );

ALTER TABLE message_tbl
    ADD CONSTRAINT message_reservation_fk FOREIGN KEY ( resv_id )
        REFERENCES reservation_tbl ( resv_id );

ALTER TABLE paylog_tbl
    ADD CONSTRAINT paylog_account_fk FOREIGN KEY ( account_id )
        REFERENCES account_tbl ( account_id );

ALTER TABLE paylog_tbl
    ADD CONSTRAINT paylog_card_fk FOREIGN KEY ( card_id )
        REFERENCES card_tbl ( card_id );

ALTER TABLE paylog_tbl
    ADD CONSTRAINT paylog_member_fk FOREIGN KEY ( member_id )
        REFERENCES member_tbl ( member_id );

ALTER TABLE paylog_tbl
    ADD CONSTRAINT paylog_payment_fk FOREIGN KEY ( payment_id )
        REFERENCES payment_tbl ( payment_id );

ALTER TABLE paylog_tbl
    ADD CONSTRAINT paylog_reservation_fk FOREIGN KEY ( resv_id )
        REFERENCES reservation_tbl ( resv_id );

ALTER TABLE payment_tbl
    ADD CONSTRAINT payment_account_fk FOREIGN KEY ( account_id )
        REFERENCES account_tbl ( account_id );

ALTER TABLE payment_tbl
    ADD CONSTRAINT payment_card_fk FOREIGN KEY ( card_id )
        REFERENCES card_tbl ( card_id );

ALTER TABLE payment_tbl
    ADD CONSTRAINT payment_reservation_fk FOREIGN KEY ( resv_id )
        REFERENCES reservation_tbl ( resv_id );

ALTER TABLE post_tbl
    ADD CONSTRAINT post_board_fk FOREIGN KEY ( board_id )
        REFERENCES board_tbl ( board_id );

ALTER TABLE post_tbl
    ADD CONSTRAINT post_member_fk FOREIGN KEY ( member_id )
        REFERENCES member_tbl ( member_id );

--  ERROR: FK name length exceeds maximum allowed length(30) 
ALTER TABLE reservation_tbl
    ADD CONSTRAINT reservation_facility_fk FOREIGN KEY ( facility_id )
        REFERENCES facility_tbl ( facility_id );

ALTER TABLE reservation_tbl
    ADD CONSTRAINT reservation_member_fk FOREIGN KEY ( member_id )
        REFERENCES member_tbl ( member_id );