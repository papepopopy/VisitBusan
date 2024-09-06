package com.project.VisitBusan.service;

import com.project.VisitBusan.constant.Role;
import com.project.VisitBusan.dto.MemberDTO;
import com.project.VisitBusan.entity.Member;
import com.project.VisitBusan.repository.MemberRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@Commit
@Transactional(readOnly = false)
//@TestPropertySource(locations = {"classpath: application-test.properties"})
@Log4j2
@SpringBootTest
class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("Member Entity 생성")
    public void insertMember() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            Member member = Member.builder()
                    .userId(i +"userId5")
                    .name("홍길동2" + i)
                    .email("test5" + i + "@email.com5")
                    .password(passwordEncoder.encode("1111"))
                    .address("부산시 진구")
                    .build();

            // 1~100까지는 USER권한 추가
            member.addRole(Role.USER);

            log.info("==> member:" + member);
            Member savedMember = memberRepository.save(member);

        });
        log.info("Test 끝");
    }
    @Test
    @DisplayName("회원 조회 Test")
    public void findMember() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            String userId = i +"userId";
            Optional<Member> memberOptional = memberRepository.findByUserId(userId);
            log.info("test start ==> ");
            if (memberOptional.isPresent()) {
                Member member = memberOptional.get();
                //상세 정보 출력
                log.info("==> userId: " + member.getUserId());
                log.info("==> name: " + member.getName());
                log.info("==> email: " + member.getEmail());
                log.info("==> password: " + member.getPassword());
                log.info("==> address: " + member.getAddress());
                log.info("==> profileText: " + member.getProfileText());
            } else {
                log.info("아이디를 찾을수 없습니다. ==> " + userId);
            }

//            member.getRoleSet().forEach(role -> log.info("=> member role :" + role.name()));
        });
    }
}


