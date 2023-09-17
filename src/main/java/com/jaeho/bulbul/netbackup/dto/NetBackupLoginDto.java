package com.jaeho.bulbul.netbackup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
public class NetBackupLoginDto {

//    {
//        "domainType": "unixpwd",
//            "domainName": "nbu10",
//            "userName": "root",
//            "password": "root"
//    }

    private String domainType;
    private String domainName;
    private String userName;
    private String password;

    @Builder
    public NetBackupLoginDto(String userName, String password) {
        this.domainType = "unixpwd";
        this.domainName = "nbu10";
        this.userName = userName;
        this.password = password;
    }
}
