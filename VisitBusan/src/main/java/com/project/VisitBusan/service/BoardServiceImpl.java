package com.project.VisitBusan.service;

import com.project.VisitBusan.dto.*;
import com.project.VisitBusan.entity.Board;
import com.project.VisitBusan.entity.FestivalInfo;
import com.project.VisitBusan.entity.Member;
import com.project.VisitBusan.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service  // 서비스
@Log4j2
@RequiredArgsConstructor
@Transactional  // transaction단위로 작업
public class BoardServiceImpl implements BoardService {

    // @RequiredArgsConstructor + final 변수 == @Autowired된 변수랑 똑같음
    private final ModelMapper modelMapper;
    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;
    private final BoardLikeRepository boardLikeRepository;
    private final ReplyLikeRepository replyLikeRepository;
    private final FestivalInfoRepository festivalInfoRepository;

    // 게시글 등록
    @Override
    public long register(BoardDTO boardDTO, Member member) {
        // 1. DTO -> Entity : 첨부파일이 없는 경우
//        Board board = modelMapper.map(boardDTO, Board.class);  // boardDTO를 Board클래스에 1:1로 맵핑 시킴
        // 1:1 맵핑하는 작업을 수작업할 시 Board 변수에 일일이 넣어줘야함
        Board board = Board.builder()
                .category(boardDTO.getCategory())
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .writer(member.getUserId())  // writer를 userId로 설정
                .writerId(member)  // member 객체 설정
                .build();
        // 2. DTO -> Entity : 첨부파일을 추가한 경우
//        Board board = dtoToEntity(boardDTO, member);

        // 3. board entity 저장
        Long id = boardRepository.save(board).getId();  // 보드를 저장하고 정상적으로 작동하면 변수에 값 저장
        //Board savedBoard = boardRepository.save(board);

        return id;

    } // end register

    // 게시글 조회

    @Override
    public BoardDTO readOne(Long id) {
        log.info("==> ReadOne start");

        // 1. fetch = FetchType.LAZY 상태일 경우 boardImage 즉시 로딩 안됨
//         Optional<Board> result = boardRepository.findById(id);

        // 2. fetch = FetchType.LAZY 상태일 경우에도 즉시 로딩 (@EntityGraph)
        Optional<Board> result = boardRepository.findByIdWithFiles(id);
        log.info("==> result: "+result);

        // optional -> entity
        Board board = result.orElseThrow();  // optional -> entity
        log.info("==> board: "+board);


        // 1. entity -> dto 맵핑 (entity와 dto 동일 구조일 경우)
//            BoardDTO boardDTO = modelMapper.map(board, BoardDTO.class);
        // 2. 다를 경우
        // BoardDTO boardDTO = entityToDTO(board);
        // log.info("==> after entityToDTO boardDTO: "+boardDTO);
        // return boardDTO;

        FestivalInfo festivalInfo = festivalInfoRepository.findByBoard_id(id);

        log.info("==> festivalInfo" + festivalInfo);

        BoardDTO boardDTO = null;
        if (festivalInfo != null) {
            log.info("==> festivalInfo != null");
            boardDTO = entityToDTOAll(board, festivalInfo);

        } else {
            log.info("==> festivalInfo == null");
            boardDTO = entityToDTO(board);
        }

        return boardDTO;

    } // end readOne

    // 게시글 수정
    @Override
    public Board modify(BoardDTO boardDTO) {

        // 수정할 글번호 유무 체크
        Optional<Board> result = boardRepository.findById(boardDTO.getId());
        Board board = result.orElseThrow();
        log.info("==> from service board: "+board);

        // entity값을 dto값으로 변경
        board.change(boardDTO.getCategory(), boardDTO.getTitle(), boardDTO.getContent());

        // ------------------------------------------------------- //
        // 기존 첨부파일 있을 경우 처리 : 기존에 첨부파일 삭제 후 추가하는 방식
        board.clearFiles();
        log.info("==> from service board: "+board);
        log.info("==> from service board.getImageSet(): "+board.getBoardFileSet());

        // 수정된 첨부파일이 있을 경우
        if (boardDTO.getFileNames() != null) {
            for (String fileName : boardDTO.getFileNames()) {
                String[] arr = fileName.split("_vb_");
                board.addFile(arr[0],arr[1]);
            }
        }
        // ------------------------------------------------------- //

        // 저장하기
        Board modifiedBoard = boardRepository.save(board);

        return modifiedBoard;  // 수정된 board

    } //end modify

