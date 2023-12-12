package com.example.askBackend.Member.controller;

import com.example.askBackend.Member.dto.LoginRequestDto;
import com.example.askBackend.Member.dto.MemberJoinRequestDto;
import com.example.askBackend.Member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "member", description = "회원 관리 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "회원가입", description = "회원가입 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "회원가입 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 정보"),
            @ApiResponse(responseCode = "409", description = "회원가입 실패"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("/join")
    public ResponseEntity<String> join(@Valid @RequestBody MemberJoinRequestDto dto){

        return memberService.join(dto.getId(), dto.getPassword(), dto.getNickname());
    }
}
