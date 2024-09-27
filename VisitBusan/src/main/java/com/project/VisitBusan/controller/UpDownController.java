package com.project.VisitBusan.controller;

import com.project.VisitBusan.dto.upload.UploadFileDTO;
import com.project.VisitBusan.dto.upload.UploadResultDTO;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@Log4j2
public class UpDownController {

    @Value("${com.project.VisitBusan.path}")  // properties파일 설정한 path 값 읽어오기
    private String uploadPath;  // => "c:/javaStudy/upload 인식

    @PostMapping(value="/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)  // 첨부파일 형식의 데이터를 받기로 설정
    public List<UploadResultDTO> upload(UploadFileDTO uploadFileDTO) {  // 클라이언트로부터 받은 첨부파일 매개 변수
        // 1.
        log.info("==> uploadFileDTO : "+uploadFileDTO);  // 클라이언트로부터 받은 첨부파일을 담고 있는 객체

        // 2.
        if(uploadFileDTO.getFiles() != null) {  // uploadFileDTO List 구조의 객체 첨부파일이 있으면

            List<UploadResultDTO> list = new ArrayList<>();

            uploadFileDTO.getFiles().forEach(multipartFile-> {

                String fileName = multipartFile.getOriginalFilename();
                log.info("==> multipartFile  : "+fileName);

                // 3. 첨부파일 저장하기
                String uuid = UUID.randomUUID().toString();  // 중복을 허용하지 않는 난수 (16진코드)
                log.info("==> uuid: "+uuid);

                // "c:/javaStudy/upload"+uuid+"_"+"첨부파일 이름"
                Path savePath = Paths.get(uploadPath, uuid+"==vb=="+fileName);

                // 5. 파일 형식 파악(이미지파일)
                boolean image = false;

                // 4. 지정된 위치에 파일 저장
                try {
                    multipartFile.transferTo(savePath);

                    // 이미지 파일이면 썸네일 생성 (용량을 줄인 파일 생성)
                    if (Files.probeContentType(savePath).startsWith("image")) {
                        image = true;

                        // "c:/javaStudy/upload"+"s_"+uuid+"_"+"첨부파일 이름"
                        File thumbFile = new File(uploadPath, "s_"+uuid+"==vb=="+fileName);
                        Thumbnailator.createThumbnail(savePath.toFile(), thumbFile, 400, 400);
                    }

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                // 파일 저장(업로드) 후 파일에 관련 정보를 List 객체에 저장
                list.add(UploadResultDTO.builder()
                        .uuid(uuid)          // uuid값
                        .fileName(fileName)  // 파일이름
                        .img(image)          // 이미지 여부판단
                        .build());

            });  // end forEach

            log.info("==>  업로드된 파일 정보: "+list);
            return list;  // 첨부파일이 있으면 UploadResultDTO 타입의 list 객체 반환

        }  // end if (첨부파일이 있을 경우)

        return null;  // 첨부파일이 없으면 null 반환

    }

    // image view
//    @Operation(summary="View 파일", description="GET방식으로 첨부파일 조회")
    @GetMapping(value="/view/{fileName}")
    public ResponseEntity<Resource> viewFileGET(@PathVariable("fileName") String fileName) {  // 클라이언트로부터 받은 첨부파일 매개 변수

        Resource resource = new FileSystemResource(uploadPath+File.separator+fileName);
        String resourceName = resource.getFilename();

        HttpHeaders headers = new HttpHeaders();

        try {
            headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok().headers(headers).body(resource);
    }

    // image remove: 첨부파일 삭제
//    @Operation(summary="remove 파일", description="DELETE방식으로 첨부파일 삭제")
//    @GetMapping(value="/remove/{fileName}")  // test용
    @DeleteMapping(value="/remove/{fileName}")
    public Map<String, Boolean> removeFile(@PathVariable String fileName) {

        Resource resource = new FileSystemResource(uploadPath+File.separator+fileName);
        // "C:\\javaStudy\\upload"+"\\"+"a.jpg" **
        String resourcename = resource.getFilename();

        Map<String, Boolean> resultMap = new HashMap<>();
        boolean removed = false;

        try {
            String contentType = Files.probeContentType(resource.getFile().toPath());
            removed = resource.getFile().delete();

            // 썸네일이 존재하면
            log.info("==> contentType: "+contentType);
            if(contentType.startsWith("image")) {
                File thumbnilfile = new File((uploadPath+File.separator+"s_"+fileName));
                thumbnilfile.delete();
            }

        } catch(Exception e) {
            log.error(e.getMessage());
        }

        // 삭제후 응답할 정보
        resultMap.put("result", removed);
        return resultMap;
    }

}
