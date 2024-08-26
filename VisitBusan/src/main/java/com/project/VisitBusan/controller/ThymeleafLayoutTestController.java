package com.project.VisitBusan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/layout")
public class ThymeleafLayoutTestController {
    @GetMapping("/main")
    public String base() {
        return "main";
    }
}
