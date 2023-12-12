package com.example.askBackend.Member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberJoinRequestDto {

    @NotBlank
    @Schema(description = "아이디")
    private String id;

    @NotBlank
    @Schema(description = "비밀번호")
    private String password;

    @NotBlank
    @Schema(description = "닉네임")
    private String nickname;
}
