package com.project.VisitBusan.service;

import com.project.VisitBusan.constant.Role;
import com.project.VisitBusan.dto.MemberDTO;
import com.project.VisitBusan.entity.Member;
import com.project.VisitBusan.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public interface MemberService {
    //사용자 추가
    Member saveMember(MemberDTO memberDTO);
    //회원중복체크
    void validateDuplicateMember(Member member);
    //로그인
    Member login(String userId, String password);
    //회원 조회
    MemberDTO findMember(String userId);
    //전체 조회
    List<MemberDTO> findAll();

    
    // dto -> entity 변환
    default Member dtoToEntity(MemberDTO memberDTO,
                               PasswordEncoder passwordEncoder) {
        Member member = Member.builder()
                .userId(memberDTO.getName())
                .userId(memberDTO.getUserId())
                .userId(memberDTO.getEmail())
                .userId(memberDTO.getAddress())
                .password(passwordEncoder.encode(memberDTO.getPassword()))
                .build();

        //권한 설정시
        member.addRole(Role.USER);

        return member;
    }

    //entity -> dto 변환 (조회용)
//    default MemberDTO entityToDto(Member member) {
//        MemberDTO memberDTO = MemberDTO.builder()
//                .userId(member.getUserId())
//                .name(member.getName())
//                .email(member.getEmail())
//                .password(member.getPassword())
//                .address(member.getAddress())
//                .profileText(member.getProfileText())
//                .build();
//        // 프로필 이미지 (list -> string 변환)
//        if (member.getProfileImage() != null) {
//            List<String> profileImage = member.getProfileImage().stream()
//                    .sorted()
//                    .map(image -> image.getUuid() + "_" + image.getFileName())
//                    .collect(Collectors.toList());
//
//            // 프로필 이미지 리스트 설정
//            memberDTO.setProfileImage(profileImage);
//        }
////        //프로필 이미지 (list -> string 변환)
////        if(member.getProfileImage() != null) {
////            ProfileImage profileImage = member.getProfileImage();
////            String profileImageString = profileImage.getUuid() + "_" + profileImage.getFileName();
////            memberDTO.setProfileImage(profileImageString); //List에 이미지 추가
////        }
//
//        return memberDTO;
//    }

}
