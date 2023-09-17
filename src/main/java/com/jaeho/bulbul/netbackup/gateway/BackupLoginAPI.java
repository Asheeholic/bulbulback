package com.jaeho.bulbul.netbackup.gateway;

import com.jaeho.bulbul.netbackup.apiCallPreset.ReturnWebClient;
import com.jaeho.bulbul.netbackup.dto.NetBackupLoginDto;
import com.jaeho.bulbul.netbackup.dto.NetBackupLoginTokenDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

import javax.net.ssl.SSLException;

@Slf4j
@Component
public class BackupLoginAPI {

    @Value("${NetBackup.username}")
    private String username;
    @Value("${NetBackup.password}")
    private String password;

    public String login() throws SSLException {

        log.info("username: " + username);
        log.info("password: " + password);

        WebClient webClient = ReturnWebClient.preset();

        NetBackupLoginDto loginDto = NetBackupLoginDto.builder()
                .userName(username)
                .password(password)
                .build();

        NetBackupLoginTokenDto tokenDto = webClient.post()
                .uri("/login")
                .bodyValue(loginDto)
                .retrieve()
                .bodyToMono(NetBackupLoginTokenDto.class)
                .block();

        assert tokenDto != null;
        return tokenDto.getToken();
    }
}
