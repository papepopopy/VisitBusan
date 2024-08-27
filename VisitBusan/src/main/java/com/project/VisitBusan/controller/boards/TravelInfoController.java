package com.project.VisitBusan.controller.boards;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/boards/travelInfo")
public class TravelInfoController {
    //명소
    @GetMapping("/list")
    public String travelInfoList() {
        return "/boards/travelInfo/list";
    }
    @GetMapping("/create")
    public String travelInfoCreate() {
        return "/boards/travelInfo/create";
    }
    @GetMapping("/modify")
    public String travelInfoModify() {
        return "/boards/travelInfo/modify";
    }
    @GetMapping("/read")
    public String travelInfoRead() {
        return "/boards/travelInfo/read";
    }
}
