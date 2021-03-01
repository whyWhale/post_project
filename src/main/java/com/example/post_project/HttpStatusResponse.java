package com.example.post_project;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class HttpStatusResponse {
    public static final ResponseEntity RESPONSE_OK = ResponseEntity.status(HttpStatus.OK).build();
    public static final ResponseEntity RESPONSE_CONFLICT = ResponseEntity.status(HttpStatus.CONFLICT).body("중복된 아이디 입니다.");
    public static final ResponseEntity RESPONSE_BAD_REQUEST = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    public static final ResponseEntity RESPONSE_NOT_FOUND = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    public static final ResponseEntity RESPONSE_UNAUTHORIZED = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    public static final ResponseEntity RESPONSE_FORBIDDEN = ResponseEntity.status(HttpStatus.FORBIDDEN).build();
}
