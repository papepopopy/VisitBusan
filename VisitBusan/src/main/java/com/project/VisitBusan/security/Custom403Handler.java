package com.project.VisitBusan.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

// 엑세스랑 관련된 에러
// 403에러 처리하는 클래스: 사용자 권한이 없는 경우, 특정 조건이 맞지 않은 경우 등
@Log4j2
public class Custom403Handler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.info("==> ACCESS DENIED");

        response.setStatus(HttpStatus.FORBIDDEN.value());

        // JSON을 요청했는지 확인
        String contentType = request.getHeader("Content-Type");
        boolean jsonRequest = contentType.startsWith("application/json");
        log.info("==> isJSON: "+jsonRequest);

        // 일반 request
        if (!jsonRequest) {
            response.sendRedirect("/members/login?error=ACCESS_DENIED");
        }

    }
}
