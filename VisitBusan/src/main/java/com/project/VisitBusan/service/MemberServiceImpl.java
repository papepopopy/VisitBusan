package com.project.VisitBusan.service;

import com.project.VisitBusan.dto.MemberDTO;
import com.project.VisitBusan.entity.Member;
import com.project.VisitBusan.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    //private final PasswordEncoder passwordEncoder; 비밀번호 암호화 추가시

    //사용자 추가
    public Member saveMember(MemberDTO memberDTO){

        //Member member = dtoToEntity(memberDTO, passwordEncoder );
        Member member = dtoToEntity(memberDTO);

        //회원중복체크(email 기준)
        validateDuplicateMember(member);

        // 중복된 이메일 없을 경우 저장(반영)
        return memberRepository.save(member);
    }

    //회원중복체크
    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if (findMember != null) throw new IllegalStateException("이미 가입된 회원 입니다.");
    }

}
