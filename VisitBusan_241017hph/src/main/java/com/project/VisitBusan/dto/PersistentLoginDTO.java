package com.project.VisitBusan.dto;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class PersistentLoginDTO {
    //자동 로그인 데이터 저장
    private String series;
    private String username;
    private String token;
    private LocalDateTime lastUsed;
}
