package com.project.VisitBusan.exception;

public class DuplicateUserIdException extends RuntimeException {
    //중복 데이터 확인 클래스
    public DuplicateUserIdException(String message) {
        super(message);
    }
}