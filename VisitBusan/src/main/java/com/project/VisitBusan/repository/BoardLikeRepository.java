package com.project.VisitBusan.repository;

import com.project.VisitBusan.entity.BoardLike;
import com.project.VisitBusan.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {

    // 1. 해당 게시글에 대한 좋아요 조회
    @Query("select bl from BoardLike bl where bl.board.id = :board_id")
    Page<BoardLike> listOfBoard(Long board_id, Pageable pageable);

    // 2. 해당 게시글에 대한 좋아요 삭제
    void deleteByBoard_id(Long board_id);

    @Query("SELECT count(bl.id) FROM BoardLike bl WHERE bl.board.id = :b_id")
    Long countBoardLike(@Param("b_id") Long board_id);

}
