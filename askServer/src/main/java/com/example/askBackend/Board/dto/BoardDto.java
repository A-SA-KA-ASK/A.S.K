package com.example.askBackend.Board.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class BoardDto {

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

        @NotBlank(message = "CATEGORY cannot be blank")
        @Schema(description = "category", example = "작성한 카테고리 이름")
        private String category;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TextBoardModifyDto {

        @NotBlank(message = "TITLE cannot be blank")
        @Schema(description = "title", example = "게시글 제목")
        private String title;

        @NotBlank(message = "CONTENT cannot be blank")
        @Schema(description = "content", example = "게시글 내용")
        private String content;

        @NotBlank(message = "CATEGORY cannot be blank")
        @Schema(description = "category", example = "작성한 카테고리 이름")
        private String category;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TextBoardDelDto {

        @Positive(message = "boardNum은 양의 정수여야 합니다.")
        @Schema(description = "boardNum", example = "1")
        private Long boardNum;
    }
}
