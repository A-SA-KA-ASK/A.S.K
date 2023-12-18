package com.example.askBackend.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    NOT_FOUND_USER_ID(HttpStatus.NOT_FOUND, ""),
    USER_DUPLICATED(HttpStatus.CONFLICT, ""),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, ""),
    USER_NICKNAME_DUPLICATED(HttpStatus.CONFLICT, ""),
    NOT_FOUND_USER_NICKNAME(HttpStatus.CONFLICT, ""),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
