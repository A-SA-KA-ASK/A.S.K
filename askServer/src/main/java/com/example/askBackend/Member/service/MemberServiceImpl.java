package com.example.askBackend.Member.service;

import com.example.askBackend.Exception.AppException;
import com.example.askBackend.Exception.ErrorCode;
import com.example.askBackend.Member.entity.Auth;
import com.example.askBackend.Member.entity.Member;
import com.example.askBackend.Member.repository.MemberRepository;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public ResponseEntity<String> join(@NotBlank String id, @NotBlank String password, @NotBlank String nickname) {

        // 중복 체크
        memberRepository.findById(id).ifPresent(member -> {
            throw new AppException(ErrorCode.USER_DUPLICATED, "중복 된 아이디");
        });

        Member member = Member.builder()
                .id(id)
                .password(passwordEncoder.encode(password))
                .nickname(nickname)
                .auth(Auth.DEFAULT)
                .build();

        // 저장
        memberRepository.save(member);

        return ResponseEntity.ok().body("회원 가입 성공");
    }
}
