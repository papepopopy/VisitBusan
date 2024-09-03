package com.project.VisitBusan.entity;

import com.project.VisitBusan.constant.Role;
import com.project.VisitBusan.dto.MemberDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="member")
@Getter@Setter
@ToString//(exclude = "roleSet")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {

    @Id  // 기본키로 지정
    @Column(name="member_id")  // 테이블 이름(필드명) 사용자 지정  없으면 그냥 동일하게 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String userId;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    @Column(unique = true, nullable = false)  //  중복 허용 x
    private String email;
    private String address;

    @OneToOne(fetch = FetchType.LAZY,
              orphanRemoval = true)  // 고아객체 발생시 자동 삭제
    private ProfileImage profileImage;


    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Role> roleSet = new HashSet<>();

    // 비밀번호 변경
    public void changePassword(String password) {
        this.password = password;
    }
    // 이메일 변경
    public void changeEmail(String email) {
        this.email = email;
    }
    // 역할 추가
    public void addRole(Role role) {
        this.roleSet.add(role);
    }
    // 모든 역할 제거
    public void clearRoles() {
        this.roleSet.clear();
    }

    // 1.엔티티 메서드 방법 : createMember():  dto -> entity
    public static Member createMember(MemberDTO memberDTO,
                                      PasswordEncoder passwordEncoder){
        Member member = new Member();

        member.setUserId(memberDTO.getUserId());
        member.setName(memberDTO.getName());
        member.setEmail(memberDTO.getEmail());
        member.setAddress(memberDTO.getAddress());

        // 비밀번호 -> 암호화 작업
        String password = passwordEncoder.encode(memberDTO.getPassword());
        member.setPassword(password);
        // member.setRole(Role.USER); // Set<Role> 사용전전
        member.addRole(Role.USER);    // Set<Role> 사용후

        return member;
    }
}
