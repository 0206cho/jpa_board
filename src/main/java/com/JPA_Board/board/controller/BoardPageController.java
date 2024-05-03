package com.JPA_Board.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// 게시글 페이지 매핑
@Controller
@RequestMapping("/board")
public class BoardPageController {

    /**
     * 게시글 리스트 페이지
     */
    @GetMapping("/list")
    public String openBoardList() {
        return "board/list";
    }

    /**
     * 게시글 동륵 페이지
     */
    @GetMapping("/write")
    public String openBoardWrite() {
        return "/board/write";
    }
}
