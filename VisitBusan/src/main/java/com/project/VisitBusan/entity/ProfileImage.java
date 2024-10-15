package com.project.VisitBusan.entity;

import com.project.VisitBusan.dto.ProfileImageDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="profile_image")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class ProfileImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //이미지 고유 ID

    private String uuid; //중복X
    private String fileName; //파일명

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @ToString.Exclude
    private Member member; //사용자 ID

    // 회원 설정
//    public void update(String uuid, String fileName) {
//        this.uuid = uuid;
//        this.fileName = fileName;
//    }

    // 회원 설정
//    public void setMember(Member member) {
//        this.member = member;
//    }

}
