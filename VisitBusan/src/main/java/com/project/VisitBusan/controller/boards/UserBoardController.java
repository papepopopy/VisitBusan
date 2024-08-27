package com.project.VisitBusan.controller.boards;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/board/member")
public class UserBoardController {
    //여행공유
    @GetMapping("/list")
    public String userList() {
        return "/board/memberBoard/list";
    }
    @GetMapping("/create")
    public String userCreate() {
        return "/board/memberBoard/create";
    }
    @GetMapping("/modify")
    public String userModify() {
        return "/board/memberBoard/modify";
    }
    @GetMapping("/read")
    public String userRead() {
        return "/board/memberBoard/read";
    }
}
