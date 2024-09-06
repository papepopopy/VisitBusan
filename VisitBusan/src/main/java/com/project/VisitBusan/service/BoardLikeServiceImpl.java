package com.project.VisitBusan.service;

import com.project.VisitBusan.dto.BoardLikeDTO;
import com.project.VisitBusan.dto.PageRequestDTO;
import com.project.VisitBusan.dto.PageResponseDTO;
import com.project.VisitBusan.entity.BoardLike;
import com.project.VisitBusan.repository.BoardRepository;
import com.project.VisitBusan.repository.BoardLikeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Log4j2
@RequiredArgsConstructor
public class BoardLikeServiceImpl implements BoardLikeService {

    private final BoardRepository boardRepository;
    private final BoardLikeRepository boardLikeRepository;
    private final ModelMapper modelMapper;


    // 1. 게시글 좋아요 등록
    @Override
    public Long register(BoardLikeDTO boardLikeDTO) {

        /*
        // 게시글 번호 => board Entity 읽기 //  BoardLikeDTO에서 boardId를 쓰기위한 임시방편
        Board board = boardRepository.findById(boardLikeDTO.getBoardId()).orElseThrow();
        boardLikeDTO.setBoard(board);

        // 1.1 dto -> entity
        BoardLike boardLike = modelMapper.map(boardLikeDTO, BoardLike.class);
        Long id = boardLikeRepository.save(boardLike).getId();
        */

        // dto -> entity
        BoardLike boardLike = dtoToEntity(boardLikeDTO);
        // entity -> DB에 반영
        Long id = boardLikeRepository.save(boardLike).getId();

        return id;
    }

    // 2. 게시글 좋아요 조회
    @Override
    public BoardLikeDTO read(Long id) {
        Optional<BoardLike> boardLikeOptional = boardLikeRepository.findById(id);
        BoardLike boardLike = boardLikeOptional.orElseThrow();
        
//        return modelMapper.map(boardLike, BoardLikeDTO.class);
        return entityToDTO(boardLike);  // entity -> dto 전환 후 반환
    }

    // 4. 게시글 좋아요 삭제
    @Override
    public void remove(Long id) {
        log.info("boardLike remove id:"+id);
        boardLikeRepository.deleteById(id);

    }

    // 5. 게시글 좋아요 목록(페이징 기능이 있는 List)
    @Override
    public PageResponseDTO<BoardLikeDTO> getListBoard(Long boardId, PageRequestDTO pageRequestDTO) {

        //  PageRequest.of(0, 10, 정렬옵션)
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() <= 0 ? 0: pageRequestDTO.getPage() -1,
                pageRequestDTO.getSize(),
                Sort.by("id").ascending());

        // 조회된 결과를 Option타입으로 반환됨.
        Page<BoardLike> result = boardLikeRepository.listOfBoard(boardId, pageable);

        // Optional객체 내에 있는 내용을 .getContent()의해 추출하여
        // entity- > dto전환하여 List구조에 저장
        List<BoardLikeDTO> dtoList =
                result.getContent()
                        .stream()
                        // .map(boardLike -> modelMapper.map(boardLike, BoardLikeDTO.class))  1:1 맵핑을 해줌
                        .map(boardLike -> entityToDTO(boardLike))
                        .collect(Collectors.toList());

        return PageResponseDTO.<BoardLikeDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }

}
