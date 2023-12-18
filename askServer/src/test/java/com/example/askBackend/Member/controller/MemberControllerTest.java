package com.example.askBackend.Member.controller;

import com.example.askBackend.Exception.AppException;
import com.example.askBackend.Exception.ErrorCode;
import com.example.askBackend.Member.dto.MemberFindIdPwRequestDto;
import com.example.askBackend.Member.dto.MemberJoinRequestDto;
import com.example.askBackend.Member.dto.MemberLoginRequestDto;
import com.example.askBackend.Member.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
class MemberControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    MemberService memberService;

    @Autowired
    ObjectMapper objectMapper;  // 자바 obejct -> json

    @Test
    @DisplayName("회원 가입 성공")
    @WithMockUser
    void join() throws Exception {
        String id = "adfsdf@naver.com";
        String password = "testTest2";
        String nickname = "testTest2";

        mockMvc.perform(post("/api/v1/users/join")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new MemberJoinRequestDto.JoinDto(id, password, nickname))))
                .andDo(print())
                .andExpect(status().isOk())
        ;
    }

    @Test
    @DisplayName("회원 가입 실패")
    @WithMockUser
    void joinFail() throws Exception {
        String id = "adfsdf@naver.com";
        String password = "testTest2";
        String nickname = "testTest2";

        when(memberService.join(any(), any(), any()))
                .thenThrow(new AppException(ErrorCode.USER_DUPLICATED, "중복 아이디"));

        mockMvc.perform(post("/api/v1/users/join")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new MemberJoinRequestDto.JoinDto(id, password, nickname))))
                .andDo(print())
                .andExpect(status().isConflict())
        ;
    }

    @Test
    @DisplayName("로그인 성공")
    @WithMockUser
    public void login_success() throws Exception {

        String id = "adfsdf@naver.com";
        String password = "testTest2";

        when(memberService.login(any(), any()))
                .thenReturn(ResponseEntity.ok("token"));

        mockMvc.perform(post("/api/v1/users/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new MemberLoginRequestDto(id, password))))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("로그인 실패 - id 없음")
    @WithMockUser
    public void login_fail() throws Exception {

        String id = "dkfma4@naver.com";
        String password = "testTest2";

        when(memberService.login(any(), any()))
                .thenThrow(new AppException(ErrorCode.NOT_FOUND_USER_ID, "사용자의 ID가 존재하지 않음"));

        mockMvc.perform(post("/api/v1/users/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new MemberLoginRequestDto(id, password))))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("로그인 실패 - password 틀림")
    @WithMockUser
    public void login_fail2() throws Exception {

        String id = "adfsdf@naver.com";
        String password = "testTest2";

        when(memberService.login(any(), any()))
                .thenThrow(new AppException(ErrorCode.INVALID_PASSWORD, "로그인 실패"));

        mockMvc.perform(post("/api/v1/users/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new MemberLoginRequestDto(id, password))))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("아이디 중복 테스트 - 실패")
    @WithMockUser
    public void dupIdCheck_false() throws Exception {
        String id = "adfsdf@naver.com";

        when(memberService.checkIdDuplication(any()))
                .thenThrow(new AppException(ErrorCode.USER_DUPLICATED, "중복된 아이디"));

        mockMvc.perform(post("/api/v1/users/join/checkIdDub")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new MemberJoinRequestDto.CheckIdDuplicationDto(id))))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    @DisplayName("아이디 중복 테스트 - 성공")
    @WithMockUser
    public void dupIdCheck_success() throws Exception {
        String id = "adfsdf@naver.com";

        when(memberService.checkIdDuplication(any()))
                .thenReturn(ResponseEntity.ok().body(Boolean.TRUE));

        mockMvc.perform(post("/api/v1/users/join/checkIdDub")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new MemberJoinRequestDto.CheckIdDuplicationDto(id))))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("닉네임 중복 테스트 - 실패")
    @WithMockUser
    public void dupNicknameCheck_false() throws Exception {
        String nickname = "nickname1";

        when(memberService.checkNicknameDuplication(any()))
                .thenThrow(new AppException(ErrorCode.USER_NICKNAME_DUPLICATED, "중복된 닉네임"));

        mockMvc.perform(post("/api/v1/users/join/checkNicknameDub")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new MemberJoinRequestDto.CheckNicknameDuplicationDto(nickname))))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    @DisplayName("닉네임 중복 테스트 - 성공")
    @WithMockUser
    public void dupNicknameCheck_success() throws Exception {
        String nickname = "nickname1";

        when(memberService.checkIdDuplication(any()))
                .thenReturn(ResponseEntity.ok().body(Boolean.TRUE));

        mockMvc.perform(post("/api/v1/users/join/checkNicknameDub")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new MemberJoinRequestDto.CheckNicknameDuplicationDto(nickname))))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("아이디 찾기 테스트 - 실패")
    @WithMockUser
    public void findMyId_fail() throws Exception {
        String nickname = "nickname1";

        when(memberService.findMemberId(any()))
                .thenThrow(new AppException(ErrorCode.NOT_FOUND_USER_NICKNAME, "닉네임을 찾을 수 없습니다."));

        mockMvc.perform(post("/api/v1/users/find/id")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new MemberFindIdPwRequestDto.FindIdDto(nickname))))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    @DisplayName("아이디 찾기 테스트 - 성공")
    @WithMockUser
    public void findMyId_success() throws Exception {
        String nickname = "nickname1";

        when(memberService.findMemberId(any()))
                .thenReturn(ResponseEntity.ok().body("userId"));

        mockMvc.perform(post("/api/v1/users/find/id")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new MemberFindIdPwRequestDto.FindIdDto(nickname))))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("비밀번호 찾기 테스트 - 실패")
    @WithMockUser
    public void findMyPw_fail() throws Exception {
        String id = "adfsdf@naver.com";
        String nickname = "nickname1";

        when(memberService.findMemberPw(any(), any()))
                .thenThrow(new AppException(ErrorCode.NOT_FOUND_USER_ID, "사용자를 찾을 수 없습니다"));

        mockMvc.perform(post("/api/v1/users/find/pw")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new MemberFindIdPwRequestDto.FindPwDto(id, nickname))))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("비밀번호 찾기 테스트 - 성공")
    @WithMockUser
    public void findMyPw_success() throws Exception {
        String id = "adfsdf@naver.com";
        String nickname = "nickname1";

        when(memberService.findMemberPw(any(), any()))
                .thenReturn(ResponseEntity.ok().body(new MemberFindIdPwRequestDto.FindPwDto(id, nickname)));

        mockMvc.perform(post("/api/v1/users/find/pw")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new MemberFindIdPwRequestDto.FindPwDto(id, nickname))))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    @DisplayName("비밀번호 변경 테스트 - 실패")
    @WithMockUser
    public void changeMyPw_fail() throws Exception {
        String id = "adfsdf@naver.com";
        String nickname = "nickname1";
        String password = "exPassword1";

        when(memberService.changeMemberPw(any(), any(), any()))
                .thenThrow(new AppException(ErrorCode.NOT_FOUND_USER_ID, "사용자를 찾을 수 없습니다."));

        mockMvc.perform(patch("/api/v1/users/find/pw")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new MemberFindIdPwRequestDto.ChangeMemberDto(id, nickname, password))))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("비밀번호 변경 테스트 - 성공")
    @WithMockUser
    public void changeMyPw_success() throws Exception {
        String id = "adfsdf@naver.com";
        String nickname = "nickname1";
        String password = "exPassword1";

        when(memberService.changeMemberPw(any(), any(), any()))
                .thenReturn(ResponseEntity.ok().body("비밀번호 변경 완료"));

        mockMvc.perform(patch("/api/v1/users/find/pw")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new MemberFindIdPwRequestDto.ChangeMemberDto(id, nickname, password))))
                .andDo(print())
                .andExpect(status().isOk());
    }

}