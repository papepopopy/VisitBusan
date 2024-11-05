package com.project.VisitBusan.controller;

import com.project.VisitBusan.dto.MemberDTO;
import com.project.VisitBusan.dto.ProfileImageDTO;
import com.project.VisitBusan.service.MemberService;
import com.project.VisitBusan.service.ProfileImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

//전역 컨트롤러
@ControllerAdvice
@Log4j2
@RequiredArgsConstructor
public class GlobalController {

    private final MemberService memberService;
    private final ProfileImageService profileImageService;

    @ModelAttribute
    public void addUserData(Model model, @AuthenticationPrincipal UserDetails userDetails) {

        // 로그인하지 않은 경우
        if (userDetails == null) {
            log.info("사용자가 로그인하지 않았습니다.");
            model.addAttribute("member", null);
            model.addAttribute("profileImage", null);
            return;
        }

        //로그인한 사용자 ID
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("=> userId: "+userId);
        //회원 정보 조회
        MemberDTO memberDTO = memberService.findMember(userId);
        //프로필 이미지 조회
        ProfileImageDTO profileImageDTO = new ProfileImageDTO();
        profileImageService.findImage(profileImageDTO, userId);
        model.addAttribute("member", memberDTO);
        model.addAttribute("profileImage", profileImageDTO);
    }
}
