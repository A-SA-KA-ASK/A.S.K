package com.example.askBackend.Member.service;

import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;

public interface MemberService {

    ResponseEntity<String> join(String id, String password, @NotBlank String nickname);
}
