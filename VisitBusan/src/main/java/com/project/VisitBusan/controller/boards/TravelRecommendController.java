package com.project.VisitBusan.controller.boards;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/board/travelRecommend")
public class TravelRecommendController {
    //일정여행 테마여행
    @GetMapping("/list")
    public String ItinerariesList() {
        return "/board/travelRecommend/list";
    }
    @GetMapping("/create")
    public String ItinerariesCreate() {
        return "/board/travelRecommend/create";
    }
    @GetMapping("/modify")
    public String ItinerariesModify() {
        return "/board/travelRecommend/modify";
    }
    @GetMapping("/board")
    public String ItinerariesRead() {
        return "/board/travelRecommend/board";
    }

}



