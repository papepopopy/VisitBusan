package com.project.VisitBusan.service;

import com.project.VisitBusan.dto.MemberDTO;
import com.project.VisitBusan.entity.Member;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface MemberService {
    public Member saveMember(MemberDTO memberDTO);

    default Member dtoToEntity(MemberDTO memberDTO, PasswordEncoder passwordEncoder) {
        Member member = new Member();

        member.setEmail(memberDTO.getEmail());
        member.setUserId(memberDTO.getUserId());
        member.setName(memberDTO.getName());
        member.setAddress(memberDTO.getAddress());
        //security 제작 후
        String password = passwordEncoder.encode(memberDTO.getPassword());
        member.setPassword(password);

        return member;
    }
}
