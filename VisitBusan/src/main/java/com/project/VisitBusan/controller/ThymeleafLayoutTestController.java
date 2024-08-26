package com.project.VisitBusan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/visitbusan")
public class ThymeleafLayoutTestController {
    @GetMapping("/main")
    public String main() {
        return "main";
    }
    @GetMapping("/boardlist")
    public String boardList() {
        return "components/boardList";
    }
    @GetMapping("/boardpage")
    public String boardPage() {
        return "components/boardPage";
    }
    @GetMapping("/posteditor")
    public String postEditor() {
        return "components/postEditor";
    }
    @GetMapping("/postpage")
    public String postPage() {
        return "components/postPage";
    }
}
