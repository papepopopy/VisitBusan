package com.project.VisitBusan.service;

import com.project.VisitBusan.dto.*;
import com.project.VisitBusan.entity.Board;

public class BoardServiceImpl implements BoardService {
    @Override
    public long register(BoardDTO boardDTO) {
        return 0;
    }

    @Override
    public BoardDTO readOne(Long bno) {
        return null;
    }

    @Override
    public Board modify(BoardDTO boardDTO) {
        return null;
    }

    @Override
    public void remove(Long bno) {

    }

    @Override
    public PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO) {
        return null;
    }

    @Override
    public PageResponseDTO<BoardListReplyCountDTO> listWithReplyCount(PageRequestDTO pageRequestDTO) {
        return null;
    }

    @Override
    public PageResponseDTO<BoardListAllDTO> listWithAll(PageRequestDTO pageRequestDTO) {
        return null;
    }
}
