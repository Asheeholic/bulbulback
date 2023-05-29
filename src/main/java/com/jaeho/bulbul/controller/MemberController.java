package com.jaeho.bulbul.controller;

import com.jaeho.bulbul.dto.MemberSignUpDto;
import com.jaeho.bulbul.entity.Member;
import com.jaeho.bulbul.security.dto.MemberLoginRequestDto;
import com.jaeho.bulbul.security.dto.TokenInfo;
import com.jaeho.bulbul.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    // 로그인
    @PostMapping("/login")
    public TokenInfo login(@RequestBody MemberLoginRequestDto memberLoginRequestDto) {
        String memberId = memberLoginRequestDto.getMemberId();
        String password = memberLoginRequestDto.getPassword();
        return memberService.login(memberId, password);
    }

    @PostMapping("/test")
    public String test() {
        return "success";
    }

    // 회원가입
    @PostMapping("/signup")
    public Member signUp(@RequestBody MemberSignUpDto memberSignUpDto) {
        memberSignUpDto.setPassword(
                passwordEncoder.encode(memberSignUpDto.getPassword())
        );
        return memberService.signUp(memberSignUpDto);
    }

}
