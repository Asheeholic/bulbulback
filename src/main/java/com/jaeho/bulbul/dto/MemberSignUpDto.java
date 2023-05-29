package com.jaeho.bulbul.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class MemberSignUpDto {

    // NEEDS
    // memberId, password, nickname, phoneNumber, email, role
    private String memberId;
    private String password;
    private String nickname;
    private String phoneNumber;
    private String email;
    private String role;

    @QueryProjection
    public MemberSignUpDto(String memberId, String password, String nickname, String phoneNumber, String email, String role) {
        this.memberId = memberId;
        this.password = password;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.role = role;
    }


}
