package com.project.VisitBusan.repository.search;

import com.project.VisitBusan.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardSearch {

    // 검색어가 포함된 페이징, Pageable인자는 마지막에 위치할 것
    Page<Board> searchAll(String[] types, String keyword, Pageable pageable);

    // 특정 게시글에 대한 댓글 개수 계산하는 인터페이스
    
    // 게시글 조건 검색 조회
}