package com.project.VisitBusan.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity@Table(name="board_file") // 생략하면 대문자 구분해서 이거랑 똑같이 만들어줌
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude="board")
public class BoardFile implements Comparable<BoardFile> {

    @Id
    private String uuid;        // 중복되지 않는 랜덤한 이름

    private String fileName;    // 파일이름
    private int ord;            // 순번

    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name="board_id")  // 생략시 자동 설정
    private Board board;

    @Override
    public int compareTo(BoardFile other) {

        // 정렬 항목이 숫자일 경우
        return this.ord - other.ord;  // 오름차순
//        return other.ord - this.ord;  // 내림차순
    }

    public void changeBoard(Board board) {
        this.board = board;
    }
}
