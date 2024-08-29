-- Visit Busan
-- 초기 데이터

-- 데이터베이스 생성
-- CREATE DATABASE visit_busan;

-- 데이터베이스 선택
USE visit_busan;

-- ------------------------------
--            멤버
-- ------------------------------

INSERT INTO member
(address, email, NAME, PASSWORD, user_id)
VALUES
('root','root@test.com', 'root', '1234', 'root'),
('admin','admin@test.com', 'admin', '1234', 'admin'),
('test','test@test.com', 'test', '1234', 'testid');
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
('Festival','title1','content1','writer1','writer_id1','good',SYSDATE(),SYSDATE()),
('Festival','title2','content2','writer2','writer_id2','bad',SYSDATE(),SYSDATE()),
('Performance','title3','content3','writer3','writer_id3','good',SYSDATE(),SYSDATE()),
('Performance','title4','content4','writer4','writer_id4','bad',SYSDATE(),SYSDATE()),
('Place','title5','content5','writer5','writer_id5','good',SYSDATE(),SYSDATE()),
('Place','title6','content6','writer6','writer_id6','bad',SYSDATE(),SYSDATE()),
('Food','title7','content7','writer7','writer_id7','good',SYSDATE(),SYSDATE()),
('Food','title8','content8','writer8','writer_id8','bad',SYSDATE(),SYSDATE()),
('Accommodation','title9','content9','writer9','writer_id9','good',SYSDATE(),SYSDATE()),
('Accommodation','title10','content10','writer10','writer_id10','good',SYSDATE(),SYSDATE()),
('Guide','title11','content11','writer11','writer_id11','bad',SYSDATE(),SYSDATE()),
('Guide','title12','content12','writer12','writer_id12','good',SYSDATE(),SYSDATE()),
('Preparation','title13','content13','writer13','writer_id13','bad',SYSDATE(),SYSDATE()),
('Preparation','title14','content14','writer14','writer_id14','good',SYSDATE(),SYSDATE()),
('Infomation','title15','content15','writer15','writer_id15','bad',SYSDATE(),SYSDATE()),
('Infomation','title16','content16','writer16','writer_id16','good',SYSDATE(),SYSDATE()),
('Schedule','title17','content17','writer17','writer_id17','good',SYSDATE(),SYSDATE()),
('Schedule','title18','content18','writer18','writer_id18','good',SYSDATE(),SYSDATE()),
('Review','title19','content19','writer19','writer_id19','good',SYSDATE(),SYSDATE()),
('Review','title20','content20','writer20','writer_id20','good',SYSDATE(),SYSDATE());
SHOW COLUMNS FROM board;
SELECT * FROM board;


-- ------------------------------
--            댓글
-- ------------------------------

INSERT INTO reply
(board_id,reply_text,replier,replier_id,reg_date,mod_date)
VALUES
(1,'reply_text1','test1','testid1',SYSDATE(),SYSDATE()),
(3,'reply_text2','test2','testid2',SYSDATE(),SYSDATE()),
(5,'reply_text3','test3','testid3',SYSDATE(),SYSDATE()),
(7,'reply_text4','test4','testid4',SYSDATE(),SYSDATE()),
(9,'reply_text5','test5','testid5',SYSDATE(),SYSDATE()),
(11,'reply_text6','test6','testid6',SYSDATE(),SYSDATE()),
(13,'reply_text7','test7','testid7',SYSDATE(),SYSDATE()),
(15,'reply_text8','test8','testid8',SYSDATE(),SYSDATE()),
(17,'reply_text9','test9','testid9',SYSDATE(),SYSDATE()),
(19,'reply_text10','test10','testid10',SYSDATE(),SYSDATE());
SHOW COLUMNS FROM reply;
SELECT * FROM reply;
