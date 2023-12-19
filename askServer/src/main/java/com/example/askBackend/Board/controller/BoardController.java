package com.example.askBackend.Board.controller;

import com.example.askBackend.Board.dto.BoardDto;
import com.example.askBackend.Board.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

@Tag(name = "board", description = "게시글 관리 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/boards")
public class BoardController {

    private final BoardService boardService;

    @Operation(summary = "게시글 작성", description = "게시글 저장 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "게시글 작성 완료"),
            @ApiResponse(responseCode = "404", description = "찾을 수 없는 정보"),
            @ApiResponse(responseCode = "403", description = "권한이 없어 접근 불가능"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("/")
    public ResponseEntity<String> saveBoard(Authentication authentication, @Valid @RequestBody BoardDto.TextBoardDto dto){
        //log.info("authentication {}", authentication.getName());
        String role = "";
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            role = authority.toString();
            //log.info("role {}", role);
        }
        return boardService.saveBoard(dto.getTitle(), dto.getContent(), dto.getCategory(), authentication.getName(), role);
    }

    @Operation(summary = "게시글 수정", description = "게시글 수정 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "게시글 작성 완료"),
            @ApiResponse(responseCode = "404", description = "찾을 수 없는 정보"),
            @ApiResponse(responseCode = "403", description = "권한이 없어 접근 불가능"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PatchMapping("/{boardNum}")
    public ResponseEntity<String> modifyBoard(Authentication authentication, @Valid @RequestBody BoardDto.TextBoardModifyDto dto, @PathVariable Long boardNum){
        String role = "";
        log.info("boardNum{}", boardNum);
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            role = authority.toString();
        }

        return boardService.modifyBoard(boardNum, dto.getTitle(), dto.getContent(), dto.getCategory(), authentication.getName(), role);
    }

    @DeleteMapping("/{boardNum}")
    public ResponseEntity<String> delBoard(Authentication authentication, @PathVariable Long boardNum){
        log.info("boardNum{}", boardNum);
        return boardService.deleteBoard(boardNum, authentication.getName());
    }
}
