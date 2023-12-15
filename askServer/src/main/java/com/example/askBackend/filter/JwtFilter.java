package com.example.askBackend.filter;

import com.example.askBackend.Member.service.MemberService;
import com.example.askBackend.Util.JwtTokenUtil;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter { // 매번 체크

    private final MemberService memberService;
    private final String secretkey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("authorization : {}", authorization);

        // token이 없을 경우
         if(authorization == null || !authorization.startsWith("Bearer ")){
             log.error("Authorization 존재하지않음.");
             filterChain.doFilter(request, response);
             return;
         }

         // token 가지고 오기
        String token  = authorization.split(" ")[1];

         // token 만료 여부 체크
        if(JwtTokenUtil.isExpired(token, secretkey)){
            log.error("token 만료");
            filterChain.doFilter(request, response);
            return;
        }

        // nickname을 Token에서 꺼내기
        String nickname = JwtTokenUtil.getNickname(token, secretkey);
        log.info("nickname: {}", nickname);

        // 권한 부여
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(nickname, null, List.of(new SimpleGrantedAuthority("USER")));

        // Detail을 넣어준다
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }
}
