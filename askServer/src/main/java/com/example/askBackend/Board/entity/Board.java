package com.example.askBackend.Board.entity;

import com.example.askBackend.Category.entity.Category;
import com.example.askBackend.Member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends TimeStamped{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long BOARD_ID;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member writer;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    public static Board createBoard(Member writer, String title, String content, Category category){

        return Board.builder()
                .writer(writer)
                .title(title)
                .content(content)
                .category(category)
                .build();
    }

}
