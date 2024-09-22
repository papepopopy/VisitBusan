-- Visit Busan
-- MariaDB용 초기 데이터

-- 데이터베이스 삭제
-- DROP DATABASE visit_busan;

-- 데이터베이스 생성
-- CREATE DATABASE visit_busan;

-- 데이터베이스 선택
USE visit_busan;

-- 선택된 데이터베이스의 테이블 목록 표시
-- SHOW TABLES;


-- ------------------------------
--            멤버
-- ------------------------------

-- 비밀번호 1234

INSERT INTO member
(address, email, NAME, PASSWORD, user_id)
VALUES
('부산광역시 부산진구 신천대로50번길 79 부전빌딩 6층','root@test.com', 'root', '$2a$10$WnW8tFgaxGe.CHuv2UjrG.1HmWPkayetVCKkXLVRkplvWjr7T7R4O', 'root'),
('부산광역시 부산진구 신천대로50번길 79 부전빌딩 6층','admin@test.com', 'admin', '$2a$10$WnW8tFgaxGe.CHuv2UjrG.1HmWPkayetVCKkXLVRkplvWjr7T7R4O', 'admin'),
('부산광역시 부산진구 신천대로50번길 79 부전빌딩 6층','user@test.com', 'user', '$2a$10$WnW8tFgaxGe.CHuv2UjrG.1HmWPkayetVCKkXLVRkplvWjr7T7R4O', 'user');
SHOW COLUMNS FROM member;
SELECT * FROM member;


-- ------------------------------
--           멤버 권한
-- ------------------------------

-- 권한
-- user=0 admin=1 root=2

INSERT INTO member_role_set
(member_member_id,role_set)
VALUES
(1,2),
(1,1),
(2,1),
(3,0);
SHOW COLUMNS FROM member_role_set;
SELECT * FROM member_role_set;


-- ------------------------------
--            게시판
-- ------------------------------

-- 카테고리

-- 관리자 게시판 : 
--  - 여행정보게시판(travelInfo) : 명소(place), 음식(food), 숙박(accommodation)
--  - 여행추천게시판(travelRecommend) : 일정여행(), 테마여행()
--  - 여행가이드게시판(travelGuide) : 가이드(guide), 여행준비(preparation)
--  - 축제게시판(festivalBoard) : 축제행사(festival), 공연전시(performance)

-- 유저 게시판 :
--  - 유저게시판(userBoard) : 여행정보(information), 여행일정(schedule), 후기(review)

INSERT INTO board
(category,title,content,writer,writer_id,reg_date,mod_date)
VALUES
('festival, all1','title1','content1','admin','admin',SYSDATE(),SYSDATE()),
('festival, all1','title2','content2','admin','admin',SYSDATE(),SYSDATE()),
('performance, all1','title3','content3','admin','admin',SYSDATE(),SYSDATE()),
('performance, all1','title4','content4','admin','admin',SYSDATE(),SYSDATE()),
('place','title5','content5','admin','admin',SYSDATE(),SYSDATE()),
('place','title6','content6','admin','admin',SYSDATE(),SYSDATE()),
('food','title7','content7','admin','admin',SYSDATE(),SYSDATE()),
('food','title8','content8','admin','admin',SYSDATE(),SYSDATE()),
('accommodation','title9','content9','admin','admin',SYSDATE(),SYSDATE()),
('accommodation','title10','content10','admin','admin',SYSDATE(),SYSDATE()),
('guide','title11','content11','admin','admin',SYSDATE(),SYSDATE()),
('guide','title12','content12','admin','admin',SYSDATE(),SYSDATE()),
('preparation','title13','content13','admin','admin',SYSDATE(),SYSDATE()),
('preparation','title14','content14','admin','admin',SYSDATE(),SYSDATE()),
('information','title15','content15','user','user',SYSDATE(),SYSDATE()),
('information','title16','content16','user','user',SYSDATE(),SYSDATE()),
('schedule','title17','content17','user','user',SYSDATE(),SYSDATE()),
('schedule','title18','content18','user','user',SYSDATE(),SYSDATE()),
('review','title19','content19','user','user',SYSDATE(),SYSDATE()),
('review','title20','content20','user','user',SYSDATE(),SYSDATE()),
('sports, all1','title21','content21','user','user',SYSDATE(),SYSDATE()),
('sports, all1','title22','content22','user','user',SYSDATE(),SYSDATE());
SHOW COLUMNS FROM board;
SELECT * FROM board;

