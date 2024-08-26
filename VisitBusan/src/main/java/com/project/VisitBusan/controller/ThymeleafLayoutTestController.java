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
    @GetMapping("/base")
    public String base() {
        return "layout/base";
    }
}
