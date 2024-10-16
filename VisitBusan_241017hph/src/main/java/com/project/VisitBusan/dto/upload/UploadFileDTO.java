package com.project.VisitBusan.dto.upload;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class UploadFileDTO {
    // 첨부파일을 받기 위해 MultipartFile 타입의 List 객체
    private List<MultipartFile> files;
}
