package com.project.VisitBusan.controller.adminPage;

import com.project.VisitBusan.dto.MemberDTO;
import com.project.VisitBusan.entity.Member;
import com.project.VisitBusan.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/admin/member")
public class ApiAdminController {

    private final MemberService memberService;

    @PostMapping(value = "/list/modify") //데이터 전송
    public ResponseEntity<?> updateMember(@Valid @RequestBody MemberDTO memberDTO, //valid 유효성 검사
//                                       public String updateMember(@Valid @RequestBody MemberDTO memberDTO, //valid 유효성 검사
                                       BindingResult bindingResult, //유효성 검사 후 데이터 담는 객체
                                       RedirectAttributes redirectAttributes) { //일회성 데이터 전달
        System.out.println("수정 요청 받음: " + memberDTO);

        //회원정보 수정
        memberService.modify(memberDTO);
        Member updatedMember = memberService.modify(memberDTO);
        System.out.println("수정된 회원 정보: " + updatedMember);

        redirectAttributes.addFlashAttribute("message", "회원정보가 수정되었습니다.");

        return ResponseEntity.ok("SU");
    }

    //4. 회원정보 삭제
    @DeleteMapping("/list/delete/{userId}")
    public ResponseEntity<?> deleteMember(@PathVariable String userId) {
        try {
            memberService.remove(userId);  // userId를 기반으로 삭제 처리
            return ResponseEntity.ok("회원이 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("회원 삭제를 실패하였습니다.");
        }
    }
}

