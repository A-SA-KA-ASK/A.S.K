package com.example.askBackend.Member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberFindIdPwRequestDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FindIdDto {

        @NotBlank(message = "NICKNAME cannot be blank")
        @Schema(description = "닉네임", example = "nickname")
        private String nickname;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FindPwDto {

        @NotBlank(message = "ID cannot be blank")
        @Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$", message = "Invalid email format")
        @Schema(description = "아이디", example = "example@example.com")
        private String id;

        @NotBlank(message = "NICKNAME cannot be blank")
        @Schema(description = "닉네임", example = "nickname")
        private String nickname;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChangeMemberDto {

        @NotBlank(message = "ID cannot be blank")
        @Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$", message = "Invalid email format")
        @Schema(description = "아이디", example = "example@example.com")
        private String id;

        @NotBlank(message = "NICKNAME cannot be blank")
        @Schema(description = "닉네임", example = "nickname")
        private String nickname;

        @NotBlank(message = "PASSWORD cannot be blank")
        @Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,15}$", message="비밀번호는 8자 이상 15자 이하이며 대문자, 소문자, 숫자를 모두 포함해야 합니다.")
        @Schema(description = "비밀번호", example = "exPassword1")
        private String password;
    }
}