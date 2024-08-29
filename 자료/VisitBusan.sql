-- Visit Busan

-- 데이터베이스 생성
CREATE DATABASE visit_busan;
-- 데이터베이스 삭제
DROP DATABASE visit_busan;
-- 데이터베이스 선택
USE visit_busan;
-- 선택된 데이터베이스의 테이블 목록 표시
SHOW TABLES;

SHOW COLUMNS FROM board;
SELECT * FROM board;
DELETE FROM board;
DROP TABLE board_file;
DROP TABLE board_like;
DROP TABLE board;

SHOW COLUMNS FROM board_file;
SELECT * FROM board_file;
DELETE FROM board_file;
DROP TABLE board_file;

SHOW COLUMNS FROM board_like;
SELECT * FROM board_like;
DELETE FROM board_like;
DROP TABLE board_like;
SELECT COUNT(bl.id) AS b_count FROM board_like bl;
SELECT COUNT(bl.id) AS b_count FROM board_like bl WHERE bl.board_id =1;

SHOW COLUMNS FROM member;
SELECT * FROM member;
DELETE FROM member;
DROP TABLE member;

SHOW COLUMNS FROM member_role_set;
SELECT * FROM member_role_set;
DELETE FROM member_role_set;
DROP TABLE member_role_set;

SHOW COLUMNS FROM profile_image;
SELECT * FROM profile_image;
DELETE FROM profile_image;
DROP TABLE profile_image;

SHOW COLUMNS FROM reply;
SELECT * FROM reply;
DELETE FROM reply;
DROP TABLE reply;