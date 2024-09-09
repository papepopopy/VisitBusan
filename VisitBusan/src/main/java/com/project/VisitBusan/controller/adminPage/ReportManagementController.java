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
@RequestMapping("/admin/report")
public class ReportManagementController {

    @GetMapping("/list")
    public String reportPage(Model model) {
        return "/adminPage/report";
    }

}
