package com.jaeho.bulbul.service;

import com.jaeho.bulbul.dto.MemberSignUpDto;
import com.jaeho.bulbul.entity.Member;
import com.jaeho.bulbul.repository.MemberRepository;
import com.jaeho.bulbul.security.component.JwtTokenProvider;
import com.jaeho.bulbul.security.dto.TokenInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
//@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    // Login
    @Transactional
    public TokenInfo login(String memberId, String password) {
        // 1. Login ID/PW 기반으로 Authentication 객체 생성
        // 이때 authentication 은 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(memberId, password);

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어 지는 부분
        // authenticate 메서드가 실행 될 때
        // CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication =
                authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        return jwtTokenProvider.generateToken(authentication);
    }

    // SignUp
    @Transactional
    public Member signUp(MemberSignUpDto memberSignUpDto) {
        List<String> roles = new ArrayList<>();
        roles.add(memberSignUpDto.getRole());

        Member member = Member.builder()
                .memberId(memberSignUpDto.getMemberId())
                .password(memberSignUpDto.getPassword())
                .nickname(memberSignUpDto.getNickname())
                .email(memberSignUpDto.getEmail())
                .phoneNumber(memberSignUpDto.getPhoneNumber())
                .roles(roles)
                .build();

        memberRepository.save(member);

        return member;
    }
}
