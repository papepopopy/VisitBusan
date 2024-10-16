package com.project.VisitBusan.advice;

import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;


import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;


// 컨트롤러에서 발생하는 예외에 JSON과 같은 순수한 응답 메시지를 생성해서 보낼 수 있음.
@RestControllerAdvice  // resconroller에서 예외 발생시 처리
@Log4j2
public class CustomRestControllerAdvice {

    // BindException 예외가 발생하면 처리하는 메서드
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public ResponseEntity<Map<String, String>> handleBindException( BindException e ){

        log.error(e);

        Map<String, String> errorMap = new HashMap<>();

        if (e.hasErrors()){  //에러가 존재하면.  바인딩에 문제가 있으면

            BindingResult bindingResult = e.getBindingResult();
            bindingResult.getFieldErrors().forEach(fieldError -> {
                // 클라이언트에게 보낼 에러정보를 담은 Map객체(상태코드,응답코드를 보냄)
                errorMap.put(fieldError.getField(), fieldError.getCode());
            });
        }

        return ResponseEntity.badRequest().body(errorMap);
    }

    // 클라이언트 서버 문제가 아니라 데이터의 문제가 있으면 예외 처리하여 메시지를 전송
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public ResponseEntity<Map<String, String>> handleBindException2( DataIntegrityViolationException e ){
        log.error("==> RestController Exception: "+e);

        // Map구조 -> JSON구조
        Map<String, String> errorMap = new HashMap<>();

        errorMap.put("time", ""+System.currentTimeMillis());
        errorMap.put("msg", "constraint fails");

        return ResponseEntity.badRequest().body(errorMap);
    }

    // 클라이언트 서버 문제가 아니라 데이터의 문제가 있으면 예외 처리하여 메시지를 전송
    @ExceptionHandler({NoSuchElementException.class, HttpServerErrorException.InternalServerError.class})  //**
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public ResponseEntity<Map<String, String>> handleBindException3( DataIntegrityViolationException e ){
        log.error("==> RestController Exception: "+e);

        // Map구조 -> JSON구조
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("time", ""+System.currentTimeMillis());
        errorMap.put("msg", "No such Element Exception");

        return ResponseEntity.badRequest().body(errorMap);
    }

}
