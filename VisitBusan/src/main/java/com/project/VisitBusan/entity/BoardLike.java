package com.project.VisitBusan.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BoardLike {

    @Id  // 기본키로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

}
// member는 여러개의 좋아요를 누를 수 있고. member:like(1:N)
// board는 여러개의 좋아요를 받을 수 있다.  board:like(1:N)
// member:like:board(1:N:1)

