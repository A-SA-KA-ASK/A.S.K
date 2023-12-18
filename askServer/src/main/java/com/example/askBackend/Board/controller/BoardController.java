package com.example.askBackend.Board.controller;

import com.example.askBackend.Board.dto.BoardSaveDto;
import com.example.askBackend.Board.service.BoardService;
import com.example.askBackend.Member.dto.MemberJoinRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "board", description = "게시글 관리 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/boards")
public class BoardController {

    private final BoardService boardService;

    @Operation(summary = "게시글 작성", description = "게시글 저장 API")
    @PostMapping("/save")
    public ResponseEntity<String> saveBoard(Authentication authentication, @Valid @RequestBody BoardSaveDto.TextBoardDto dto){
        log.info("authentication!!! {}", authentication.getName());
        //return boardService.save(dto.getTitle(), dto.getContent());
        return ResponseEntity.ok().body("test");
    }
}
