package com.jaeho.bulbul.service;

import com.jaeho.bulbul.dto.MemberInfoDto;
import com.jaeho.bulbul.dto.MemberPasswordUpdateDto;
import com.jaeho.bulbul.dto.MemberSignUpDto;
import com.jaeho.bulbul.entity.Member;
import com.jaeho.bulbul.repository.MemberQueryRepository;
import com.jaeho.bulbul.repository.MemberRepository;
import com.jaeho.bulbul.security.component.JwtTokenProvider;
import com.jaeho.bulbul.security.dto.TokenInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
//@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberQueryRepository memberQueryRepository;
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

    public String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    public MemberInfoDto getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String memberId = authentication.getName();
        Member member = memberQueryRepository.findMemberByMemberId(memberId);
        return MemberInfoDto.builder()
                .memberId(member.getMemberId())
                .nickname(member.getNickname())
                .phoneNumber(member.getPhoneNumber())
                .email(member.getEmail())
                .build();
    }

    public String getPassword(String memberId) {
        Member member = memberQueryRepository.findMemberByMemberId(memberId);
        log.info("member: " + member);
        return member.getPassword();
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

    // update User Info
    @Transactional
    public Member updateUserInfo(MemberInfoDto memberInfoDto) {
        Member member = memberQueryRepository.findMemberByMemberId(memberInfoDto.getMemberId());
        Long result = memberQueryRepository.updateMemberInfo(
                memberInfoDto.getMemberId(),
                memberInfoDto.getNickname(),
                memberInfoDto.getPhoneNumber(),
                memberInfoDto.getEmail()
        );
        log.info("updateUserInfoServiceTest");
        log.info("result: " + result);
        return result == 1L ? member : null;
    }

    // Update Password
    @Transactional
    public Long updatePassword(MemberPasswordUpdateDto memberPasswordUpdateDto) {
        Member member = memberQueryRepository.findMemberByMemberId(memberPasswordUpdateDto.getMemberId());
        Long result = memberQueryRepository.updateMemberPassword(
                memberPasswordUpdateDto.getMemberId(),
                memberPasswordUpdateDto.getPassword()
        );
        log.info("updatePasswordServiceTest");
        log.info("result: " + result);
        return result;
    }
}
