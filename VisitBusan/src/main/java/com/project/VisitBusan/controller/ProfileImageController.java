package com.project.VisitBusan.controller;

import com.project.VisitBusan.dto.ProfileImageDTO;
import com.project.VisitBusan.dto.upload.UploadFileDTO;
import com.project.VisitBusan.dto.upload.UploadResultDTO;
import com.project.VisitBusan.service.ProfileImageService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/profile")
@Log4j2
@RequiredArgsConstructor  // 이걸 해줘야
public class ProfileImageController {


    private String uploadPath; //업로드 경로

    //프로필 서비스 객체
    private final ProfileImageService profileImageService;


    /*---------------------------------------------*/
    //파일 업로드 받기
    /*---------------------------------------------*/
    @PostMapping(value = "/upload")
    public String upload(@ModelAttribute ProfileImageDTO profileImageDTO,
                         Authentication authentication) {
        log.info("==> profileImageDTO : " + profileImageDTO);
        return "redirect:/profile/upload";
    }

//    @Operation(summary="Replies POST", description="POST방식으로 등록")
//    @PostMapping(value="/upload")// 전송받은 data 종류 명시
//    public String upload(
//            @Valid @RequestBody ProfileImageDTO profileImageDTO,
//            BindingResult bindingResult // 객체값 검증
//    ) throws BindException {
//
//        log.info("=> profileImageDTO: "+profileImageDTO);
//
//        // 에러가 존재하면 BindException 예외 발생
//        if (bindingResult.hasErrors()){
//            throw new BindException(bindingResult);
//        }
//
////        String uuid = profileImageService.upload(profileImageDTO);
////
////        Map<String, String> resultMap = new HashMap<>();
////
////        return resultMap;
//        return null;
//    }


}

