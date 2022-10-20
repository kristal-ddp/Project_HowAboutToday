-- create user '아이디'@'%' identified by '비밀번호';	-- 아이디/비밀번호 생성

-- grant select, insert, update on DB명.* to '아이디'@'%';	-- 특정 DB에 대한 특정권한(update나 insert등) 주기
-- grant all privileges on *.* to '아이디'@'localhost';		-- 모든 DB에 모든 권한주기
-- grant all privileges on DB명.* to '아이디'@'localhost';	-- 특정 DB에 대한 모든 권한주기

-- FLUSH PRIVILEGES; -- 최종적으로 권한을 적용시킴

-- DROP USER [user명]@[server명]; -- 유저 삭제
-- ex) drop user gillog@localhost;



-- 유저 생성과 DB 생성, 권한 주기는 무조건 ROOT 계정에서 실행해야함!!!!!!!
create user 'phoenix'@'%' identified by 'a123';
CREATE DATABASE how_about_today_db default CHARACTER SET UTF8MB4;
grant all privileges on how_about_today_db.* to 'phoenix'@'%';	-- 특정 DB에 대한 모든 권한주기
FLUSH PRIVILEGES; -- 모든 권한 적용
-- DROP USER  phoenix@'%'; -- 유저 삭제

--  멤버
CREATE TABLE MEMBER (
  memberNum int auto_increment,
  email varchar(40) NOT NULL,
  pw varchar(20) NOT NULL,
  nickName varchar(50) NOT NULL,
  memberTel varchar(20) NOT NULL,
--  memberPoint int NOT NULL, -- 포인트 일단 빼고
  memberGrade int NOT NULL, --  회원 등급: 일반회원, 판매자, 관리자
  PRIMARY KEY (`memberNum`)
)AUTO_INCREMENT=1;

--  쿠폰
CREATE TABLE COUPON (
  couponNum int auto_increment,
  couponName varchar(50) NOT NULL,
  discountPrice int NOT NULL, --  고정 할인 금액
  couponStartDate datetime NOT NULL,
  couponEndDate datetime NOT NULL,
  useTerms int NOT NULL,  --  사용 조건(ex. 40,000원 이상)
  PRIMARY KEY (`couponNum`)
)AUTO_INCREMENT=1;

--  멤버와 쿠폰간의 N:M 관계를 위한 테이블
CREATE TABLE MEMBER_VIEW_COUPON (
  memberNum int,
  couponNum int,
  CONSTRAINT FK_23 FOREIGN KEY (memberNum) REFERENCES MEMBER(memberNum)on delete cascade,
  CONSTRAINT FK_24 FOREIGN KEY (couponNum) REFERENCES COUPON(couponNum)on delete cascade
);

--  지역
CREATE TABLE REGION (
  regionNum int auto_increment,
  regionName varchar(20) NOT NULL,
  regionParentNum int NOT NULL,
  PRIMARY KEY (`regionNum`)
)AUTO_INCREMENT=1;

--  숙소 카테고리
CREATE TABLE ACCOM_CATEGORY (
  accomCategoryNum int auto_increment,
  accomCategoryName varchar(50) NOT NULL,
  PRIMARY KEY (`accomCategoryNum`)
)AUTO_INCREMENT=1;

--  숙소
CREATE TABLE ACCOMMODATION (
  accomNum int auto_increment,
  accomName varchar(50) NOT NULL,
  accomTel varchar(50) NOT NULL,
  accomCategoryNum int NOT NULL,   -- 카테고리 (모텔, 호텔, 펜션)
  regionNum int NOT NULL,       -- 지역
  accomAddress varchar(200) NOT NULL,
  accomRating DECIMAL(2,1) NOT NULL,
  accomWishListCount int NOT null,
  totalreviewNum int NOT NULL,   -- 숙소의 총 리뷰 수
  latitude decimal(16,14) NOT NULL,	-- 위도
  longitude decimal(17,14) NOT NULL,	-- 경도
  lowPrice int NOT NULL,         -- 숙소의 모든 객실중에서 가장 낮은 가격
  reserveRange int NOT NULL,      -- 예약 가능 범위(예를 들어서 60으로 설정해두면 오늘로부터 +60일까지만 예약이 가능합니다.)
  PRIMARY KEY (`accomNum`),
  CONSTRAINT FK_9 FOREIGN KEY (regionNum) REFERENCES REGION(regionNum)on delete cascade,
  CONSTRAINT FK_25 FOREIGN KEY (accomCategoryNum) REFERENCES ACCOM_CATEGORY (accomCategoryNum)on delete cascade
)AUTO_INCREMENT=1;

