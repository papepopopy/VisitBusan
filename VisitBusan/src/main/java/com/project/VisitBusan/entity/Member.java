package com.project.VisitBusan.entity;

import com.project.VisitBusan.constant.Role;
import jakarta.persistence.*;
import lombok.*;

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

    private String name;
    @Column(unique = true)  //  중복 허용 x
    private String email;
    private String password;
    private String address;

    @OneToOne(fetch = FetchType.LAZY,
              orphanRemoval = true)  // 고아객체 발생시 자동 삭제
    private ProfileImage profileImage;


    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Role> roleSet = new HashSet<>();

    public void changePassword(String password) {
        this.password = password;
    }
    public void changeEmail(String email) {
        this.email = email;
    }
    public void addRole(Role role) {
        this.roleSet.add(role);
    }
    public void clearRoles() {
        this.roleSet.clear();
    }
}
