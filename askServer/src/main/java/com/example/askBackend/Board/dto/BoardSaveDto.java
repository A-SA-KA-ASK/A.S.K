package com.example.askBackend.Board.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class BoardSaveDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TextBoardDto {

        @NotBlank(message = "TITLE cannot be blank")
        @Schema(description = "title", example = "게시글 제목")
        private String title;

        @NotBlank(message = "CONTENT cannot be blank")
        @Schema(description = "content", example = "게시글 내용")
        private String content;
    }
}
