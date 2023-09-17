package com.jaeho.bulbul.netbackup.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class ManualBackupDto {
    private Data data;

    @Builder
    @lombok.Data
    public static class Data {
        private String type;
        private Attributes attributes;
    }

    @Builder
    @lombok.Data
    public static class Attributes {
        private String policyName;
        private String scheduleName;
        private String clientName;
        private boolean trialBackup;
    }

    /**
     * @param policyName memberId
     */
    @Builder
    public ManualBackupDto(String policyName, String scheduleName, String clientName) {
        this.data = Data.builder()
                .type("backupRequest")
                .attributes(Attributes.builder()
                        .policyName(policyName)
                        .scheduleName(scheduleName)
                        .clientName(clientName)
                        .trialBackup(false)
                        .build())
                .build();
    }
}
