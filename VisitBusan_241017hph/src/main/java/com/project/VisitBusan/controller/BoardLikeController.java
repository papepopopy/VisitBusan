package com.project.VisitBusan.controller;

import com.project.VisitBusan.dto.BoardLikeDTO;
import com.project.VisitBusan.dto.PageRequestDTO;
import com.project.VisitBusan.dto.PageResponseDTO;
import com.project.VisitBusan.service.BoardLikeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


// REST방식 : 주로 XML, JSON형태의 문자열을 전송하고 이를 컨트롤러에서 처리하는 방식
@RestController
@RequestMapping("/boardLike")
@Log4j2
@RequiredArgsConstructor
public class BoardLikeController {

    private final BoardLikeService boardLikeService;  // 게시글 좋아요 서비스 객체

    // 게시글 좋아요 등록
    @PostMapping(value="/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String>  register(  // @Valid 제약조건 유효성 검사
                                           @Valid @RequestBody BoardLikeDTO boardLikeDTO, // boardLikeDTO랑 똑같은 이름의 클래스가 있으면 자동으로 값이 들어옴
                                           BindingResult bindingResult  // 유효성 검사 결과가 들어있음. 객체값 검증
                                         ) throws BindException {  //  에러가 존재하면 bindingResult에서 값을 받아서 리턴

        log.info("==> boardLikeDTO: "+boardLikeDTO);
        log.info("==> bindingResult: "+bindingResult.toString());
        log.info("==> BoardLikeDTO.get: "+boardLikeDTO.getId()+","+boardLikeDTO.getBoard_id()+","+boardLikeDTO.getRegDate());

        if (bindingResult.hasErrors()) {  // 에러가 존재하면 bindingResult에서 값을 받아서 BindException으로 리턴
            throw new BindException(bindingResult);
        }

        Long id = boardLikeService.register(boardLikeDTO);
        log.info("==> id: "+id);


        //Board board = Board.builder().board_id(boardLikeDTO.getBoard_id()).build();

        // 1
//        Map<String, String> resultMap = Map.of("board_id", String.valueOf(boardLikeDTO.getBoard_id()), "replyText" , boardLikeDTO.getReplyText(), "replyer", boardLikeDTO.getReplyer());
//        resultMap.put("id",300L);  // 에러 발생
//        return ResponseEntity.ok(resultMap);  // ResponseEntity.ok()  200성공 코드 + 반환값
//        return new ResponseEntity(resultMap, HttpStatus.OK);  // 같음
//        return ResponseEntity.ok(boardLikeDTO);

        // 2
//        Map<String, Long> resultMap = Map.of("id", id);
//        return resultMap;

        // 3
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("id", id+"번 게시글 좋아요이 등록되었습니다옹~");
        return resultMap;

    }

    // 2. 특정 게시물의 게시글 좋아요 목록
    @GetMapping(value="/list/{board_id}")
    public PageResponseDTO<BoardLikeDTO> getList (@PathVariable("board_id") Long board_id, // 경로에서 board_id값을 받음
                                                  PageRequestDTO pageRequestDTO) {

        log.info("==> pageRequestDTO: "+pageRequestDTO);
        log.info("==> board_id: "+board_id);

        PageResponseDTO<BoardLikeDTO> responseDTO = boardLikeService.getListBoard(board_id, pageRequestDTO);
        // 서버쪽에 클라이언트에 보내는 데이터: 페이징 객체, 게시글 좋아요 리스트 ...
        return responseDTO;

//        responseDTO.getDtoList().stream().forEach(reply-> log.info("==> reply: "+reply));
//        Map<String, List<BoardLikeDTO>> resultMap = Map.of("list", responseDTO.getDtoList());
//        return resultMap;

    }

    // 4. 좋아요 삭제
    @DeleteMapping(value="/{id}" )// 전송받은 data 종류 명시
    public Map<String, Long> remove(@PathVariable("id") Long id){  // 경로에서 id값을 받음


        boardLikeService.remove(id);

        // 클라이언트에게 보낼 data 정보 및 메시지
        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("id",id);

        return resultMap;
    }

    // 5. 게시글 좋아요 조회
    @GetMapping(value="/{id}" )// 전송받은 data 종류 명시
    public BoardLikeDTO getBoardLikeDTO(@PathVariable("id") Long id){

        BoardLikeDTO boardLikeDTO = boardLikeService.read(id);
        return boardLikeDTO;
    }
}


/*
Springdoc 공식 가이드에서 설명하는 어노테이션의 변화는 다음과 같다.

@Api → @Tag
@ApiIgnore
  → @Parameter(hidden = true) or @Operation(hidden = true) or @Hidden
@ApiImplicitParam
  → @Parameter
@ApiImplicitParams
  → @Parameters
@ApiModel
  → @Schema
@ApiModelProperty(hidden = true)
  → @Schema(accessMode = READ_ONLY)
@ApiModelProperty
  → @Schema
@ApiOperation(value = "foo", notes = "bar")
  → @Operation(summary = "foo", description = "bar")
@ApiParam
  → @Parameter
@ApiResponse(code = 404, message = "foo")
  → @ApiResponse(responseCode = "404", description = "foo")

 */