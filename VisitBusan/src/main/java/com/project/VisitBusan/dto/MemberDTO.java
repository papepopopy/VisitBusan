package com.project.VisitBusan.dto;

import com.project.VisitBusan.constant.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor  // 데이터가 없는 상태로 객체 생성함
@Builder
public class MemberDTO {

    @NotBlank(message = "아이디는 필수입니다~")
    private String userId;
    @NotBlank(message = "이름은 필수입니다~")  // Null 체크 및 문자열의 경우 길이 0인지 및 빈문자열("") 검사
    private String name;
    @NotBlank(message = "이메일은 필수입니다~")  // Null 체크 및 문자열의 경우 길이 0인지 및 빈문자열("") 검사
    @Email(message = "이메일 형식으로 입력하세요~")
    private String email;
    @NotBlank(message = "비밀번호는 필수입니다~")
    @Length(min=4, max=16, message = "비밀번호는 4자 이상 16자 이하로 입력하세요~")
    private String password;
    @NotBlank(message = "주소는 필수입니다~")
    private String address;

    // 사용자 정의 User 객체(AuthMemberDTO) 생성해서 사용
    // Role data 임시 저장 용
    private Role role;

}
