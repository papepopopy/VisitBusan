package com.project.VisitBusan.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.VisitBusan.entity.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
public class AlarmDTO {

    private Long id;

    private Member member;  // 알람을 받을 유저

    // 받을 알람 유형
    private Reply reply;
    private SubReply subReply;

    private BoardLike boardLike;
    private ReplyLike replyLike;
    private SubReplyLike subReplyLike;

    private Warning warning;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regDate;

}
