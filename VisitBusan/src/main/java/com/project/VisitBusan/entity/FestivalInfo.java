package com.project.VisitBusan.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

// Entity 정의 : 테이블에 적용될 구조설계 정의하여 테이블과 entity 1:1 맵핑
@Entity
//@Table(name="festival_date")  // name을 따로 설정하지 않으면 자동으로 엔티티명과 동일한 이름의 테이블을 만듬
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FestivalInfo {

    @Id  // 기본키로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Board board;

    private String contactNum;
    private String place;
    private String host;
    private String supervision;
    private String homepage;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

}