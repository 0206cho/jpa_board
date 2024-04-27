package com.JPA_Board.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

// Custom 예외 처리용 Exception 클래스 생성
@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {

    private final ErrorCode errorCode;
}