--  숙소 이미지
CREATE TABLE ACCOM_IMAGE (
  accomNum int,
  accomOriginFileName varchar(100) NOT NULL,
  accomSaveFileName varchar(100) NOT NULL,
  CONSTRAINT FK_43 FOREIGN KEY (accomNum) REFERENCES ACCOMMODATION(accomNum)on delete cascade
);

--  객실
CREATE TABLE ROOM (
  accomNum int,
  roomNum int auto_increment,
  roomName varchar(50) NOT NULL,
  defaultGuest int NOT NULL,
  maxGuest int NOT NULL,
  price int NOT NULL,
  restStartTime varchar(20) NOT NULL,
  restEndTime varchar(20) NOT NULL,
  roomInfo varchar(1000) NOT NULL,
  PRIMARY KEY (`roomNum`),
  CONSTRAINT FK_26 FOREIGN KEY (accomNum) REFERENCES ACCOMMODATION(accomNum)on delete cascade
)AUTO_INCREMENT=1;

-- 원래는 객실 가격을 테이블로 나눴는데 굳이 필요가 없다는 판단이 들어서 객실테이블로 합쳤습니다.

--  객실 정보
CREATE TABLE ROOM_RESERVATION_INFO (
  accomNum int,
  roomNum int,
  useStart date NOT NULL,
  useEnd date NOT NULL,
  CONSTRAINT FK_1 FOREIGN KEY (accomNum) REFERENCES ACCOMMODATION (accomNum)on delete cascade,
  CONSTRAINT FK_2 FOREIGN KEY (roomNum) REFERENCES ROOM (roomNum)on delete cascade
);
-- 이 테이블은 날짜를 선택했을 때 해당 숙소의 객실이 예약이 가능한지를 판단합니다.
-- 예를 들어 유저A가 숙소A의 객실X를 10월 1일을 예약했다면 이 INFO에 숙소번호와 객실번호와 날짜가 저장됩니다.
-- 그리고 유저 B가 숙소A의 페이지에서 10월 1일을 선택하면 INFO의 정보를 읽어와서 10월 1일 객실 X는 예약 마감으로 보입니다.


--  객실 이미지
CREATE TABLE ROOM_IMAGE (
  roomNum int,
  roomOriginFileName varchar(100) NOT NULL,
  roomSaveFileName varchar(100) NOT NULL,
  CONSTRAINT FK_18 FOREIGN KEY (roomNum) REFERENCES ROOM(roomNum)on delete cascade
);


--  장바구니 숙소
-- DROP TABLE CART_ACCO;
-- CREATE TABLE CART_ACCO (
--   memberNum int,
--   accommodationNum int,
--   CONSTRAINT FK_10 FOREIGN KEY CART_ACCO(memberNum) REFERENCES MEMBER(memberNum),
--   CONSTRAINT FK_11 FOREIGN KEY CART_ACCO(accommodationNum) REFERENCES ACCOMMODATION(accommodationNum)
-- );

--  장바구니
CREATE TABLE CART (
  memberNum int,
  accomNum int,
  roomNum int,
  useStart date NOT NULL,
  useEnd date NOT NULL,
  price int NOT NULL,
  CONSTRAINT FK_11 FOREIGN KEY (memberNum) REFERENCES MEMBER(memberNum)on delete cascade,
  CONSTRAINT FK_12 FOREIGN KEY (accomNum) REFERENCES ACCOMMODATION(accomNum)on delete cascade,
  CONSTRAINT FK_13 FOREIGN KEY (roomNum) REFERENCES ROOM(roomNum)on delete cascade
);

--  편의 시설
CREATE TABLE FACILITIES (
  facilityNum int auto_increment,
  facilityName varchar(50) NOT NULL,
  PRIMARY KEY (`facilityNum`)
)AUTO_INCREMENT=1;

--  찜하기
CREATE TABLE WISHLIST (
  memberNum int,
  accomNum int,
  CONSTRAINT FK_14 FOREIGN KEY (memberNum) REFERENCES MEMBER(memberNum)on delete cascade,
  CONSTRAINT FK_15 FOREIGN KEY (accomNum) REFERENCES ACCOMMODATION(accomNum)on delete cascade
);

