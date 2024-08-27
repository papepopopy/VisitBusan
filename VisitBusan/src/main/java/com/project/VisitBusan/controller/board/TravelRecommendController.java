package com.project.VisitBusan.controller.board;

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
    //일정여행
    @GetMapping("/itineraries/list")
    public String ItinerariesList() {
        return "/board/travelRecommend/itinerariesCreate";
    }
    @GetMapping("/itineraries/create")
    public String ItinerariesCreate() {
        return "/board/travelRecommend/itinerariesList";
    }
    @GetMapping("/itineraries/modify")
    public String ItinerariesModify() {
        return "/board/travelRecommend/itinerariesModify";
    }
    @GetMapping("/itineraries/board")
    public String ItinerariesRead() {
        return "/board/travelRecommend/itinerariesRead";
    }

    //테마여행
    @GetMapping("/themeTravel/list")
    public String ThemeTravelList() {
        return "/board/travelRecommend/themeTravelCreate";
    }
    @GetMapping("/themeTravel/create")
    public String ThemeTravelCreate() {
        return "/board/travelRecommend/themeTravelList";
    }
    @GetMapping("/themeTravel/modify")
    public String ThemeTravelModify() {
        return "/board/travelRecommend/themeTravelModify";
    }
    @GetMapping("/themeTravel/board")
    public String ThemeTravelRead() {
        return "/board/travelRecommend/themeTravelRead";
    }
}



