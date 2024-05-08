package com.JPA_Board.paging;

import lombok.Getter;

/**
 * description    : 페이지네이션 처리용 클래스
 * packageName    : com.JPA_Board.paging
 * fileName        : Pagination
 * author         : kimminsol
 * date           : 2024/05/04
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/05/04        kimminsol       최초 생성
 */
@Getter
public class Pagination {

    // 전체 데이터 수 - 검색 조건이 없으면 전체 데이터 개수, 조건이 있으면 조건에 해당하는 데이터 개수가 됨
    private int totalRecordCount;       // 전체 데이터 수

    private int totalPageCount;         // 전체 페이지 수
    private int startPage;              // 첫 페이지 번호
    private int endPage;                // 끝 페이지 번호

    // LIMIT 시작 위치 - LIMIT 구문에서 파라미터 처음은 시작위치(몇 번째 데이터부터 조회할지) 지정, 두번째는 시작 limitStart를 기준으로 조회할 데이터 개수 지정
    // ex) 현재 번호 1, 페이장 출력 데이터 개수 10 : (1-1) * 10 = 0 => LIMIT 0,10
    // ex) 현재 번호 5, 페이장 출력 데이터 개수 10 : (5-1) * 10 = 40 => LIMIT 40,10
    private int limitStart;             // LIMIT 시작 위치

    private boolean existPrevPage;      // 이전 페이지 존재 여부 - startPage 1아니면 무조건 존재
    private boolean existNextPage;      // 다음 페이지 존재 여부

    public Pagination(int totalRecordCount, CommonParams params) {
        if (totalRecordCount > 0) {
            this.totalRecordCount = totalRecordCount;
            this.calculation(params);   // limitStart 값 계산되고, 그 값은 Pagination 객체에 담김
        }
    }

    private void calculation(CommonParams params) {

        // 전체 페이지 수 계산
        totalPageCount = ((totalRecordCount - 1) / params.getRecordPerPage()) + 1;

        // 현재 페이지 번호가 전체 페이지 수 보다 큰 경우, 현재 페이지 번호에 전체 페이지 수 저장
        if (params.getPage() > totalPageCount) {
            params.setPage(totalPageCount);
        }

        // 첫 페이지 번호 계산
        startPage = ((params.getPage() -1) / params.getPageSize()) * params.getPageSize() + 1;

        // 끝 페이지 번호 계산
        endPage = startPage + params.getPageSize() - 1;

        // 끝 페이지가 전체 페이지 수 보다 큰 경우, 끝 페이지 전체 페이지 수 저장
        if (endPage > totalPageCount) {
            endPage = totalPageCount;
        }

        // LIMIT 시작 위치 계산
        limitStart = (params.getPage() -1) * params.getRecordPerPage();

        // 이전 페이지 존재 여부 확인
        existPrevPage = startPage != 1;

        // 다음 페이지 존재 여부 확인
        existNextPage = (endPage * params.getRecordPerPage()) < totalRecordCount;
    }
}
