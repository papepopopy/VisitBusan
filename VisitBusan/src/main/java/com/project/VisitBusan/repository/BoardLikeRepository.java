package com.project.VisitBusan.repository;

import com.project.VisitBusan.entity.BoardLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {

    @Query("SELECT count(bl.id) FROM BoardLike bl WHERE bl.board.id = :b_id")
    Long countBoardLike(@Param("b_id") Long boardId);

}
