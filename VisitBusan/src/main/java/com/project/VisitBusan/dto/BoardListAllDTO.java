package com.project.VisitBusan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardListAllDTO {

    private Long id;
    private String category;
    private String title;
    private String writer;
    private String writerId;
    private LocalDateTime regDate;
    private long viewCount;

    //SELECT COUNT(*) FROM reply WHERE board_id = 1 (게시글번호)
    private long replyCount;
    private long boardLikeCount;


    private List<BoardFileDTO> boardFiles;

}
