package com.project.VisitBusan.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Alarm {

    @Id  // 기본키로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    private Reply reply;

    @OneToOne(fetch = FetchType.LAZY)
    private SubReply subReply;

    @OneToOne(fetch = FetchType.LAZY)
    private BoardLike boardLike;

    @OneToOne(fetch = FetchType.LAZY)
    private ReplyLike replyLike;

    @OneToOne(fetch = FetchType.LAZY)
    private SubReplyLike subReplyLike;

    @OneToOne(fetch = FetchType.LAZY)
    private Warning warning;

    @CreatedDate
    @Column(name="reg_date", updatable=false)
    private LocalDateTime regDate;

}
