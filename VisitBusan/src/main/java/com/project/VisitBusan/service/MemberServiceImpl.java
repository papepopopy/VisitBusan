package com.project.VisitBusan.service;

import com.project.VisitBusan.dto.MemberDTO;
import com.project.VisitBusan.entity.Member;
import com.project.VisitBusan.exception.DuplicateEmailException;
import com.project.VisitBusan.exception.DuplicateUserIdException;
import com.project.VisitBusan.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    //사용자 추가
    @Override
    public Member saveMember(MemberDTO memberDTO){

        // 1. dto -> entity: Member Entity createMember() 메서드 활용
        Member member = Member.createMember(memberDTO, passwordEncoder);

        // 회원 중복 체크
        validateDuplicateMember(member);

        log.info("===> 중복 이메일 없음");
        // 중복된 이메일 없을 경우 저장(반영)
        return memberRepository.save(member);
    }


    //회원중복체크
    @Override
    public void validateDuplicateMember(Member member){
        //이메일 체크
        Member findMemberByEmail = memberRepository.findByEmail(member.getEmail());
        if (findMemberByEmail != null) {
            log.info("이미 가입된 회원 입니다."+member.getEmail());
            throw new DuplicateEmailException("이미 가입된 회원 입니다.");
        }

        //ID 중복 체크
        Optional<Member> findMemberByUserId = memberRepository.findByUserId(member.getUserId());
        if (findMemberByUserId.isPresent()) {
            log.info("이미 가입된 ID 입니다."+ member.getUserId());
            throw new DuplicateUserIdException("이미 사용 중인 ID 입니다.");
        }
    }

    //로그인
    @Override
    public Member login(String userId, String password) {
        Optional<Member> optionalMember = memberRepository.findByUserId(userId);

        if(optionalMember.isPresent()) {
            //작성한 ID와 PW 일치시 로그인
            Member member = optionalMember.get();
            if (passwordEncoder.matches(password, member.getPassword())) {
                return member;
            } else {
                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
            }
        } else {
            throw new IllegalArgumentException("존재하지 않는 사용자 ID 입니다.");
        }
    }

    //회원 조회
    @Override
    public MemberDTO findMember(String userId) {
        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다."));


        return MemberDTO.toMemberDTO(member);
    }

    //회원 수정
    @Override
    public Member modify(MemberDTO memberDTO) {
        //수정 Id
        Optional<Member> result = memberRepository.findByUserId(memberDTO.getUserId());
        Member member = result.orElseThrow();

        // 비밀번호가 변경된 경우에만 암호화
        if (!passwordEncoder.matches(memberDTO.getPassword(), member.getPassword())) {
            member.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
        }

        //dto 로 변경
        member.change(
                memberDTO.getName(),
                memberDTO.getEmail(),
                memberDTO.getAddress(),
                memberDTO.getPassword()
//                memberDTO.getProfileImage(),
//                memberDTO.getProfileText()
        );

        //저장하기
        return memberRepository.save(member);
    }

    //회원 삭제
//    @Override
//    public void remove(MemberDTO memberDTO) {
//        //회원이 삭제되어지기 전에 작성한 댓글과 게시물이 사라져야할지
//        memberRepository.deleteByUserId(memberDTO.getUserId());
//    }

    //전체 조회
    @Override
    public List<MemberDTO> findAll() {
        List<Member> memberList = memberRepository.findAll(); //member 조회
        List<MemberDTO> memberDTOList = new ArrayList<>();  //memberDTO 저장리스트 생성

        for (Member member:memberList) { //엔티티가 DTO로 변환
            MemberDTO memberDTO = MemberDTO.toMemberDTO(member);
            memberDTOList.add(memberDTO);
        }

        return memberDTOList;
    }

}
