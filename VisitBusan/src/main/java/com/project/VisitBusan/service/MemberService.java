package com.project.VisitBusan.service;

import com.project.VisitBusan.constant.Role;
import com.project.VisitBusan.dto.MemberDTO;
import com.project.VisitBusan.entity.Member;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface MemberService {
    public Member saveMember(MemberDTO memberDTO);

    // dtoToEntity 메서드는 필요에 따라 사용되거나 제거될 수 있습니다.
    default Member dtoToEntity(MemberDTO memberDTO, PasswordEncoder passwordEncoder) {
        Member member = new Member();

        member.setName(memberDTO.getName());//닉네임
        member.setEmail(memberDTO.getEmail());//이메일
        member.setAddress(memberDTO.getAddress());//주소

        //비밀번호 암호화 전
        member.setPassword(memberDTO.getPassword());

        String password = passwordEncoder.encode(memberDTO.getPassword());
        member.setPassword(password);

        //권환 설정시
        member.addRole(Role.USER);

        return member;
    }

}
