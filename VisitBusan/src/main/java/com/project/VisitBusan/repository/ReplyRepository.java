package com.project.VisitBusan.repository;

import com.project.VisitBusan.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    // 1. 해당 게시글에 대한 댓글 조회
    @Query("select r from Reply r where r.board.id = :board_id")
    Page<Reply> listOfBoard(Long board_id, Pageable pageable);

    // 2. 해당 게시글에 대한 댓글 삭제
    void deleteByBoard_id(Long board_id);

    @Query("select count(r) from Reply r where r.board.id = :board_id")
    Long replyCount(Long board_id);
}
