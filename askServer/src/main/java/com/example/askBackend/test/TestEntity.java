package com.example.askBackend.test;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TestEntity {
    @Id
    private Long id;

    private String name;

    @Builder
    public TestEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
