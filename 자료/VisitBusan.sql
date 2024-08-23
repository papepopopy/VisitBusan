-- Visit Busan

-- 데이터베이스 생성
CREATE DATABASE visit_busan;

-- 데이터베이스 선택
USE visit_busan;
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