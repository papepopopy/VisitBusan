package com.project.VisitBusan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardListReplyCountDTO {

    private Long id;
    private String category;
    private String title;
    private String writer;
    private String writerId;
    private LocalDateTime regDate;
    private long viewCount;

    // 특정 게시글에 대한 댓글 개수
    private long replyCount;
    private long boardLikeCount;
}
