package com.project.VisitBusan.controller;

import com.project.VisitBusan.dto.ProfileImageDTO;
import com.project.VisitBusan.service.ProfileImageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Controller
@RequestMapping("/profile")
@Log4j2
@RequiredArgsConstructor  // 이걸 해줘야
public class ProfileImageController {

    //프로필 서비스 객체
    private final ProfileImageService profileImageService;

    @Value("${com.project.VisitBusan.path}")  // properties파일 설정한 path 값 읽어오기
    private String uploadPath;  // => "c:/javaStudy/upload 인식

    /*---------------------------------------------*/
    //파일 업로드 받기
    /*---------------------------------------------*/


    @PreAuthorize("isAuthenticated")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)  // 첨부파일 형식의 데이터를 받기로 설정
    public String upload(@RequestParam("uploadImgFile") MultipartFile file,
                         RedirectAttributes redirectAttributes) {
        try {
            // 현재 로그인 ID 가져오기
            String memberId = SecurityContextHolder.getContext().getAuthentication().getName();
            log.info("memberId ====> " + memberId);

            String fileName = file.getOriginalFilename();
            log.info("==> fileName : " + fileName);

            String uuid = UUID.randomUUID().toString();
            log.info("==> uuid : " + uuid);

            //파일 저장 폴더 경로
            Path savePath = Paths.get(uploadPath);

            //폴더가 존재하지 않으면 생성
            if (!Files.exists(savePath)) {
                Files.createDirectories(savePath);
            }

            //파일 저장 경로
            String uploadFileName = "pro_"+uuid + "_" + fileName;
            Path filePath = savePath.resolve(uploadFileName);

            //파일 저장
            file.transferTo(filePath);

            // ProfileImageDTO 에 데이터 설정
            ProfileImageDTO profileImageDTO = new ProfileImageDTO();
            profileImageDTO.setFileName(uploadFileName);
            profileImageDTO.setUuid(uuid);

            log.info("ProfileImageDTO fileName: {}, uuid: {}", profileImageDTO.getFileName(), profileImageDTO.getUuid());
            profileImageService.upload(profileImageDTO);

            redirectAttributes.addFlashAttribute("filePath", filePath.toString());
            log.info("filePath : {} ", filePath);
            return "redirect:/mypage";
        } catch (IOException e) {
            log.error("File upload failed", e);
            redirectAttributes.addFlashAttribute("errorMessage", "파일 업로드에 실패했습니다.");
        }
        return "redirect:/mypage";
    }

    @GetMapping("/view/{fileName}")
    public ResponseEntity<Resource> viewFileGET(@PathVariable("fileName") String fileName) {
        Path filePath = Paths.get(uploadPath).resolve(fileName);
        Resource resource = new FileSystemResource(filePath);

        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();

        try {
            headers.add(HttpHeaders.CONTENT_TYPE, Files.probeContentType(filePath));
        } catch (IOException e) {
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        }

        return ResponseEntity.ok().headers(headers).body(resource);
    }
}

