package com.example.askBackend.Member.service;

import com.example.askBackend.Member.dto.MemberFindIdPwRequestDto;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;

public interface MemberService {

    ResponseEntity<String> join(String id, String password, @NotBlank String nickname);
    ResponseEntity<String> login(@NotBlank String id, @NotBlank String password);
    ResponseEntity<Boolean> checkIdDuplication(String id);
    ResponseEntity<Boolean> checkNicknameDuplication(String nickname);
    ResponseEntity<String> findMemberId(String nickname);
    ResponseEntity<MemberFindIdPwRequestDto.FindPwDto> findMemberPw(String id, String nickname);
    ResponseEntity<String> changeMemberPw(String id, String nickname, String password);
}
