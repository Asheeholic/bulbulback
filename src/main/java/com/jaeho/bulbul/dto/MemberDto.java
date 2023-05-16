package com.jaeho.bulbul.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class MemberDto {

    private String id;
    private String password;
    private String nickname;
    private String phoneNumber;
    private String email;

    @QueryProjection
    public MemberDto(String id, String password, String nickname, String phoneNumber, String email) {
        this.id = id;
        this.password = password;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
