package com.project.VisitBusan.controller.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/board/travelInfo")
public class TravelInfoController {
    @GetMapping("/destinations/list")
    public String destinationsList() {
        return "/board/travelInfo/destinationsList";
    }
    @GetMapping("/destinations/create")
    public String destinationsCreate() {
        return "/board/travelInfo/destinationsCreate";
    }
    @GetMapping("/destinations/modify")
    public String userModify() {
        return "/board/travelInfo/destinationsModify";
    }
    @GetMapping("/destinations/read")
    public String destinationsRead() {
        return "/board/travelInfo/destinationsRead";
    }

    @GetMapping("/food/list")
    public String foodList() {
        return "/board/travelInfo/foodList";
    }
    @GetMapping("/food/create")
    public String foodCreate() {
        return "/board/travelInfo/foodCreate";
    }
    @GetMapping("/food/modify")
    public String foodModify() {
        return "/board/travelInfo/foodModify";
    }
    @GetMapping("/food/read")
    public String foodRead() {
        return "/board/travelInfo/foodRead";
    }

    @GetMapping("/hotels/list")
    public String hotelsList() {
        return "/board/travelInfo/hotelsList";
    }
    @GetMapping("/hotels/create")
    public String hotelsCreate() {
        return "/board/travelInfo/hotelsCreate";
    }
    @GetMapping("/hotels/modify")
    public String hotelsModify() {
        return "/board/travelInfo/hotelsModify";
    }
    @GetMapping("/hotels/read")
    public String hotelsRead() {
        return "/board/travelInfo/hotelsRead";
    }
}
