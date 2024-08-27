package com.project.VisitBusan.controller.fragments;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/modal")
public class ModalController {
    @GetMapping("")
    public String showLoginForm() {
        return "/fragment/report";
    }
}
