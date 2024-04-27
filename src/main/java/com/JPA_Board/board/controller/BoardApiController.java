package com.JPA_Board.board.controller;

import com.JPA_Board.exception.CustomException;
import com.JPA_Board.exception.ErrorCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BoardApiController {

    @GetMapping("/test")
    public String test() {
        //throw new RuntimeException("Holy! Exception ... ");
        throw new CustomException(ErrorCode.POST_NOT_FOUND);
    }
}