    // 게시글 삭제
    @Override
    public void remove(Long id) {

        // 댓글이 있는 게시글 삭제
        // 댓글(외래키(FK)) 때문에 삭제 안됨
        // 댓글이 있는 경우 댓글 삭제 후 게시글을 삭제 해야함.

        // 1. 댓글 그냥 삭제 (체크 안해도 상관없음)
        replyRepository.deleteByBoard_id(id);

        // 2. 댓글이 있는지 체크 후 댓글 삭제
//        List<Reply> replies = replyRepository.findByBoard_id(id);
//        log.info("==> replies"+replies);
//
//        if(replies.size() > 0) {  // 댓글이 있으면 댓글 삭제
//            log.info("==> delete replies");
//            replyRepository.deleteByBoard_id(id);
//        }
        // 댓글 삭제 후 게시글 삭제
        boardRepository.deleteById(id);

    } // end remove

    @Override
    public PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO) {

        // 검색 조건에 대한 처리
        String category = pageRequestDTO.getBCategory();
        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("id");
        log.info("==> pageRequestDTO: "+pageRequestDTO);

        // 조건 검색 및 페이징한 결과값 가져오기
        Page<Board> result = boardRepository.searchAll(category, types, keyword, pageable);

        log.info("==> result: "+result);

        // page객체에 있는 내용을 List구조 가져오기
        List<BoardDTO> dtoList = result.getContent().stream()
                // collection 구조에 있는 entity를 하나씩 dto로 변환하여 List구조에 저장
                .map(board-> modelMapper.map(board,BoardDTO.class))
                .collect(Collectors.toList());

        log.info("==> dtoList: "+dtoList);

        // 매개변수로 전달받은 객체(pageRequestDTO)를 가지고 PageResponseDTO.Builder()를 통해
        // PageRequestDTO객체 생성되어 필요시 스프링이 필요시점에 주입 시켜줌(list에서 pageRequestDTO객체 사용가능함 )
        return PageResponseDTO.<BoardDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();

    } // end list

    @Override
    public PageResponseDTO<BoardListReplyCountDTO> listWithReplyCount(PageRequestDTO pageRequestDTO) {

        // 검색 조건에 대한 처리
        String category = pageRequestDTO.getBCategory();
        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("id");

        // 조건 검색 및 페이징한 결과값 가져오기
        Page<BoardListReplyCountDTO> result = boardRepository.searchWithReplyCount(category, types, keyword, pageable);

        // 매개변수로 전달받은 객체(pageRequestDTO)를 가지고 PageResponseDTO.Builder()를 통해
        // PageRequestDTO객체 생성되어 필요시 스프링이 필요시점에 주입 시켜줌(list에서 pageRequestDTO객체 사용가능함 )
        return PageResponseDTO.<BoardListReplyCountDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(result.getContent()) // Projection.bean(): JPQL의 결과를 바로 DTO로 처리한 결과를 입력 **
                .total((int)result.getTotalElements())
                .build();

    } // end listWithReplyCount

    // 게시글의 이미지와 댓글의 숫자 처리기능 구현
    @Override
    public PageResponseDTO<BoardListAllDTO> listWithAll(PageRequestDTO pageRequestDTO) {

        // 검색 조건에 대한 처리
        String category = pageRequestDTO.getBCategory();
        String[] types = pageRequestDTO.getTypes();  // 검색 타입(글제목, 글내용, 작성자)
        String keyword = pageRequestDTO.getKeyword(); // 검색 키워드
        LocalDateTime startDate = pageRequestDTO.getStartDate();
        LocalDateTime endDate = pageRequestDTO.getEndDate();
        Pageable pageable = pageRequestDTO.getPageable("id");

        // BoardSearch 클래스로를 상속받은 boardRepository는 searchWithAll() 사용가능
        Page<BoardListAllDTO> result = boardRepository.searchWithAll(category, types, keyword, startDate, endDate, pageable);

        return PageResponseDTO.<BoardListAllDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(result.getContent())
                .total((int)result.getTotalElements())  // 이거 잘 설정해줘야함. 잘못하면 계산 어긋남
                .build();

    } // end listWithAll()

    @Override
    public void viewCount(BoardDTO boardDTO, Member member) {

        boardDTO.setViewCount(boardDTO.getViewCount()+1L);

        Board board = this.dtoToEntity(boardDTO, member);

        boardRepository.save(board);

    } // end viewCount()

    @Override
    public void baordLikeCount(Long board_id) {
    }

} // end class