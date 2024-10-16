package com.project.VisitBusan.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


// Entity 정의 : 테이블에 적용될 구조설계 정의하여 테이블과 entity 1:1 맵핑
@Entity@Table(name="reply")  // name을 따로 설정하지 않으면 자동으로 엔티티명과 동일한 이름의 테이블을 만듬
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reply extends BaseEntity {

    @Id  // 기본키로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 현재 댓글의 필드명은 엔티티_필드명(PK)으로 자동으로 생성 => board_bno
    // private Board board에서 @ManyToOne 필수
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    @Column(length = 2000, nullable = false)
    private String replyText;
    @Column(length = 50, nullable = false)
    private String replier;
    @Column(length = 50, nullable = false)
    private String replierId;

    // 수정 작업을 위한 메서드 정의
    public void changeText(String text) {
        this.replyText = text;
    }
}
