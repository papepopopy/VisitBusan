package com.project.VisitBusan.controller.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/board/user")
public class UserBoardController {
    //여행공유
    @GetMapping("/list")
    public String userList() {
        return "/board/userBoard/userList";
    }
    @GetMapping("/create")
    public String userCreate() {
        return "/board/userBoard/userCreate";
    }
    @GetMapping("/modify")
    public String userModify() {
        return "/board/userBoard/userModify";
    }
    @GetMapping("/read")
    public String userRead() {
        return "/board/userBoard/userRead";
    }
}
