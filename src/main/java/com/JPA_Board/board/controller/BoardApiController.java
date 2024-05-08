package com.JPA_Board.board.controller;

import com.JPA_Board.board.dto.BoardRequestDto;
import com.JPA_Board.board.dto.BoardResponseDto;
import com.JPA_Board.board.model.BoardService;
import com.JPA_Board.exception.ErrorCode;
import com.JPA_Board.paging.CommonParams;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardApiController {
    @Autowired
    private final BoardService boardService;

    /*
    @GetMapping("/test")
    public String test() {
        //throw new RuntimeException("Holy! Exception ... ");
        throw new CustomException(ErrorCode.POST_NOT_FOUND);
    }
     */

    /**
     * 게시글 생성
     * @param params
     * @return board id
     */
    @PostMapping("/boards")
    public Long save(@RequestBody final BoardRequestDto params) {
        return boardService.save(params);
    }

    /**
     * 게시글 리스트 조회
     * @return board list
     */
    @GetMapping("/boards")
    public Map<String, Object> findAll(final CommonParams params) {
        return boardService.findAll(params);
    }

    /**
     * 게시글 수정
     * @param id
     * @param params
     * @return borad id
     */
    @PatchMapping("/boards/{id}")
    public Long save(@PathVariable final Long id, @RequestBody final BoardRequestDto params) {
        return boardService.update(id, params);
    }

    /**
     * 게시글 삭제
     * @param id
     * @return
     */
    @DeleteMapping("/boards/{id}")
    public Long delete(@PathVariable final Long id) {
        return boardService.delete(id);
    }

    /**
     * 게시글 상세 정보 조회
     * @param id
     * @return
     */
    @GetMapping("/boards/{id}")
    public BoardResponseDto findById(@PathVariable final Long id) {
        return boardService.findById(id);
    }
}
