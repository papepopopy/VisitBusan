package com.project.VisitBusan.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
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

    private LocalDate startDate;
    private LocalDate endDate;

}
