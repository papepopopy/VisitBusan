package com.project.VisitBusan.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TagDTO {

    private Long id;

    @NotNull
    private Long board_id;  // 태그의 부모

    @NotEmpty
    private String tagName;

}
