package com.example.askBackend.Category.entity;

import com.example.askBackend.Member.entity.Auth;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long CATEGORY_ID;

    private String name;

    private Boolean isImgCategory;

    @ElementCollection(targetClass = Auth.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private List<Auth> authorities = new ArrayList<>();

    public void setCategoryAuth(Auth auth){
        this.authorities.add(auth);
    }

    public static Category createCategory(String name, Boolean isImgCategory, List<Auth> authorities){
        Category category = Category.builder()
                .name(name)
                .isImgCategory(isImgCategory)
                .build();


        for (Auth authority : authorities) {
            category.setCategoryAuth(authority);
        }

        return category;
    }
}
