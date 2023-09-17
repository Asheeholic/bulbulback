package com.jaeho.bulbul.netbackup.config;

import com.jaeho.bulbul.netbackup.apiCallPreset.ReturnWebClient;
import com.jaeho.bulbul.netbackup.dto.CreatePolicyDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import javax.net.ssl.SSLException;

@Component
@Slf4j
public class CheckPolicyExistAPI {
    public String checkPolicyExist(String token, String memberId) throws SSLException {
        WebClient webClient = ReturnWebClient.preset();
        try {
            return webClient.get()
                    .uri("/config/policies/" + memberId)
                    .header("Authorization", token)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        } catch (Exception e) {
            log.warn("정책이 존재하지 않습니다.");
            return null;
        }
    }
}
