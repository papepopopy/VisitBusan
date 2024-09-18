package com.project.VisitBusan.service;

import com.project.VisitBusan.dto.ProfileImageDTO;

public interface ProfileImageService {


//    void upload(ProfileImageDTO profileImageDTO, String userId);
//    ProfileImageDTO findImage(ProfileImageDTO profileImageDTO, String userId);
    void findImage(ProfileImageDTO profileImageDTO, String userId);

    //이미지 추가
    void addProfileImage(String userId, ProfileImageDTO profileImageDTO);

    //이미지 수정
    void updateProfileImage(String userId, ProfileImageDTO profileImageDTO);


//    String insertProfile(ProfileImageDTO profileImageDTO);
}