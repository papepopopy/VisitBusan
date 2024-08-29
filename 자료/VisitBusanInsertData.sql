-- Visit Busan
-- 초기 데이터

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

INSERT INTO member
(address, email, NAME, PASSWORD, user_id)
VALUES
('root','root@test.com', 'root', '1234', 'root'),
('admin','admin@test.com', 'admin', '1234', 'admin'),
('user','user@test.com', 'user', '1234', 'user'),
('test','test@test.com', 'test', '1234', 'test');
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
-- 관리자 게시판 : 축제행사(Festival), 공연전시(Performance), 명소(Place), 음식(Food), 숙박(Accommodation), 가이드(Guide), 여행준비(Preparation)
-- 유저 게시판 :  여행정보(Infomation), 여행일정(Schedule), 후기(Review)

INSERT INTO board
(category,title,content,writer,writer_id,tags,reg_date,mod_date)
VALUES
('Festival','title1','content1','admin','admin','good',SYSDATE(),SYSDATE()),
('Festival','title2','content2','admin','admin','bad',SYSDATE(),SYSDATE()),
('Performance','title3','content3','admin','admin','good',SYSDATE(),SYSDATE()),
('Performance','title4','content4','admin','admin','bad',SYSDATE(),SYSDATE()),
('Place','title5','content5','admin','admin','good',SYSDATE(),SYSDATE()),
('Place','title6','content6','admin','admin','bad',SYSDATE(),SYSDATE()),
('Food','title7','content7','admin','admin','good',SYSDATE(),SYSDATE()),
('Food','title8','content8','admin','admin','bad',SYSDATE(),SYSDATE()),
('Accommodation','title9','content9','admin','admin','good',SYSDATE(),SYSDATE()),
('Accommodation','title10','content10','admin','admin','good',SYSDATE(),SYSDATE()),
('Guide','title11','content11','admin','admin','bad',SYSDATE(),SYSDATE()),
('Guide','title12','content12','admin','admin','good',SYSDATE(),SYSDATE()),
('Preparation','title13','content13','admin','admin','bad',SYSDATE(),SYSDATE()),
('Preparation','title14','content14','admin','admin','good',SYSDATE(),SYSDATE()),
('Infomation','title15','content15','user','user','bad',SYSDATE(),SYSDATE()),
('Infomation','title16','content16','user','user','good',SYSDATE(),SYSDATE()),
('Schedule','title17','content17','user','user','good',SYSDATE(),SYSDATE()),
('Schedule','title18','content18','user','user','good',SYSDATE(),SYSDATE()),
('Review','title19','content19','user','user','good',SYSDATE(),SYSDATE()),
('Review','title20','content20','user','user','good',SYSDATE(),SYSDATE());
SHOW COLUMNS FROM board;
SELECT * FROM board;


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


