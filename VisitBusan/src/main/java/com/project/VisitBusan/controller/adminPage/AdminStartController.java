package com.project.VisitBusan.controller.adminPage;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Log4j2
public class AdminStartController {

    @GetMapping("/admin")
    public String mainPage(Model model) {
        return "adminPage/main";
    }

}
