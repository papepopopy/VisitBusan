package com.project.VisitBusan.controller.boards;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/boards/travelRecommend")
public class TravelRecommendController {
    //일정여행 테마여행
    @GetMapping("/list")
    public String ItinerariesList() {
        return "/boards/travelRecommend/list";
    }
    @GetMapping("/create")
    public String ItinerariesCreate() {
        return "/boards/travelRecommend/create";
    }
    @GetMapping("/modify")
    public String ItinerariesModify() {
        return "/boards/travelRecommend/modify";
    }
    @GetMapping("/read")
    public String ItinerariesRead() {
        return "/boards/travelRecommend/read";
    }

}



