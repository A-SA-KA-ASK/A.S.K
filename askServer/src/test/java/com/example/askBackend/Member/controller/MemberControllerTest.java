package com.example.askBackend.Member.controller;

import com.example.askBackend.Exception.AppException;
import com.example.askBackend.Exception.ErrorCode;
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
    void join() throws Exception{
        String id = "adfsdf";
        String password = "1q2w3e";
        String nickname = "test";

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
    void joinFail() throws Exception{
        String id = "reuamasdfsdssdfg";
        String password = "1q2w3e";
        String nickname = "test";

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

        String id = "reuamasdfsdssdfg";
        String password = "1q2w3e";

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

        String id = "reuamasdfsds";
        String password = "1q2w3e";

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

        String id = "reuamasdfsdssdfg";
        String password = "1q2w3easdfadsf";

        when(memberService.login(any(), any()))
                .thenThrow(new AppException(ErrorCode.INVALID_PASSWORD, "로그인 실패"));

        mockMvc.perform(post("/api/v1/users/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new MemberLoginRequestDto(id, password))))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }
}