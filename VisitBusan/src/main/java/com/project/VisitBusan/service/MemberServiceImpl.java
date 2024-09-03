package com.project.VisitBusan.service;

import com.project.VisitBusan.dto.MemberDTO;
import com.project.VisitBusan.entity.Member;
import com.project.VisitBusan.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    //사용자 추가
    public Member saveMember(MemberDTO memberDTO){

        // 1. dto -> entity: Member Entity createMember() 메서드 활용
        Member member = Member.createMember(memberDTO, passwordEncoder);

        // 2. dto -> entity: MeberService 인터페이스 활용
        //Member member = dtoToEntity(memberDTO);

        // 회원 중복 체크(email 기준) 메서드 호출
        validateDuplicateMember(member);

//        Member findMember = memberRepository.findByEmail(member.getEmail());
//        if (findMember != null) throw new IllegalStateException("이미 가입된 회원 입니다.");

        // 중복된 이메일 없을 경우 저장(반영)
        return memberRepository.save(member);
    }


    //회원중복체크
    private void validateDuplicateMember(Member member){
        //이메일 체크
        // Member Entity Email 기존에 Entity에 있는 유무 체크
        Member findMemberByEmail = memberRepository.findByEmail(member.getEmail());
        if (findMemberByEmail != null) throw new IllegalStateException("이미 가입된 회원 입니다.");

        //ID 중복 체크
        Optional<Member> findMemberByUserId = memberRepository.findByUserId(member.getUserId());
        if (findMemberByUserId.isPresent()) {
            throw new IllegalStateException("이미 사용 중인 ID 입니다.");
        }
    }

}
