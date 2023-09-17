package com.jaeho.bulbul.controller;

import com.jaeho.bulbul.dto.MemberInfoDto;
import com.jaeho.bulbul.dto.MemberPasswordUpdateDto;
import com.jaeho.bulbul.dto.MemberSignUpDto;
import com.jaeho.bulbul.entity.Member;
import com.jaeho.bulbul.security.dto.MemberLoginRequestDto;
import com.jaeho.bulbul.security.dto.TokenInfo;
import com.jaeho.bulbul.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/username")
    public String getUsername() {
        return memberService.getUsername();
    }

    // 회원가입
    @PostMapping("/signup")
    public Member signUp(@RequestBody MemberSignUpDto memberSignUpDto) {
        memberSignUpDto.setPassword(
                passwordEncoder.encode(memberSignUpDto.getPassword())
        );
        return memberService.signUp(memberSignUpDto);
    }

    // 유저의 모든 정보 가져오기
    @GetMapping("/get-user-info")
    public MemberInfoDto getUserInfo() {
        return memberService.getUserInfo();
    }

    // 유저 정보 변경
    @PostMapping("/update-user-info")
    public Member updateInfo(@RequestBody MemberInfoDto memberInfoDto) {
        return memberService.updateUserInfo(memberInfoDto);
    }


    // 패스워드 변경
    @PostMapping("/update-password")
    public String updatePassword(@RequestBody MemberPasswordUpdateDto memberPasswordUpdateDto) {
        memberPasswordUpdateDto.setPassword(
                passwordEncoder.encode(memberPasswordUpdateDto.getPassword())
        );
        Long result = memberService.updatePassword(memberPasswordUpdateDto);

        if (result == 1L) {
            return "success";
        } else {
            return "fail";
        }

    }

}
