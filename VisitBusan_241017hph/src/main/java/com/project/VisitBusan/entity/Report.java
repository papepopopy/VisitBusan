package com.project.VisitBusan.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

// Entity 정의 : 테이블에 적용될 구조설계 정의하여 테이블과 entity 1:1 맵핑
@Entity@Table(name="report")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Report {

    @Id  // 기본키로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;

    private String reportText;
    private String reporter;
    private String reporterId;

    // 엔티티가 생성되어 저장될 때 시간을 자동으로 저장
    @CreatedDate
    @Column(name="reg_date", updatable=false)
    private LocalDateTime regDate;

}
