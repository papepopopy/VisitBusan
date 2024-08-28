package com.project.VisitBusan.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity@Table(name="profile_image")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude="member")
public class ProfileImage implements Comparable<ProfileImage> {

    @Id
    private String uuid;        // 중복되지 않는 랜덤한 이름

    private String fileName;    // 파일이름
    private int ord;            // 순번

    @OneToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name="member_id")  // 생략시 자동 설정
    private Member member;

    @Override
    public int compareTo(ProfileImage other) {

        // 정렬 항목이 숫자일 경우
        return this.ord - other.ord;  // 오름차순
//        return other.ord - this.ord;  // 내림차순
    }

    public void changeMember(Member member) {
        this.member = member;
    }
}
