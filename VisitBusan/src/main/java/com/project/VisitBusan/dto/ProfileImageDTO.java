package com.project.VisitBusan.dto;

import com.project.VisitBusan.entity.Member;
import com.project.VisitBusan.entity.ProfileImage;
import lombok.*;
import lombok.extern.log4j.Log4j2;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileImageDTO {

    private String uuid;        // 중복되지 않는 랜덤한 이름

    private String fileName;    // 파일이름
    private int ord;            // 순번

//    private Member member; //이미 dto에 포함되어있음

    public static ProfileImageDTO toProfileImageDTO(ProfileImage profileImage) {
        ProfileImageDTO dto = new ProfileImageDTO();
        dto.setFileName(profileImage.getFileName());
        return dto;
    }
    public static ProfileImage toEntity(ProfileImageDTO dto) {
        ProfileImage profileImage = new ProfileImage();
        profileImage.setFileName(dto.getFileName());
        return profileImage;
    }
}