--  주문
-- DROP TABLE ORDER;
-- CREATE TABLE ORDER (
--   memberNum int,
--   orderNum int PRIMARY KEY,
--   orderDate datetime NOT NULL,
--   CONSTRAINT FK_16 FOREIGN KEY ORDER(memberNum) REFERENCES MEMBER(memberNum)
-- );

-- 예약 상태
CREATE TABLE RESERVATION_STATE (
  reserveStatus int auto_increment,
  orderStatus varchar(20),
  PRIMARY KEY (`reserveStatus`)
)AUTO_INCREMENT=1;
-- 상태는 아마 3개정도? 뿐이겠지만 이렇게 나눠 놓는 게 추후에 상태가 추가 되면 더 구현에 좋을 것 같아서 테이블로 만들었습니다.

--  예약
CREATE TABLE RESERVATION (
  memberNum int,
  accomNum int,
  roomNum int,
  reserveStatus int,
  orderNum int auto_increment,
  orderDate datetime NOT NULL,
  useStartDate datetime NOT NULL,
  useEndDate datetime NOT NULL,
  price int NOT NULL,
  PRIMARY KEY (`orderNum`),
  CONSTRAINT FK_16 FOREIGN KEY (memberNum) REFERENCES MEMBER(memberNum)on delete cascade,
  CONSTRAINT FK_17 FOREIGN KEY (accomNum) REFERENCES ACCOMMODATION(accomNum)on delete cascade,
  CONSTRAINT FK_46 FOREIGN KEY (roomNum) REFERENCES ROOM(roomNum)on delete cascade,
  CONSTRAINT FK_5 FOREIGN KEY (reserveStatus) REFERENCES RESERVATION_STATE(reserveStatus)on delete cascade
)AUTO_INCREMENT=1;

--  숙소와 편의시설간의 N:M 관계를 위한 테이블
CREATE TABLE ACCOM_VIEW_FACILITIES (
  accomNum int,
  facilities int,
  CONSTRAINT FK_21 FOREIGN KEY (accomNum) REFERENCES ACCOMMODATION(accomNum)on delete cascade,
  CONSTRAINT FK_22 FOREIGN KEY (facilities) REFERENCES FACILITIES(facilityNum)on delete cascade
);

--  리뷰
CREATE TABLE REVIEW (
  memberNum int,
  accomNum int,
  roomNum int,
  orderNum int,
  reviewNum int auto_increment,
  reviewRating DECIMAL(2,1) NOT NULL,      -- 평점
  reviewCreated datetime NOT NULL,
  reviewContent varchar(1000) NOT NULL,    -- 내용.
  PRIMARY KEY (`reviewNum`),
  CONSTRAINT FK_4 FOREIGN KEY (orderNum) REFERENCES RESERVATION(orderNum)on delete cascade,
  CONSTRAINT FK_6 FOREIGN KEY (memberNum) REFERENCES MEMBER(memberNum)on delete cascade,
  CONSTRAINT FK_7 FOREIGN KEY (accomNum) REFERENCES ACCOMMODATION(accomNum)on delete cascade,
  CONSTRAINT FK_8 FOREIGN KEY (roomNum) REFERENCES ROOM(roomNum)on delete cascade
)AUTO_INCREMENT=1;

--  리뷰이미지
CREATE TABLE REVIEW_IMAGE (
  reviewNum int,
  reviewOriginFileName varchar(100) NOT NULL,
  reviewSaveFileName varchar(100) NOT NULL,
  CONSTRAINT FK_20 FOREIGN KEY (reviewNum) REFERENCES REVIEW(reviewNum) on delete cascade
);

--  리뷰 답글
CREATE TABLE REPLY (
  memberNum int,    -- 판매자
  accomNum int,
  roomNum int,
  orderNum int,
  reviewNum int,
  replyNum int auto_increment,
  replyCreate datetime NOT NULL,
  replyContent varchar(1000) NOT NULL,
  PRIMARY KEY (`replyNum`),
  CONSTRAINT FK_36 FOREIGN KEY (memberNum) REFERENCES MEMBER(memberNum)on delete cascade,
  CONSTRAINT FK_37 FOREIGN KEY (accomNum) REFERENCES ACCOMMODATION(accomNum)on delete cascade,
  CONSTRAINT FK_38 FOREIGN KEY (roomNum) REFERENCES ROOM(roomNum)on delete cascade,
  CONSTRAINT FK_44 FOREIGN KEY (orderNum) REFERENCES RESERVATION(orderNum)on delete cascade,
  CONSTRAINT FK_34 FOREIGN KEY (reviewNum) REFERENCES REVIEW(reviewNum)on delete cascade
)AUTO_INCREMENT=1;

