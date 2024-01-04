package com.example.askBackend.InitDB;

import com.example.askBackend.Category.entity.Category;
import com.example.askBackend.Category.repository.CategoryRepository;
import com.example.askBackend.Member.entity.Auth;
import com.example.askBackend.Member.entity.Member;
import com.example.askBackend.Member.repository.MemberRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MemberInitDB {
    private final InitService initService;

    @PostConstruct
    public void init() {
        /*initService.dbInit1();*/
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final MemberRepository memberRepository;
        private final PasswordEncoder passwordEncoder;

        public void dbInit1() {

            Member user = Member.builder()
                    .id("dkfma1@naver.com")
                    .password(passwordEncoder.encode("dkfmaPassword1"))
                    .nickname("사용자이름")
                    .auth(Auth.USER)
                    .build();

            memberRepository.save(user);


            Member admin = Member.builder()
                    .id("admin@naver.com")
                    .password(passwordEncoder.encode("adminPassword1"))
                    .nickname("관리자이름")
                    .auth(Auth.ADMIN)
                    .build();

            memberRepository.save(admin);
        }
    }
}