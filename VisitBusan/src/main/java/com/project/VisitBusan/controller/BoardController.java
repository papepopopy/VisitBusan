package com.project.VisitBusan.controller;


import com.project.VisitBusan.dto.BoardDTO;
import com.project.VisitBusan.dto.PageRequestDTO;
import com.project.VisitBusan.dto.PageResponseDTO;
import com.project.VisitBusan.entity.Board;
import com.project.VisitBusan.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.function.Function;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Log4j2
public class BoardController {

    private final BoardService boardService;

    // 카테고리
    // 관리자 게시판 :
    //  - 여행정보게시판(travelInfo) : 명소(place), 음식(food), 숙박(accommodation)
    //  - 여행추천게시판(travelRecommend) : 일정여행(scheduledTravel), 테마여행(themeTravel)
    //  - 여행가이드게시판(travelGuide) : 가이드(guide), 여행준비(preparation)
    //  - 축제게시판(festivalBoard) : 축제행사(festival), 공연전시(performance)
    // 유저 게시판 :
    //  - 유저게시판(userBoard) : 여행정보(information), 여행일정(schedule), 후기(review)


    // 1. 게시글 목록
    @GetMapping("/{category}")
    public String list(@PathVariable("category") String category,
                       PageRequestDTO pageRequestDTO,
                       Model model){
        // PageRequestDTO 객체 생성만 했을 경우 기본값 설정

        pageRequestDTO.setCategory(category);

        // 1-1. 게시글 댓글 개수 없는 List 조회
        PageResponseDTO responseDTO = boardService.list(pageRequestDTO);

        model.addAttribute("responseDTO", responseDTO);
        return switch (category) {
            case "place", "food", "accommodation" -> "/boards/travelInfo/list";
            case "scheduledTravel", "themeTravel" -> "/boards/travelRecommend/list";
            case "guide" -> "/boards/travelGuide/list";
            case "preparation" -> "/boards/travelGuide/travelPreparation";
            case "festival", "performance" -> "/boards/festivalBoard/list";
            case "information", "schedule", "review" -> "/boards/userBoard/list";
            default -> "";

        };

    }

    // 2. 게시글 등록
    @GetMapping("/{category}/create")
    public String registerGet(@PathVariable("category") String category) {

        return switch (category) {
            case "place", "food", "accommodation" -> "/boards/travelInfo/create";
            case "scheduledTravel", "themeTravel" -> "/boards/travelRecommend/create";
            case "guide" -> "/boards/travelGuide/create";
            case "preparation" -> "/boards/travelGuide/travelPreparation/create";
            case "festival", "performance" -> "/boards/festivalBoard/create";
            case "information", "schedule", "review" -> "/boards/userBoard/create";
            default -> "";

        };

    }

    @PostMapping("/{category}/create")
    // BoardDTO는 메서드가 호출 받았을 때 넘겨받은 파라미터 값이 BoardDTO의 필드명과 일치하면 자동 매핑 (일치하는 값만 불러옴)
    public String registerPost(@PathVariable("category") String category,
                               @Valid BoardDTO boardDTO,  // @Valid 넘어온 데이터 BoardDTO의 에러 유무 체크
                               BindingResult bindingResult,  // 감지한 에러 데이터
                               RedirectAttributes redirectAttributes) {

        log.info("==> boardDTO: "+boardDTO);

        // 클라이언트로 부터 전송받은 boardDTO를 @valid에서 문제가 발생했을 경우
        if(bindingResult.hasErrors()) {
            log.info("==> has errors...");

            // 1회용 정보유지 : redirect방식으로 요청시 정보관리하는 객체
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());  // 한번 에러값을 보낸 후 없어짐

            // Get방식으로 board/register를 재요청
            return "redirect:/board/"+category+"/create";
        }

        log.info("==> "+boardDTO);
        // 게시글 등록 서비스 호출(DB에 저장)
        Long id = boardService.register(boardDTO);

        redirectAttributes.addFlashAttribute("id",id);
        redirectAttributes.addFlashAttribute("result", "created");


