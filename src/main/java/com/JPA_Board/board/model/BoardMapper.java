package com.JPA_Board.board.model;

import com.JPA_Board.board.dto.BoardResponseDto;
import com.JPA_Board.paging.CommonParams;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * description    : SQl을 작성할 Mapper XML과 매핑할 인터페이스
 * packageName    : com.JPA_Board.board.model
 * fileName        : BoardMapper
 * author         : kimminsol
 * date           : 2024/05/04
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/05/04        kimminsol       최초 생성
 */
@Mapper     // MyBatis는 @Mapper가 선언된 인터페이스와 연결된 XML Mapper에서 메서드명과 동일한 SQL을 찾아 쿼리 실행
public interface BoardMapper {
    // 게시글 수 조회
    // totalRecordCount와 연관, 검색 조건의 유무에 따라 테이블에서 데이터 수 카운팅
    // 카운팅 된 데이터 수 (totalRecordCount)를 기준으로 페이지 번호 계산
    int count(final CommonParams params);

    // 게시글 리스트 조회 (검색조건 유무 기준)
    List<BoardResponseDto> findAll(final CommonParams params);
}
