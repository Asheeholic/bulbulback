package com.jaeho.bulbul.netbackup.config;

import com.jaeho.bulbul.netbackup.apiCallPreset.ReturnWebClient;
import com.jaeho.bulbul.netbackup.dto.CreatePolicyDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import javax.net.ssl.SSLException;

@Component
@Slf4j
public class UpdateBackupSelectionAPI {

    @Value("${NetBackup.clientName}")
    private String clientName;

    public void updateBackupSelection(String token, String memberId, String backupSelections) throws SSLException {
        WebClient webClient = ReturnWebClient.preset();
        String[] location = new String[1];
        location[0] = backupSelections;

        CreatePolicyDto createPolicyDto =
                CreatePolicyDto.builder()
                        .memberId(memberId)
                        .clientName(clientName)
                        .selections(location)
                        .build();

        String result = webClient.put()
                .uri("/config/policies/" + memberId)
                .header("Authorization", token)
                .bodyValue(createPolicyDto)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        log.info(result);
    }
}
