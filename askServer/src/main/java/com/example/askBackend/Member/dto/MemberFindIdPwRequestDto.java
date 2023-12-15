package com.example.askBackend.Member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberFindIdPwRequestDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FindIdDto {

        @NotBlank(message = "NICKNAME cannot be blank")
        @Schema(description = "닉네임")
        private String nickname;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FindPwDto {

        @NotBlank(message = "ID cannot be blank")
        private String id;

        @NotBlank(message = "NICKNAME cannot be blank")
        private String nickname;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChangeMemberDto {

        @NotBlank(message = "ID cannot be blank")
        private String id;

        @NotBlank(message = "NICKNAME cannot be blank")
        private String nickname;

        @NotBlank(message = "PASSWORD cannot be blank")
        private String password;
    }
}