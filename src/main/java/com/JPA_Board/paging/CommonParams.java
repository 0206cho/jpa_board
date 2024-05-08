package com.JPA_Board.paging;

import lombok.Getter;
import lombok.Setter;

/**
 * description    : 페이징과 검색 처리에 필수적으로 전달 받아야하는 파라미터 관리
 * packageName    : com.JPA_Board.paging
 * fileName        : CommonParams
 * author         : kimminsol
 * date           : 2024/05/04
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/05/04        kimminsol       최초 생성
 */

@Getter
@Setter
public class CommonParams {

    private int page;                    // 현재 페이지 번호
    private int recordPerPage;           // 페이지당 출력할 데이터 개수
    private int pageSize;                // 화면 하단에 출력할 페이지 개수
    private String keyword;              // 검색 키워드

    // 검색 유형 (keyword와 함께 사용. ex) 제목, 내용, 작성자 중 1 or 전부를 기준으로 LIKE 검색 시 사용)
    private String searchType;

    private Pagination pagination;      // 페이지네이션 정보
}
