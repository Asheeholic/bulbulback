package com.jaeho.bulbul.netbackup.admin;

import com.jaeho.bulbul.netbackup.apiCallPreset.ReturnWebClient;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import javax.net.ssl.SSLException;

@Component
public class CancelJobAPI {
    /**
     * 해당 Job을 취소하는 API
     * @param token is String
     * @param jobId is int
     * @throws SSLException is Exception
     */
    public void cancelJob(String token, int jobId) throws SSLException {
        String jobIdString = String.valueOf(jobId);
        WebClient webClient = ReturnWebClient.preset();
        webClient.post()
                .uri("/jobs/" + jobIdString + "/cancel")
                .header("Authorization", token)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
