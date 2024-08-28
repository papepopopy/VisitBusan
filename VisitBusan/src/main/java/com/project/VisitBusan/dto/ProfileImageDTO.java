package com.project.VisitBusan.dto;

import com.project.VisitBusan.entity.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Getter
@Setter
@Log4j2
public class ProfileImageDTO {

    private String uuid;        // 중복되지 않는 랜덤한 이름

    private String fileName;    // 파일이름
    private int ord;            // 순번

    private Member member;

}
