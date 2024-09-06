package com.project.VisitBusan.controller;

import com.project.VisitBusan.dto.PageRequestDTO;
import com.project.VisitBusan.dto.PageResponseDTO;
import com.project.VisitBusan.dto.ReplyDTO;
import com.project.VisitBusan.service.ReplyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/replies")
@Log4j2
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    // 1. 댓글 등록
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)// 전송받은 data 종류 명시
    public Map<String, String> register(
            @Valid @RequestBody ReplyDTO replyDTO,
            BindingResult bindingResult
    ) throws BindException {

        log.info("=> replyDTO: " + replyDTO);

        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        Long id = replyService.register(replyDTO);

        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("id", id + "번 댓글 등록되었습니다.");

        return resultMap;
    }

    // 2. 특정 게시물의 댓글 목록
    @GetMapping(value = "/list/{board_id}")
    public PageResponseDTO<ReplyDTO> getList(
            @PathVariable("board_id") Long board_id,
            PageRequestDTO pageRequestDTO) {

        PageResponseDTO<ReplyDTO> responseDTO = replyService.getListOBoard(board_id, pageRequestDTO);

        return responseDTO;
    }

    // 3. 댓글 수정
    @PutMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Long> modify(@PathVariable("id")Long id,
                                    @RequestBody ReplyDTO replyDTO) {

        replyDTO.setId(id);
        replyService.modify(replyDTO);

        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("id",id);

        return resultMap;
    }

    // 4. 댓글 삭제
    @DeleteMapping(value="/{id}")
    public Map<String, Long> remove(@PathVariable("id")Long id) {

        replyService.remove(id);

        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("id",id);

        return resultMap;
    }

    // 5. 댓글 조회
    @GetMapping(value="/{id}")
    public ReplyDTO getReplyDTO(@PathVariable("id") Long id) {

        ReplyDTO replyDTO = replyService.read(id);
        return replyDTO;
    }

}