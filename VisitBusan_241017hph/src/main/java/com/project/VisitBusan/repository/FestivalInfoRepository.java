package com.project.VisitBusan.repository;

import com.project.VisitBusan.entity.FestivalInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FestivalInfoRepository extends JpaRepository<FestivalInfo, Long> {

    // 해당 게시글에 대한 댓글 삭제
    void deleteByBoard_id(Long board_id);

    // 해당 게시글에 대한 축제 정보
    FestivalInfo findByBoard_id(Long board_id);
}
