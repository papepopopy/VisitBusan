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

    //로그인
    @GetMapping("/login")
    public String showLoginForm() {
        return "/user/login";
    }

    //프로필 수정페이지
    @GetMapping("/modify")
    public String showModifyForm() {
        return "/user/modify";
    }

    //마이페이지
    @GetMapping("/mypage")
    public String showMyPage() {
        return "/user/myPage";
    }

    //알림함
    @GetMapping("/mypageMail")
    public String showMyPageMail() {
        return "/user/myPageMail";
    }

    //회원가입(정보입력)
    @GetMapping("/signup/1")
    public String showSignUp1() {
        return "/user/signUp_1";
    }
    //회원가입(정보입력)
    @GetMapping("/signup/2")
    public String showSignUp2() {
        return "/user/signup_2";
    }
    //회원가입(정보입력)
    @GetMapping("/signup/3")
    public String showSignUp3() {
        return "/user/signup_3";
    }
}
