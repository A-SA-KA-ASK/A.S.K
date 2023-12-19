package com.example.askBackend.config.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class PrincipalDetails implements UserDetails {

    private final String id; // 추가된 필드
    private final String nickname;
    private final String password; // 실제로는 필요 없을 수 있습니다.
    private final Collection<? extends GrantedAuthority> authorities;

    // 기타 필요한 필드들

    public PrincipalDetails(String id, String nickname, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
        this.authorities = authorities;
    }


    public String getNickname(){
        return nickname;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}