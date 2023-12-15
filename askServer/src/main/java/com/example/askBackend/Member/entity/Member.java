package com.example.askBackend.Member.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long MEMBER_ID;

    private String id;
    private String password;
    private String nickname;

    @Enumerated(EnumType.STRING)
    private Auth auth;

    public void changePw(String password){
        this.password = password;
    }
}
