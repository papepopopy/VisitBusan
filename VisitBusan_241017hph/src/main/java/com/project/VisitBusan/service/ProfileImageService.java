package com.project.VisitBusan.service;

import com.project.VisitBusan.dto.ProfileImageDTO;

public interface ProfileImageService {


    void findImage(ProfileImageDTO profileImageDTO, String memberId);

    //이미지 수정
    void upload(ProfileImageDTO profileImageDTO);


//    String insertProfile(ProfileImageDTO profileImageDTO);
}