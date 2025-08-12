-- 신청(예약) 정보 테이블
CREATE TABLE reservation_tbl (
    resv_id           NUMBER NOT NULL,
    member_id         VARCHAR2(20) NOT NULL,
    facility_id       NUMBER NOT NULL,
    resv_content      VARCHAR2(500),
    want_date         DATE NOT NULL,
    resv_date         DATE NOT NULL DEFAULT sysdate,
    resv_log_time     DATE NOT NULL DEFAULT sysdate,
    resv_person_count NUMBER,
    resv_status       VARCHAR2(20) NOT NULL DEFAULT '완료'
);


-- PK 설정
ALTER TABLE reservation_tbl ADD CONSTRAINT pk_resv_id PRIMARY KEY ( resv_id );



-- FK 설정
-- 회원 정보 테이블 연결
ALTER TABLE reservation_tbl
ADD CONSTRAINT reservation_member_fk
FOREIGN KEY (member_id)
REFERENCES member_tbl(member_id)

-- 시설 정보 테이블 연결
ALTER TABLE reservation_tbl
ADD CONSTRAINT reservation_facility_fk
FOREIGN KEY (facility_id)
REFERENCES facility_tbl(facility_id)


-- 예약 상태 제약
ALTER TABLE reservation_tbl
ADD CONSTRAINT resv_status_ch
CHECK ( resv_status IN('완료', '취소', '대기') );

-- 신청 인원수 제약 (추가) : 한 명 이상으로 제약
ALTER TABLE reservation_tbl
ADD CONSTRAINT resv_person_count_chk
CHECK (resv_person_count > 0);


-- 주석 설정
COMMENT ON TABLE  reservation_tbl                   IS '신청(예약) 정보 테이블';
COMMENT ON COLUMN reservation_tbl.resv_id           IS '예약 고유 번호 (결제 시스템과 연동)';
COMMENT ON COLUMN reservation_tbl.member_id         IS '신청자 ID';
COMMENT ON COLUMN reservation_tbl.facility_id       IS '예약 대상 시설';
COMMENT ON COLUMN reservation_tbl.resv_content      IS '신청시 요구 사항(단순 텍스트)';
COMMENT ON COLUMN reservation_tbl.want_date         IS '예약 희망일';
COMMENT ON COLUMN reservation_tbl.resv_date         IS '예약 신청일 (프론트 화면에 나오는 용도)';
COMMENT ON COLUMN reservation_tbl.resv_log_time     IS 'resv_date의 확장판 - 사용자가 신청한 등록한 로그 추적용(log4-j2)';
COMMENT ON COLUMN reservation_tbl.resv_person_count IS '신청 인원 수';
COMMENT ON COLUMN reservation_tbl.resv_status       IS '예약 상태';