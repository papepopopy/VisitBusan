package com.project.VisitBusan.controller.board;

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
    //공연전시
    @GetMapping("/exhibitions/list")
    public String exhibitionsList() {
        return "/board/festivalBoard/exhibitionsCreate";
    }
    @GetMapping("/exhibitions/create")
    public String exhibitionsCreate() {
        return "/board/festivalBoard/exhibitionsList";
    }
    @GetMapping("/exhibitions/modify")
    public String exhibitionsModify() {
        return "/board/festivalBoard/exhibitionsModify";
    }
    @GetMapping("/exhibitions/userBoard")
    public String exhibitionsRead() {
        return "/board/festivalBoard/exhibitionsRead";
    }

    //축제행사
    @GetMapping("/festival/list")
    public String festivalList() {
        return "/board/festivalBoard/festivalCreate";
    }
    @GetMapping("/festival/create")
    public String festivalCreate() {
        return "/board/festivalBoard/festivalList";
    }
    @GetMapping("/festival/modify")
    public String festivalModify() {
        return "/board/festivalBoard/festivalModify";
    }
    @GetMapping("/festival/userBoard")
    public String festivalRead() {
        return "/board/festivalBoard/festivalRead";
    }
}
