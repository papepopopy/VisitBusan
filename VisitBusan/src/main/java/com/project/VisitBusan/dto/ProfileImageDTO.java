package com.project.VisitBusan.dto;

import com.project.VisitBusan.entity.Member;
import com.project.VisitBusan.entity.ProfileImage;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileImageDTO {
    private Long id;

    @NotNull
    private String uuid;

    @NotEmpty
    private String fileName;    // 파일이름
    @NotEmpty
    private int ord;            // 순번

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
//    private Member member; //이미 dto에 포함되어있음
}
