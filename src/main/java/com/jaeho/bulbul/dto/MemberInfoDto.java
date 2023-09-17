package com.jaeho.bulbul.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;

@Data
public class MemberInfoDto {

    private String memberId;
    private String nickname;
    private String phoneNumber;
    private String email;
    private String loginDate;
    private String roles;

    @Builder
    public MemberInfoDto(String memberId, String nickname, String phoneNumber, String email, String loginDate, String roles) {
        this.memberId = memberId;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.loginDate = loginDate;
        this.roles = roles;
    }

}
