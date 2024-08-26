package com.project.VisitBusan.repository.search;

import com.project.VisitBusan.entity.Board;
import com.project.VisitBusan.entity.QBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

@Log4j2
// 구현 클래스 : 반드시 '인터페이스이름+impl" 표현해야 한다.
public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

    // 인자가 있을 경우
    /*
    public BoardSearchImpl(Class<?> domainClass) {
    super(domainClass);
    */

    public BoardSearchImpl() {
        super(Board.class);
    }


    @Override
    public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {

        QBoard board = QBoard.board;

        JPQLQuery<Board> query = from(board);

        if((types != null && types.length>0) && keyword != null) {

            // 조건문을 작성하는 클래스
            BooleanBuilder booleanBuilder = new BooleanBuilder();

            // 조건문 추가
            for (String type : types) {
                switch (type) {
                    case "t":
                        booleanBuilder.or(board.title.contains(keyword)); break;
                    case "c":
                        booleanBuilder.or(board.content.contains(keyword)); break;
                    case "w":
                        booleanBuilder.or(board.writer.contains(keyword)); break;
                }
            } // end for

            query.where(booleanBuilder);

        } // end if

        // paging 추가
        this.getQuerydsl().applyPagination(pageable, query);

        // query 실행
        List<Board> list = query.fetch();
        long count = query.fetchCount();

        return new PageImpl<>(list, pageable, count);

    } // end method

} // end class
