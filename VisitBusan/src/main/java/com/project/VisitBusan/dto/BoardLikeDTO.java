package com.project.VisitBusan.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.VisitBusan.entity.Board;
import com.project.VisitBusan.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
public class BoardLikeDTO {

    private Long id;
    private Board board;
    private Member member;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regDate;


}
