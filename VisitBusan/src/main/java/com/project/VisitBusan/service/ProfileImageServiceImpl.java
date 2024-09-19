package com.project.VisitBusan.service;

import com.project.VisitBusan.dto.ProfileImageDTO;
import com.project.VisitBusan.entity.Member;
import com.project.VisitBusan.entity.ProfileImage;
import com.project.VisitBusan.repository.MemberRepository;
import com.project.VisitBusan.repository.ProfileImageRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.File;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class ProfileImageServiceImpl implements ProfileImageService {
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;
    private final ProfileImageRepository profileImageRepository;

    @Value("${com.project.VisitBusan.path}")  // properties파일 설정한 path 값 읽어오기
    private String uploadPath;

//    @Override
//    public void upload(ProfileImageDTO profileImageDTO, Member member) {
//        memberRepository.findByUserId(member.getUserId()).orElseThrow(() -> new UsernameNotFoundException("아이디가 존재하지 않습니다."));
//        String fileName = profileImageDTO.getFileName();
//
//        UUID uuid = UUID.randomUUID();
//        String imageFileName = uuid + "_" + fileName;
//        File destinationFile = new File(uploadPath + imageFileName);
//
//        try {
//            ProfileImage image = profileImageRepository.findByMember(member);
//            if(image != null) {
////                image.updateUrl("/profileImages/" + imageFileName);
//            } else {
//                image = Image.builder()
//                        .member(member)
////                        .fileName()
//                        .build();
//            }
//            ProfileImageRepository.save(image);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Override
    public void findImage(ProfileImageDTO profileImageDTO, String userId) {
        Member member = memberRepository.findByUserId(userId).orElseThrow(() -> new UsernameNotFoundException("계정이 존재하지 않습니다."));
//        MultipartFile file = profileImageDTO.getFile();

        UUID uuid = UUID.randomUUID();
        String defaultImageUrl = "/profileImages/profile_img.jpg";

//        if(image == null) {
//            return ProfileImageRepository.builer().url(defaultImageUrl).build();
//        } else {
//            return ProfileImageDTO.builder().url(image.getUrl()).build();
//        }
//        return profileImageDTO;
        memberRepository.save(member);
    }

    /*---------------------------------------------*/
    //DB에 정보 저장
    /*---------------------------------------------*/
    @Override
    public void addProfileImage(String userId, ProfileImageDTO profileImageDTO) {
        //회원조회
        //Member member = memberRepository.findByUserId(userId).orElseThrow(() -> new EntityNotFoundException("해당 회원을 찾을 수 없습니다."));
        //엔티티 변환 후 프로필 생성
//        ProfileImage profileImage = new ProfileImage(profileImageDTO.getUserId(),
//                                                    profileImageDTO.getFileName(),
//                                                    profileImageDTO.getUploadPath());
//        //저장
//        profileImageRepository.save(profileImage);
    }

    //이미지 수정
    @Override
    public void updateProfileImage(String userId, ProfileImageDTO profileImageDTO) {
        //회원조회
        Member member = memberRepository.findByUserId(userId).orElseThrow(() -> new EntityNotFoundException("해당 회원을 찾을 수 없습니다."));
        //기존이미지 들고오기
        ProfileImage profileImage = member.getProfileImage();
        //null 상태
        if (profileImage == null) {
            profileImage = new ProfileImage();
        }
        //프로필 이미지명 업데이트
        profileImage.setFileName(profileImageDTO.getFileName());
        //새로운 이미지 설정
        member.setProfileImage(profileImage);
        //저장
        memberRepository.save(member);
    }



}
