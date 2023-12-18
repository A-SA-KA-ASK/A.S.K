package com.example.askBackend.Member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginRequestDto {

    @NotBlank
    @Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$", message = "Invalid email format")
    @Schema(description = "아이디", example = "example@example.com")
    private String id;

    @NotBlank
    @Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,15}$", message="Invalid password format")
    @Schema(description = "비밀번호", example = "exPassword1")
    private String password;
}
