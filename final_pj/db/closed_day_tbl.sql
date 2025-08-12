-- 휴무일 설정
CREATE TABLE closed_day_tbl (
    closed_id   NUMBER NOT NULL,
    facility_id NUMBER NOT NULL,
    closed_date DATE NOT NULL,
    reason      VARCHAR2(200)
);

COMMENT ON TABLE closed_day_tbl                 IS '휴무일 설정 테이블';
COMMENT ON COLUMN closed_day_tbl.closed_id      IS '휴무일 고유 번호';
COMMENT ON COLUMN closed_day_tbl.facility_id    IS '대상 시설 ID';
COMMENT ON COLUMN closed_day_tbl.closed_date    IS '휴무일 날짜';
COMMENT ON COLUMN closed_day_tbl.reason         IS '휴무 사유';


-- PK 설정
ALTER TABLE closed_day_tbl ADD CONSTRAINT closed_day_tbl_pk PRIMARY KEY ( closed_id );

-- FK 설정
-- 시설 정보 테이블 연결
ALTER TABLE closed_day_tbl
ADD CONSTRAINT closed_day_facility_fk
FOREIGN KEY (facility_id)
REFERENCES facility_tbl(facility_id);


--  동일 시설 중복 방지 제약
ALTER TABLE closed_day_tbl
ADD CONSTRAINT closed_day_total_un
UNIQUE ( facility_id, closed_date );
