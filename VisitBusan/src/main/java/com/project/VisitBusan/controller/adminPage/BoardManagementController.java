package com.project.VisitBusan.controller.adminPage;


import com.project.VisitBusan.dto.*;
import com.project.VisitBusan.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/admin/board")
public class BoardManagementController {

    private final BoardService boardService;

    @GetMapping("")
    public String boardPage(
            PageRequestDTO pageRequestDTO,
            Model model) {

        // 1-1. 게시글 댓글 개수 없는 List 조회
//        PageResponseDTO responseDTO = boardService.list(pageRequestDTO);

        // 1-2. 게시글 조회수,댓글수,좋아요수 List 조회
        PageResponseDTO<BoardListReplyCountDTO> responseDTO = boardService.listWithReplyCount(pageRequestDTO);

        // 1-3. 게시글 파일포함 List 조회
//        PageResponseDTO<BoardListAllDTO> responseDTO = boardService.listWithAll(pageRequestDTO);

//        log.info("=> "+responseDTO);

        model.addAttribute("responseDTO", responseDTO);

        return "adminPage/board";

    }

    @GetMapping("/read")
    public String read(Long id,
                       PageRequestDTO pageRequestDTO,
                       Model model) {

        log.info("==> id: "+id);
        log.info("==> pageRequestDTO: "+pageRequestDTO);

        // 게시글 조회 서비스 요청
        BoardDTO boardDTO = boardService.readOne(id);
        model.addAttribute("dto",boardDTO);
        log.info("==> after service boardDTO: "+boardDTO);

        boardService.viewCount(boardDTO);

        /*
        반환값을 void로 할 경우
        return 생략하면 "board/read" 형태으로 자동 포워딩  (return "board/read";)
        */
//        return "boards/userBoard/read";

        return "adminPage/boardRead";

    } // end get read

    // 5. 게시글 삭제
    @PostMapping("/remove")
    // BoardDTO는 메서드가 호출 받았을 때 넘겨받은 파라미터 값이 BoardDTO의 필드명과 일치하면 자동 매핑 (일치하는 값만 불러옴)
    public String remove(Long id,
                         PageRequestDTO pageRequestDTO,
                         RedirectAttributes redirectAttributes){

        // 수정 페이지에서 넘겨받은 페이징 정보
        String link = pageRequestDTO.getLink();

        // 게시글 삭제 서비스 요청
        boardService.remove(id);

        // addFlashAttribute() => 1회용 정보유지 : redirect방식으로 요청시 정보관리하는 객체
        redirectAttributes.addFlashAttribute("id",id);
        redirectAttributes.addFlashAttribute("result", "removed");

        return "redirect:/admin/board?"+link;

    } // end remove

} // end class