INSERT INTO board
(category,title,content,writer,writer_id,reg_date,mod_date)
VALUES
('review','부산 시티투어버스 환승 그린 오렌지라인 송도해수욕장과 암남공원','부산여행! 뚜벅이 아니더라도 하루쯤은 시티투어버스에 몸을 맡기고 운전, 주차따위 걱정없이 길맥도 가능한 ㅋㅋㅋ 코스를 추천드립니다. 너무 좋았기 때문에 다음 여행에도 기차여행을 예약해놓은 상태!! 매번 남편, 아빠만 고생하는 것 같지만 부산 시티투어버스 타면 아빠도 매우 즐거움! 부산여행! 뚜벅이 아니더라도 하루쯤은 시티투어버스에 몸을 맡기고 운전, 주차따위 걱정없이 길맥도 가능한 ㅋㅋㅋ 코스를 추천드립니다. 너무 좋았기 때문에 다음 여행에도 기차여행을 예약해놓은 상태!! 매번 남편, 아빠만 고생하는 것 같지만 부산 시티투어버스 타면 아빠도 매우 즐거움! 부산여행! 뚜벅이 아니더라도 하루쯤은 시티투어버스에 몸을 맡기고 운전, 주차따위 걱정없이 길맥도 가능한 ㅋㅋㅋ 코스를 추천드립니다. 너무 좋았기 때문에 다음 여행에도 기차여행을 예약해놓은 상태!! 매번 남편, 아빠만 고생하는 것 같지만 부산 시티투어버스 타면 아빠도 매우 즐거움!','test','test',SYSDATE(),SYSDATE());


-- ------------------------------
--        축제 게시글 정보
-- ------------------------------

INSERT INTO festival_info
(board_id, start_date, end_date, contact_num, HOST, place, supervision, homepage)
VALUES
(1, '2024-07-25', '2024-10-15', '070-4705-2008', '㈜블렌트, 부산일보사', 'APEC 나루공원 일대', '어반스포츠', 'https://urbansports.kr/shop_view/?idx=77' ),
(2, '2024-06-25', '2024-09-15', '070-4705-2008', '㈜블렌트, 부산일보사', 'APEC 나루공원 일대', '어반스포츠', 'https://urbansports.kr/shop_view/?idx=77' ),
(3, '2024-05-25', '2024-08-15', '070-4705-2008', '㈜블렌트, 부산일보사', 'APEC 나루공원 일대', '어반스포츠', 'https://urbansports.kr/shop_view/?idx=77' ),
(4, '2024-04-25', '2024-07-15', '070-4705-2008', '㈜블렌트, 부산일보사', 'APEC 나루공원 일대', '어반스포츠', 'https://urbansports.kr/shop_view/?idx=77' ),
(21, '2024-03-25', '2024-06-15', '070-4705-2008', '㈜블렌트, 부산일보사', 'APEC 나루공원 일대', '어반스포츠', 'https://urbansports.kr/shop_view/?idx=77' ),
(22, '2024-02-25', '2024-05-15', '070-4705-2008', '㈜블렌트, 부산일보사', 'APEC 나루공원 일대', '어반스포츠', 'https://urbansports.kr/shop_view/?idx=77' );
SHOW COLUMNS FROM festival_info;
SELECT * FROM festival_info;


-- ------------------------------
--            댓글
-- ------------------------------

INSERT INTO reply
(board_id,reply_text,replier,replier_id,reg_date,mod_date)
VALUES
(1,'reply_text1','user','user',SYSDATE(),SYSDATE()),
(3,'reply_text2','user','user',SYSDATE(),SYSDATE()),
(5,'reply_text3','user','user',SYSDATE(),SYSDATE()),
(7,'reply_text4','user','user',SYSDATE(),SYSDATE()),
(9,'reply_text5','user','user',SYSDATE(),SYSDATE()),
(11,'reply_text6','user','user',SYSDATE(),SYSDATE()),
(13,'reply_text7','user','user',SYSDATE(),SYSDATE()),
(15,'reply_text8','user','user',SYSDATE(),SYSDATE()),
(17,'reply_text9','user','user',SYSDATE(),SYSDATE()),
(19,'reply_text10','user','user',SYSDATE(),SYSDATE());
SHOW COLUMNS FROM reply;
SELECT * FROM reply;

INSERT INTO reply(board_id,reply_text,replier,replier_id,reg_date,mod_date)VALUES(11,'reply_text1','user','user',SYSDATE(),SYSDATE());


-- ------------------------------
--         게시글 좋아요
-- ------------------------------

INSERT INTO board_like
(board_id,user_id,reg_date)
VALUES
(1,'user',SYSDATE()),
(3,'user',SYSDATE()),
(5,'user',SYSDATE()),
(7,'user',SYSDATE()),
(9,'user',SYSDATE()),
(1,'user2',SYSDATE()),
(3,'user2',SYSDATE()),
(5,'user2',SYSDATE()),
(7,'user2',SYSDATE()),
(9,'user2',SYSDATE());
SHOW COLUMNS FROM board_like;
SELECT * FROM board_like;

INSERT INTO board_like(board_id,user_id,reg_date)VALUES(11,'user',SYSDATE());


