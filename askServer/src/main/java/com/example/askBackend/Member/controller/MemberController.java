package com.example.askBackend.Member.controller;

import com.example.askBackend.Member.dto.MemberFindIdPwRequestDto;
import com.example.askBackend.Member.dto.MemberLoginRequestDto;
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

    @Operation(summary = "회원가입", description = "회원 가입 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "회원 가입 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 정보"),
            @ApiResponse(responseCode = "409", description = "회원 가입 실패"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("/join")
    public ResponseEntity<String> join(@Valid @RequestBody MemberJoinRequestDto.JoinDto dto){

        return memberService.join(dto.getId(), dto.getPassword(), dto.getNickname());
    }

    @Operation(summary = "아이디 중복 체크 ", description = "회원 가입 아이디 중복 체크 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "아이디 사용 가능"),
            @ApiResponse(responseCode = "400", description = "잘못된 정보가 전달됨"),
            @ApiResponse(responseCode = "409", description = "아이디 중복, 사용 불가능"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("/join/checkIdDub")
    public ResponseEntity<Boolean> checkIdDuplication(@Valid @RequestBody MemberJoinRequestDto.CheckIdDuplicationDto dto){
        log.info(dto.getId());

        return memberService.checkIdDuplication(dto.getId());
    }

    @Operation(summary = "닉네임 중복 체크 ", description = "회원 가입 닉네임 중복 체크 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "닉네임 사용 가능"),
            @ApiResponse(responseCode = "400", description = "잘못된 정보가 전달됨"),
            @ApiResponse(responseCode = "409", description = "닉네임 중복, 사용 불가능"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("/join/checkNicknameDub")
    public ResponseEntity<Boolean> checkNicknameDuplication(@Valid @RequestBody MemberJoinRequestDto.CheckNicknameDuplicationDto dto){
        log.info(dto.getNickname());

        return memberService.checkNicknameDuplication(dto.getNickname());
    }

    @Operation(summary = "로그인", description = "로그인 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody MemberLoginRequestDto memberLoginRequestDto){
        log.info("loginRequestDto {}", memberLoginRequestDto.getId());

        return memberService.login(memberLoginRequestDto.getId(), memberLoginRequestDto.getPassword());
    }

    @Operation(summary = "아이디 찾기", description = "아이디 찾기 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("/find/id")
    public ResponseEntity<String> findMemberId(@Valid @RequestBody MemberFindIdPwRequestDto.FindIdDto memberFindIdPwRequestDto){
        log.info("loginRequestDto {}", memberFindIdPwRequestDto.getNickname());

        return memberService.findMemberId(memberFindIdPwRequestDto.getNickname());
    }

    @Operation(summary = "비밀번호 찾기", description = "비밀번호 찾기 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("/find/pw")
    public ResponseEntity<MemberFindIdPwRequestDto.FindPwDto> findMemberPw(@Valid @RequestBody MemberFindIdPwRequestDto.FindPwDto memberFindIdPwRequestDto){
        log.info("loginRequestDto: {}, {}", memberFindIdPwRequestDto.getId(), memberFindIdPwRequestDto.getNickname());

        return memberService.findMemberPw(memberFindIdPwRequestDto.getId(), memberFindIdPwRequestDto.getNickname());
    }

    @Operation(summary = "비밀번호 변경", description = "비밀번호 찾기 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PatchMapping("/find/pw")
    public ResponseEntity<String> changeMemberPw(@Valid @RequestBody MemberFindIdPwRequestDto.ChangeMemberDto memberFindIdPwRequestDto){
        log.info("loginRequestDto: {}, {}", memberFindIdPwRequestDto.getId(), memberFindIdPwRequestDto.getNickname());

        return memberService.changeMemberPw(memberFindIdPwRequestDto.getId(), memberFindIdPwRequestDto.getNickname(), memberFindIdPwRequestDto.getPassword());
    }
}
