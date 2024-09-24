package com.project.VisitBusan.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {

    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 8;

    private String type;  // 검색 종류 = t,c,w,tc,tw, twc
    private String keyword;
    private String bCategory;  // 카테고리 = information, schedule, review, festival ...
    private LocalDate bStartDate;
    private LocalDate bEndDate;

    // 키워드에 대한 type을 구분하여 배열구조로 반환
    public String[] getTypes() {
        if (type== null || type.isEmpty()) return null;

        return type.split("");
    }

    // 페이징 초기값 설정
    public Pageable getPageable(String... props) {
        return PageRequest.of(this.page-1, this.size, Sort.by(props).descending());
    }

    // 검색 조건 매개변수 설정과 페이지 조건 매개 변수 설정을 처리하는 문자열
    private String link;
    public String getLink() {
        if (link == null) {
            StringBuilder builder = new StringBuilder();
            builder.append("page="+this.page);
            builder.append("&size="+this.size);

            if (bCategory != null && bCategory.length()>0)  // 카테고리 추가
                builder.append("&bCategory="+bCategory);

            if (type != null && type.length()>0)  // 타입이 있으면 추가
                builder.append("&type="+type);

            if (keyword != null && keyword.length()>0) {  // 키워드가 있으면 추가
                try {
                    builder.append("&keyword=" + URLEncoder.encode(this.keyword, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            }

            if (bStartDate != null)  // 카테고리 추가
                builder.append("&bStartDate="+bStartDate);

            if (bEndDate != null)  // 카테고리 추가
                builder.append("&bEndDate="+bEndDate);

            // link = page=1&size10&type=twc&keyword=URLEncoder.Encode("홍길동")...
            link = builder.toString();
        }

        return link;
    }
}