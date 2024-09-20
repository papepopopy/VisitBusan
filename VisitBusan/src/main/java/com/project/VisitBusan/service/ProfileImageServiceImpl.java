package com.project.VisitBusan.service;

import com.project.VisitBusan.dto.MemberDTO;
import com.project.VisitBusan.dto.ProfileImageDTO;
import com.project.VisitBusan.entity.Member;
import com.project.VisitBusan.entity.ProfileImage;
import com.project.VisitBusan.repository.MemberRepository;
import com.project.VisitBusan.repository.ProfileImageRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Id;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class ProfileImageServiceImpl implements ProfileImageService {
    private final MemberRepository memberRepository;
    private final ProfileImageRepository profileImageRepository;

    @Override
    public void findImage(ProfileImageDTO profileImageDTO, String userId) {
        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("계정이 존재하지 않습니다."));

        if(member.getProfileImage() == null) {
            profileImageDTO.setFileName("profile_img.jpg");
        } else {
            profileImageDTO.setFileName(member.getProfileImage().getFileName());
        }
    }

    //이미지 수정
    @Override
    public void upload(ProfileImageDTO profileImageDTO) {
        //1. 수정이미지 존재 확인
        String fileName = profileImageDTO.getFileName();
        if (fileName == null || fileName.isEmpty()) {
            throw new IllegalArgumentException("파일이 비어있습니다.");
        }
        // 현재 로그인한 사용자 ID 가져오기
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();
        Member member = memberRepository.findByUserId(memberId)
                .orElseThrow(() -> new UsernameNotFoundException("계정이 존재하지 않습니다."));

        //3. 기존 이미지 조회s
        ProfileImage originalImage = profileImageRepository.findByMember(member)
                .orElse(null);

        //기존 이미지 삭제
        if(originalImage != null) {
            profileImageRepository.delete(originalImage); //기존 이미지 삭제
            profileImageRepository.flush(); //반영
        }

        //4. 파일 저장 처리(새로운 파일명 생성)
//        String uuid = UUID.randomUUID().toString();


        //새 이미지 저장
        ProfileImage newProfileImage = new ProfileImage();
        newProfileImage.setFileName(fileName);
//        newProfileImage.setUuid(profileImageDTO.getUuid());
        newProfileImage.setMember(member);

        if(profileImageDTO.getId() != null) {
            newProfileImage.setId(profileImageDTO.getId());
        }

        //저장
        profileImageRepository.save(newProfileImage);
    }
}
