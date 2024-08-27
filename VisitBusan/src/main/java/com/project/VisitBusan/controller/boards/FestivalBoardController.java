package com.project.VisitBusan.controller.boards;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/board/festivalBoard")
public class FestivalBoardController {
    //공연전시 축제행사
    @GetMapping("/list")
    public String festivalBoardList() {
        return "/board/festivalBoard/list";
    }
    @GetMapping("/create")
    public String festivalBoardCreate() {
        return "/board/festivalBoard/create";
    }
    @GetMapping("/modify")
    public String festivalBoardModify() {
        return "/board/festivalBoard/modify";
    }
    @GetMapping("/read")
    public String festivalBoardRead() {
        return "/board/festivalBoard/read";
    }

}