-- (Notice, FAQ, About Us) 게시판
CREATE TABLE BOARD (
	boardNum int auto_increment, -- 게시글 번호
	boardCategoryNum int NOT NULL, -- 게시글 카테고리 번호
	boardTitle varchar(200) NOT NULL, -- 게시글 제목
	boardContent varchar(3096) NOT NULL, -- 게시글 내용
	boardCreate datetime NOT NULL, -- 게시일
	PRIMARY KEY (`boardNum`),
CONSTRAINT FK_27 FOREIGN KEY (boardCategoryNum) REFERENCES BOARD_CATEGORY(boardCategoryNum) on delete cascade
)AUTO_INCREMENT=1;

-- (Notice, FAQ, About Us) 게시판 카테고리
CREATE TABLE BOARD_CATEGORY(
	boardCategoryNum int auto_increment, -- 게시글 카테고리 번호
	boardCategoryName varchar(20) NOT NULL, -- 게시글 카테고리 이름
	boardParentNum int NOT NULL, -- 게시글 카테고리 상위 번호
	PRIMARY KEY (`boardCategoryNum`)
)AUTO_INCREMENT=1;

-- Event 게시판
CREATE TABLE EVENT (
	eventNum int auto_increment, -- 이벤트 게시글 번호
	eventTitle varchar(200) NOT NULL, -- 이벤트 게시글 제목
	eventCreate datetime NOT NULL, -- 이벤트 게시일
	eventStart datetime NOT NULL, -- 이벤트 시작일
	eventEnd datetime NOT NULL, -- 이벤트 종료일
	PRIMARY KEY (`eventNum`)
)AUTO_INCREMENT=1;

-- Event 게시판 이미지
CREATE TABLE EVENT_IMAGE (
    eventImageNum int auto_increment, -- 이벤트 이미지 번호
    eventNum int, -- 이벤트 게시글 번호
    eventOriginFileName varchar(100) NOT NULL, -- 기존 파일 이름
    eventSaveFileName varchar(100) NOT NULL, -- 저장 파일 이름
    PRIMARY KEY (`eventImageNum`),
    CONSTRAINT FK_53 FOREIGN KEY (eventNum) REFERENCES EVENT(eventNum) on delete cascade
)AUTO_INCREMENT=1;

--  결제
CREATE TABLE PAYMENT (
    pay_num int auto_increment, -- pk
    pay_tel varchar(50) NOT NULL,   -- 전화번호(결제 시 따로 적으면 그걸로
    pay_name varchar(50) NOT NULL,
    member_num int NOT NULL,    -- fk
    accom_num int NOT NULL, -- fk
    room_num int NOT NULL, -- fk
    orderDate datetime NOT NULL,
    useStartDate datetime NOT NULL,
    useEndDate datetime NOT NULL,
    amountPrice  int NOT NULL,
    paymentType varchar(50) NOT NULL,
    CONSTRAINT FK_61 FOREIGN KEY (member_num) REFERENCES MEMBER(member_num)on delete cascade,
    CONSTRAINT FK_62 FOREIGN KEY (accom_num) REFERENCES ACCOMMODATION(accom_num)on delete cascade,
    CONSTRAINT FK_63 FOREIGN KEY (room_num) REFERENCES ROOM(room_num)on delete cascade,
)AUTO_INCREMENT=1;


DROP TABLE MEMBER;
DROP TABLE COUPON;
DROP TABLE MEMBER_VIEW_COUPON;
DROP TABLE REGION;
DROP TABLE ACCOM_CATEGORY;
DROP TABLE ACCOMMODATION;
DROP TABLE ACCOM_IMAGE;
DROP TABLE ROOM;
DROP TABLE ROOM_RESERVATION_INFO;
DROP TABLE ROOM_IMAGE;

DROP TABLE CART;
DROP TABLE FACILITIES;
DROP TABLE WISHLIST;
DROP TABLE RESERVATION_STATE;
DROP TABLE RESERVATION;
DROP TABLE ACCOM_VIEW_FACILITIES;
DROP TABLE REVIEW;
DROP TABLE REPLY;
DROP TABLE REVIEW_IMAGE;
DROP TABLE BOARD;
DROP TABLE BOARD_CATEGORY;
DROP TABLE EVENT;
DROP TABLE BOARD_IMAGE;

