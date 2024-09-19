package com.project.VisitBusan.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FestivalInfoDTO {

    private Long id;

    private long board_id;

    private String contactNum;
    private String place;
    private String host;
    private String supervision;
    private String homepage;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
