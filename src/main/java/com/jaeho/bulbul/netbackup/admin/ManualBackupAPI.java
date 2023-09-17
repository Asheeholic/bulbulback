package com.jaeho.bulbul.netbackup.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaeho.bulbul.netbackup.apiCallPreset.ReturnWebClient;
import com.jaeho.bulbul.netbackup.dto.ManualBackupDto;
import com.jaeho.bulbul.netbackup.dto.ManualBackupSuccessDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import javax.net.ssl.SSLException;

@Component
@Slf4j
public class ManualBackupAPI {

    @Value("${NetBackup.scheduleName}")
    private String scheduleName;

    @Value("${NetBackup.clientName}")
    private String clientName;

    /**
     * 정책명을 memberId로 지정한다.
     * 이 와중에 스케줄명과 클라이언트명은 application.yml에서 지정한다.
     * 토큰은 로그인을 통해 받아온다.
     * 리턴은 job id를 리턴한다.
     *
     * @param memberId (String)
     * @param token (String)
     * @return jobId (int)
     * @throws SSLException (Exception)
     */
    public int manualBackup(String memberId, String token) throws SSLException, JsonProcessingException {
        // main logic
        WebClient webClient = ReturnWebClient.preset();

        ManualBackupDto backupDto = ManualBackupDto.builder()
                .policyName(memberId)
                .scheduleName(this.scheduleName)
                .clientName(this.clientName)
                .build();

        ObjectMapper mapper = new ObjectMapper();
        log.info("ManualBackupDto: " + mapper.writeValueAsString(backupDto));

        ManualBackupSuccessDto backupSuccessDto =
                webClient.post()
                .uri("/admin/manual-backup")
                .header("Authorization", token)
                .bodyValue(backupDto)
                .retrieve()
                .bodyToMono(ManualBackupSuccessDto.class)
                .block();

        assert backupSuccessDto != null;
        return backupSuccessDto.getData()[0].getAttributes().getJobId();

    }
}
