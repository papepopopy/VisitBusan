package com.project.VisitBusan.exception;

public class DuplicateEmailException extends RuntimeException {
    //중복 데이터 확인 클래스
    public DuplicateEmailException(String message) {
        super(message);
    }
}