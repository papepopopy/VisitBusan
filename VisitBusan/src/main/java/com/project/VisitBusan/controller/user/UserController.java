package com.project.VisitBusan.controller.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/user")
public class UserController {

    @GetMapping("/login")
    public String showLoginForm() {
        return "/user/login";
    }

    @GetMapping("/modify")
    public String showModifyForm() {
        return "/user/modify";
    }

    @GetMapping("/mypage")
    public String showMyPage() {
        return "/user/mypage";
    }

    @GetMapping("/mypageMail")
    public String showMyPageMail() {
        return "/user/mypageMail";
    }

    @GetMapping("/signup/1")
    public String showsignup1() {
        return "/user/signup_1";
    }

    @GetMapping("/signup/2")
    public String showsignup2() {
        return "/user/signup_2";
    }

    @GetMapping("/signup/3")
    public String showsignup3() {
        return "/user/signup_3";
    }
}
