package com.jaeho.bulbul.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class MemberPasswordUpdateDto {

    private String memberId;
    private String password;

    @Builder
    public MemberPasswordUpdateDto(String memberId, String password) {
        this.memberId = memberId;
        this.password = password;
    }
}
