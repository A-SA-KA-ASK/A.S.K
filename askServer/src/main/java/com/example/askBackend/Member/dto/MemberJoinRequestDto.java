package com.example.askBackend.Member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberJoinRequestDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinDto {

        @NotBlank(message = "ID cannot be blank")
        @Schema(description = "아이디")
        private String id;

        @NotBlank(message = "PASSWORD cannot be blank")
        @Schema(description = "비밀번호")
        private String password;

        @NotBlank(message = "NICKNAME cannot be blank")
        @Schema(description = "닉네임")
        private String nickname;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CheckIdDuplicationDto {

        @NotBlank(message = "ID cannot be blank")
        private String id;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CheckNicknameDuplicationDto {

        @NotBlank(message = "NICKNAME cannot be blank")
        private String nickname;
    }
}