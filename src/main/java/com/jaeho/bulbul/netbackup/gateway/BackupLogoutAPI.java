package com.jaeho.bulbul.netbackup.gateway;

import com.jaeho.bulbul.netbackup.apiCallPreset.ReturnWebClient;
import com.jaeho.bulbul.netbackup.dto.NetBackupLoginDto;
import com.jaeho.bulbul.netbackup.dto.NetBackupLoginTokenDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import javax.net.ssl.SSLException;

@Slf4j
@Component
public class BackupLogoutAPI {

    public void logout(String token) throws SSLException {

        WebClient webClient = ReturnWebClient.preset();

        webClient.post()
                .uri("/logout")
                .header("Authorization", token)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
