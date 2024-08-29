package com.project.VisitBusan.controller;

import com.project.VisitBusan.dto.MemberDTO;
import com.project.VisitBusan.entity.Member;
import com.project.VisitBusan.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("")
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/signup")
    public String memberSignUp(Model model){
        model.addAttribute("memberDTO", new MemberDTO());

        return "members/signUp_1";
    }
    @PostMapping(value = "/signup")
    public String memberSignUp(@Valid @ModelAttribute Member member,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            return "members/signUp_1";
        }
        try {
            // dto -> entity -> email중복 체크 ->  save
            memberService.saveMember(memberDTO);
        } catch(Exception e){ // 중복된 이메일 등록시 예외발생되는 Exception 처리
            model.addAttribute("errorMessage",e.getMessage());
            return "members/signUp_1";// 입력폼으로 포워딩
        }

        return "redirect:/";
    }
}