        return "redirect:/board/"+category;


    }

    @GetMapping("/{category}/read")
    public String read(@PathVariable("category") String category,
                     Long id,
                     PageRequestDTO pageRequestDTO,
                     Model model) {

        // 게시글 조회 서비스 요청
        BoardDTO boardDTO = boardService.readOne(id);
        model.addAttribute("dto",boardDTO);

        /*
        반환값을 void로 할 경우
        return 생략하면 "board/read" 형태으로 자동 포워딩  (return "board/read";)
        */

        return switch (category) {
            case "place", "food", "accommodation" -> "/boards/travelInfo/read";
            case "scheduledTravel", "themeTravel" -> "/boards/travelRecommend/read";
            case "guide" -> "/boards/travelGuide/read";
            case "preparation" -> "/boards/travelGuide/travelPreparation/read";
            case "festival", "performance" -> "/boards/festivalBoard/read";
            case "information", "schedule", "review" -> "/boards/userBoard/read";
            default -> "";

        };
    }


    @GetMapping("/{category}/modify")  // 두개이상 사용시 {}안에 ,쓰고 하나 더 입력
    public String modifyget(@PathVariable("category") String category,
                          Long id,
                          PageRequestDTO pageRequestDTO,
                          Model model) {

        // 게시글 조회 서비스 요청
        BoardDTO boardDTO = boardService.readOne(id);
        model.addAttribute("dto",boardDTO);

        return switch (category) {
            case "place", "food", "accommodation" -> "/boards/travelInfo/modify";
            case "scheduledTravel", "themeTravel" -> "/boards/travelRecommend/modify";
            case "guide" -> "/boards/travelGuide/modify";
            case "preparation" -> "/boards/travelGuide/travelPreparation/modify";
            case "festival", "performance" -> "/boards/festivalBoard/modify";
            case "information", "schedule", "review" -> "/boards/userBoard/modify";
            default -> "";

        };

    }

    // 4. 게시글 수정
    @PostMapping("/{category}/modify")
    // BoardDTO는 메서드가 호출 받았을 때 넘겨받은 파라미터 값이 BoardDTO의 필드명과 일치하면 자동 매핑 (일치하는 값만 불러옴)
    public String modifypost(@PathVariable("category") String category,
                             BoardDTO boardDTO,  // @Valid 넘어온 데이터 BoardDTO의 에러 유무 체크
                             BindingResult bindingResult,  // 감지한 에러 데이터
                             PageRequestDTO pageRequestDTO,
                             RedirectAttributes redirectAttributes) {

        log.info("==> boardDTO: "+boardDTO);

        // 수정 페이지에서 넘겨받은 페이징 정보
        String link = pageRequestDTO.getLink();

        // 클라이언트로 부터 전송받은 boardDTO를 @valid에서 문제가 발생했을 경우
        if(bindingResult.hasErrors()) {
            log.info("==> has errors...");

            // addFlashAttribute() => 1회용 정보유지 : redirect방식으로 요청시 정보관리하는 객체
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());  // 한번 에러값을 보낸 후 없어짐
            redirectAttributes.addFlashAttribute("id", boardDTO.getId());

            // Get방식으로 board/modify+페이징정보 재요청
            return "redirect:/board/"+category+"/modify?"+link;
        }

        // 수정 서비스 요청
        Board board = boardService.modify(boardDTO);
        log.info("==> board: "+board);
        log.info("==> board.getBno: "+board.getId());

        // addFlashAttribute() => 1회용 정보유지 : redirect방식으로 요청시 정보관리하는 객체
        redirectAttributes.addFlashAttribute("result", "modified");
        redirectAttributes.addFlashAttribute("id", board.getId());

        // redirect방식에서 파라미터 추가 기능
        redirectAttributes.addAttribute("id", board.getId());

        return "redirect:/board/"+category+"/read?"+link;
//        return "redirect:/board/read";

    }

    // 5. 게시글 삭제
    @PostMapping("/{category}/remove")
    // BoardDTO는 메서드가 호출 받았을 때 넘겨받은 파라미터 값이 BoardDTO의 필드명과 일치하면 자동 매핑 (일치하는 값만 불러옴)
    public String remove(@PathVariable("category") String category,
                         BoardDTO boardDTO,
                         RedirectAttributes redirectAttributes){

        log.info("==> boardDTO: "+boardDTO);  // 맵핑 제대로 안되면 값이 null // 보내는 파라미터 값이 동일한지 // html내부에서 값을 잘 보내주는지(query,변수,식 등등) 확인

        Long id = boardDTO.getId();
        log.info("remove id: "+id);

        // 게시글 삭제 서비스 요청
        boardService.remove(id);

        // addFlashAttribute() => 1회용 정보유지 : redirect방식으로 요청시 정보관리하는 객체
        redirectAttributes.addFlashAttribute("id",id);
        redirectAttributes.addFlashAttribute("result", "removed");

        return "redirect:/board/"+category+"/list";
    }





}
