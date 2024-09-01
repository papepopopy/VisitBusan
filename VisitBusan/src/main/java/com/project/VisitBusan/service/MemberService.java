package com.project.VisitBusan.service;

import com.project.VisitBusan.constant.Role;
import com.project.VisitBusan.dto.MemberDTO;
import com.project.VisitBusan.entity.Member;

public interface MemberService {
    public Member saveMember(MemberDTO memberDTO);

//    default Member dtoToEntity(MemberDTO memberDTO, PasswordEncoder passwordEncoder) {
    default Member dtoToEntity(MemberDTO memberDTO) {
        Member member = new Member();

        member.setName(memberDTO.getName());//닉네임
        member.setEmail(memberDTO.getEmail());//이메일
        member.setAddress(memberDTO.getAddress());//주소

        //비밀번호 암호화전
        member.setPassword(memberDTO.getPassword());

        //String password = passwordEncoder.encode(memberDTO.getPassword());
        //member,setPassword(password);

        //권환 설정시
        //member.addRole(Role.USER);

        return member;
    }
}
