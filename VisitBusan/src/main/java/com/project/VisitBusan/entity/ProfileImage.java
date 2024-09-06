package com.project.VisitBusan.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="profile_image")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
@ToString(exclude="member")
public class ProfileImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid;
    private String fileName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public void update(String uuid, String fileName) {
        this.uuid = uuid;
        this.fileName = fileName;
    }

    // 회원 설정
//    public void setMember(Member member) {
//        this.member = member;
//    }

}
