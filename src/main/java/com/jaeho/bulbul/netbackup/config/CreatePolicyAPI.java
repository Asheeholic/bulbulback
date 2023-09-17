package com.jaeho.bulbul.netbackup.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaeho.bulbul.netbackup.apiCallPreset.ReturnWebClient;
import com.jaeho.bulbul.netbackup.dto.CreatePolicyDto;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import javax.net.ssl.SSLException;

@Component
public class CreatePolicyAPI {

    @Value("${NetBackup.clientName}")
    private String clientName;

    public void createPolicy(String token, String memberId) throws SSLException, JsonProcessingException {
        WebClient webClient = ReturnWebClient.preset();

        CreatePolicyDto createPolicyDto =
                CreatePolicyDto.builder()
                        .memberId(memberId)
                        .clientName(clientName)
                        .build();

        System.out.println(createPolicyDto.toString());
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(createPolicyDto);
        System.out.println(json);

        webClient.post()
                .uri("/config/policies")
                .header("Authorization", token)
                .bodyValue(createPolicyDto)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
