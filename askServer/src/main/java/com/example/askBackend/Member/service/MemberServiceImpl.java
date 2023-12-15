package com.example.askBackend.Member.service;

import com.example.askBackend.Exception.AppException;
import com.example.askBackend.Exception.ErrorCode;
import com.example.askBackend.Member.dto.MemberFindIdPwRequestDto;
import com.example.askBackend.Member.entity.Auth;
import com.example.askBackend.Member.entity.Member;
import com.example.askBackend.Member.repository.MemberRepository;
import com.example.askBackend.Util.JwtTokenUtil;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${jwt.token.secret}")
    private String key;

    private Long expireTimeMs = 1000 * 60 * 60l;

    @Transactional
    @Override
    public ResponseEntity<String> join(@NotBlank String id, @NotBlank String password, @NotBlank String nickname) {

        // 중복 체크
        Member findMember = memberRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_DUPLICATED, "중복 된 아이디"));

        // 닉네임 중복 체크
        if (findMember.getNickname().equals(nickname)) throw new AppException(ErrorCode.USER_DUPLICATED, "중복 된 닉네임");

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

    @Override
    public ResponseEntity<String> login(@NotBlank String id, @NotBlank String password) {

        // 아이디가 없는 경우
        Member findMember = memberRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_USER_ID  , "회원을 찾을 수 없습니다."));


        // 비밀번호가 틀린 경우
        if(!passwordEncoder.matches(password, findMember.getPassword())){
            throw new AppException(ErrorCode.INVALID_PASSWORD, "로그인 실패");
        }

        String token = JwtTokenUtil.createToken(findMember.getId(), key, expireTimeMs);

        return ResponseEntity.ok().body(token);
    }

    @Override
    public ResponseEntity<Boolean> checkIdDuplication(String id) {

        // 중복 체크
        memberRepository.findById(id)
                .ifPresent(member -> {
                    throw new AppException(ErrorCode.USER_DUPLICATED, "중복된 아이디");
                });

        return ResponseEntity.ok().body(Boolean.TRUE);
    }

    @Override
    public ResponseEntity<Boolean> checkNicknameDuplication(String nickname) {

        // 중복 체크
        memberRepository.findByNickname(nickname)
                .ifPresent(member -> {
                    throw new AppException(ErrorCode.USER_NICKNAME_DUPLICATED, "중복된 닉네임");
                });

        return ResponseEntity.ok().body(Boolean.TRUE);
    }

    @Override
    public ResponseEntity<String> findMemberId(String nickname) {
        // 닉네임 검색
        Member findMember = memberRepository.findByNickname(nickname).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_USER_NICKNAME, "닉네임을 찾을 수 없습니다."));

        return ResponseEntity.ok().body(findMember.getId());
    }

    @Override
    public ResponseEntity<MemberFindIdPwRequestDto.FindPwDto> findMemberPw(String id, String nickname) {
        // id 검색
        Member findMember = memberRepository.findByIdAndNickname(id, nickname).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_USER_ID, "아이디를 찾을 수 없습니다."));

        // 정보 전달
        MemberFindIdPwRequestDto.FindPwDto findPwDto = new MemberFindIdPwRequestDto.FindPwDto(findMember.getId(), findMember.getNickname());

        return ResponseEntity.ok().body(findPwDto);
    }

    @Transactional
    @Override
    public ResponseEntity<String> changeMemberPw(String id, String nickname, String password) {
        // id 검색
        Member findMember = memberRepository.findByIdAndNickname(id, nickname).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_USER_ID, "아이디를 찾을 수 없습니다."));

        findMember.changePw(passwordEncoder.encode(password));

        memberRepository.save(findMember);
        return ResponseEntity.ok().body("비밀번호 변경 완료");
    }
}
