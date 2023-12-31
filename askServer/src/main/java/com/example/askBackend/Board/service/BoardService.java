package com.example.askBackend.Board.service;

import org.springframework.http.ResponseEntity;

public interface BoardService {
    ResponseEntity<String> saveBoard(String title, String content, String categoryName, String id, String role);

    ResponseEntity<String> modifyBoard(Long BoardNum, String title, String content, String categoryName, String id, String role);

    ResponseEntity<String> deleteBoard(Long boardNum, String name);
}
