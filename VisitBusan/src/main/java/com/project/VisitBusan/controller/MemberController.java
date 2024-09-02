package com.project.VisitBusan.controller;

import com.project.VisitBusan.dto.MemberDTO;
import com.project.VisitBusan.service.MemberService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("")
public class MemberController {
    private final MemberService memberService;
    //private final PasswordEncoder passwordEncoder;

    // 회원 등록: GET, POST
    @GetMapping(value="/signup")
    public String memberRegisterForm(Model model){
        // 데이터가 없는 memberDTO생성 : form에 입력한 데이터와 1:1 맵핑
        model.addAttribute("memberDTO", new MemberDTO());

        // 포워딩: 뷰리졸브
        return "members/signUp";
    }

    // @ModelAttribute: 다양한 소스의 데이터를 모델 특성으로 바인딩하는 데 사용
    // @RequestBody: HTTP request body를 메소드에 매핑하는데 사용
    @PostMapping(value="/signup")
    public String memberRegister(@Valid @ModelAttribute MemberDTO memberDTO,
                                 BindingResult bindingResult,
                                 Model model){

        log.info("=> memberDTO: "+memberDTO);

        if (bindingResult.hasErrors()){// 유효성 검사결과 1개이상 에러가 있으면 처리
            log.info("=> bindingResult: "+ bindingResult.toString());

            return "members/signUp";
        }

        try {
            // dto -> entity -> email중복 체크 ->  save
            memberService.saveMember(memberDTO);
        } catch(Exception e){ // 중복된 이메일 등록시 예외발생되는 Exception 처리
            model.addAttribute("errorMessage",e.getMessage());
            return "members/signup";// 입력폼으로 포워딩
        }

        //회원가입 후 로그인 페이지 이동
        return "redirect:/login";
    }

    //----------------------- //
    // 로그인, 로그아웃 처리
    //----------------------- //
    // 1. 로그인
    @GetMapping(value="/login")
    public String loginMember(String error, String logout){
        log.info("=> login ");

        // 로그인 폼이 있는 페이지로 포워딩
        return "/members/login";
    }
    // 1-1. 로그인 실패시 처리할 url
    @GetMapping("/login/error")
    public String loginError(Model model){
        log.info("==> login error");

        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호 확인해주세요.");
        return "/members/login";

    }

}
