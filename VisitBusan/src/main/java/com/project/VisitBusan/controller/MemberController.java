package com.project.VisitBusan.controller;

import com.project.VisitBusan.dto.MemberDTO;
import com.project.VisitBusan.dto.PageRequestDTO;
import com.project.VisitBusan.entity.Member;
import com.project.VisitBusan.exception.DuplicateEmailException;
import com.project.VisitBusan.exception.DuplicateUserIdException;
import com.project.VisitBusan.service.MemberService;
import com.project.VisitBusan.service.MemberServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("")
public class MemberController {
    private final MemberService memberService;
    private final MemberServiceImpl memberServiceImpl;
    private final PasswordEncoder passwordEncoder;

    //----------------------- //
    // 회원가입
    //----------------------- //
    // 회원 등록: GET, POST
    @GetMapping(value="/signup")
    public String memberRegisterForm(Model model){
        // 데이터가 없는 memberDTO생성 : form에 입력한 데이터와 1:1 맵핑
        model.addAttribute("memberDTO", new MemberDTO());

        // 포워딩: 뷰리졸브
        return "members/signUp";
    }

    @PostMapping(value="/signup")
    public String memberRegister(@Valid @ModelAttribute MemberDTO memberDTO,
                                 BindingResult bindingResult,
                                 Model model){

        log.info("=> memberDTO: "+memberDTO);

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

    // 중복 체크 처리
    @PostMapping("/check-existing")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> checkExisting(@RequestBody Map<String, String> requestData) {
        Map<String, Object> response = new HashMap<>();
        String userId = requestData.get("userId");
        String email = requestData.get("email");

        try {
            // ID와 이메일을 가진 임시 Member 객체 생성
            Member tempMember = new Member();
            tempMember.setUserId(userId);
            tempMember.setEmail(email);

            // validateDuplicateMember 메서드를 호출하여 중복 검사
            memberServiceImpl.validateDuplicateMember(tempMember);

            // 중복이 없는 경우
            response.put("exists", false);
        } catch (DuplicateUserIdException e) {
            // ID가 중복된 경우
            response.put("exists", true);
            response.put("type", "userId");
        } catch (DuplicateEmailException e) {
            // 이메일이 중복된 경우
            response.put("exists", true);
            response.put("type", "email");
        } catch (Exception e) {
            // 다른 예외가 발생한 경우
            response.put("exists", false);
            response.put("error", "서버에서 오류가 발생했습니다.");
        }

        return ResponseEntity.ok(response);
//        return ResponseEntity.ok(new HashMap<String, Object>() {{
//            put("exists", false);
//        }});

//        return response;
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


    //----------------------- //
    // 회원정보 조회
    //----------------------- //

    /*1. 회원 목록 조회*/
    @PreAuthorize("isAuthenticated") //로그인 인증 완료
    @GetMapping(value="/mypage/")
    public String findAll(Model model) {
        List<MemberDTO> memberDTOList = memberService.findAll();
        model.addAttribute("memberList",memberDTOList);
        log.info("회원목록 조회 ==> " + memberDTOList);

        return "members/myPage";
    }

    /*2. 마이페이지 조회*/
    @PreAuthorize("isAuthenticated") //로그인 인증 완료
    @GetMapping(value="/mypage")
    public String memberMyPageForm(Model model) {
        //로그인한 사용자 ID
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();

        //회원 정보 조회
        MemberDTO memberDTO = memberService.findMember(userId);
        model.addAttribute("member", memberDTO);

        return "members/myPage";
    }

    /*3. 회원정보 수정*/
    @PreAuthorize("isAuthenticated") //로그인 인증 완료
    @GetMapping(value = "/modify")
    public String updateMemberMyPageForm(@Valid @ModelAttribute
                                         MemberDTO memberDTO,
                                         BindingResult bindingResult,
                                         RedirectAttributes redirectAttributes) {
         //Valid 유효성 검사
         if(bindingResult.hasErrors()) {
             redirectAttributes.addFlashAttribute("errors",
                     bindingResult.getAllErrors());

             //수정 페이지 재요청
             return "members/modify";
         }
         //수정 서비스 요청
         memberService.modify(memberDTO);
         //1회 정보 유지
         redirectAttributes.addFlashAttribute("result", "modified");

         return "redirect:members/modify";
    }

     /*4. 회원정보 삭제*/
     @PreAuthorize("isAuthenticated") //로그인 인증 완료
     @GetMapping(value = "/remove")
     public String removeMember(@RequestParam String userId,
                                  RedirectAttributes redirectAttributes) {
//         try {
//             memberService.remove(userId);
//             redirectAttributes.addFlashAttribute("result","deleted");
//         } catch (Exception e) {
//             redirectAttributes.addFlashAttribute("error", "회원 삭제를 실패하여습니다.");
//         }

         return "redirect:members/myPage";
     }
}
