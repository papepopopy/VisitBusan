package com.project.VisitBusan.controller.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/board/travelGuide")
public class TravelGuideController {
    @GetMapping("/list")
    public String travelGuideList() {
        return "/board/travelGuide/travelGuideList";
    }
    @GetMapping("/create")
    public String userCreate() {
        return "/board/travelGuide/travelGuideCreate";
    }
    @GetMapping("/modify")
    public String travelGuideModify() {
        return "/board/travelGuide/travelGuideModify";
    }
    @GetMapping("/read")
    public String travelGuideRead() {
        return "/board/travelGuide/travelGuideRead";
    }
}
