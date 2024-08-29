package com.project.VisitBusan.repository;

import com.project.VisitBusan.entity.Board;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.UUID;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Log4j2
//@TestPropertySource(locations="classpath:application-test.properties")  // 환경설정파일 따로 지정
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private ReplyRepository replyRepository;

    @Test
    @DisplayName("보드 생성 테스트")
    void createBoardTest() {

        // 1. 더미 생성
//        IntStream.rangeClosed(1,3).forEach(i-> {
//            Board board = Board.builder()
//                    .category("festival")
//                    .title("title"+i)
//                    .content("content"+i)
//                    .writer("user"+(i%10))
//                    .writerId("user"+(i%10))
//                    .build();
//            Board result = boardRepository.save(board);
//            log.info("==> board id: "+result.getId());
//        });

        // 2. 이미지 포함 더미 생성
        IntStream.rangeClosed(1,5).forEach(i-> {
            Board board = Board.builder()
                    .category("festival")
                    .title("title"+i)
                    .content("content"+i)
                    .writer("user"+(i%10))
                    .writerId("user"+(i%10))
                    .build();

            // 새로운 첨부파일 추가
            IntStream.rangeClosed(1,3).forEach(j-> {
                // 부모객체 내에서 하위객체 생성
                // board객체에서 BoardImage 객체를 생성
                board.addFile(UUID.randomUUID().toString(), "file"+i+"-"+j+".jpg");
            });

            Board result = boardRepository.save(board);
            log.info("==> Id: "+result.getId());

        });

    } // end test

    @Test
    void findByIdWithFiles() {
    }
}