package com.project.VisitBusan.entity;

import jakarta.persistence.*;
import lombok.*;

// Entity 정의 : 테이블에 적용될 구조설계 정의하여 테이블과 entity 1:1 맵핑
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubReply extends BaseEntity {

    @Id  // 기본키로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 현재 댓글의 필드명은 엔티티_필드명(PK)으로 자동으로 생성 => board_bno
    // private Board board에서 @ManyToOne 필수
    @ManyToOne(fetch = FetchType.LAZY)
    private Reply reply;

    private String replyText;
    private String replier;
    private String replierEmail;

    // 수정 작업을 위한 메서드 정의
    public void changeText(String text) {
        this.replyText = text;
    }

}
