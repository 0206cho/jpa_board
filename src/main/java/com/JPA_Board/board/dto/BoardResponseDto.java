package com.JPA_Board.board.dto;

import com.JPA_Board.board.entity.Board;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 게시글 정보를 리턴할 응답 DTO 클래스
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardResponseDto {

    private Long id;            //  PK
    private String title;       //  제목
    private String content;     //  내용
    private String writer;      //  작성자
    private int hits;           //  조회 수
    private char deleteYn;      //  삭제 여부
    private LocalDateTime createdDate;      // 생성일
    private LocalDateTime modifiedDate;      // 수정일

    public BoardResponseDto(Board entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.writer = entity.getWriter();
        this.hits = entity.getHits();
        this.deleteYn = entity.getDeleteYn();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }
}
