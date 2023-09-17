package com.jaeho.bulbul.netbackup.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

//{"data":{"type":"policy","attributes":{"policy":{"policyName":"zxczxc","policyType":"MS-Windows","policyAttributes":{"active":true,"alternateClientHostname":null,"backupHost":null,"clientCompress":false,"clientEncrypt":false,"collectBareMetalRestoreInfo":false,"collectTrueImageRestoreInfo":"Off","crossMountPoints":false,"dataMoverMachine":null,"disableClientSideDeduplication":false,"effectiveDateUTC":"2023-07-23T06:59:11.264Z","followNFSMounts":false,"granularRecovery":false,"mediaOwner":"*ANY*","offhostBackup":false,"optimizedBackup":false,"priority":0,"retainSnapshot":false,"snapshotBackup":false,"snapshotMethod":null,"snapshotMethodArgs":null,"storage":"TEST","storageIsSLP":false,"useAccelerator":false,"useMultipleDataStreams":false,"useReplicationDirector":false,"volumePool":"NetBackup"},"schedules":[{"acceleratorForcedRescan":false,"backupCopies":{"copies":[{"failStrategy":null,"mediaOwner":null,"retentionPeriod":{"value":1,"unit":"WEEKS"},"retentionLevel":0,"storage":null,"volumePool":null}],"priority":-1},"backupType":"Full Backup","excludeDates":{"lastDayOfMonth":false,"recurringDaysOfMonth":[],"recurringDaysOfWeek":[],"specificDates":[]},"frequencySeconds":86400,"mediaMultiplexing":1,"scheduleName":"Full","scheduleType":"Frequency","snapshotOnly":false,"startWindow":[],"storageIsSLP":false,"syntheticBackup":false}],"clients":[{"hostName":"test-pc","OS":"Windows10","hardware":"Windows-x64"}],"backupSelections":{"selections":["C:/"]}}},"id":"zxczxc"}}
@Data
public class CreatePolicyDto {
    
    private Data data;

    @lombok.Data
    @Builder
    public static class Data {
        private String id;
        private String type;
        private Attributes attributes;
    }

    @lombok.Data
    @Builder
    public static class Attributes {
        private Policy policy;
    }

    @lombok.Data
    @Builder
    public static class Policy {
        private String policyName;
        private String policyType;
        private PolicyAttributes policyAttributes;
        private Schedules[] schedules;
        private Clients[] clients;
        private BackupSelections backupSelections;
    }

    @lombok.Data
    @Builder
    public static class PolicyAttributes {
        private boolean active;
        private String alternateClientHostname;
        private String backupHost;
        private boolean clientCompress;
        private boolean clientEncrypt;
        private boolean collectBareMetalRestoreInfo;
        private String collectTrueImageRestoreInfo;
        private boolean crossMountPoints;
        private String dataMoverMachine;
        private boolean disableClientSideDeduplication;
        private String effectiveDateUTC;
        private boolean followNFSMounts;
        private String mediaOwner;
        private boolean offhostBackup;
        private int priority;
        private boolean retainSnapshot;
        private boolean snapshotBackup;
        private String snapshotMethod;
        private String snapshotMethodArgs;
        private String storage;
        private boolean storageIsSLP;
        private boolean useAccelerator;
        private boolean useMultipleDataStreams;
        private boolean useReplicationDirector;
        private String volumePool;
    }

    @lombok.Data
    @Builder
    public static class Schedules {
        private boolean acceleratorForcedRescan;
        private BackupCopies backupCopies;
        private String backupType;
        private ExcludeDates excludeDates;
        private int frequencySeconds;
        private int mediaMultiplexing;
        private String scheduleName;
        private String scheduleType;
        private boolean snapshotOnly;
        private String[] startWindow;
        private boolean storageIsSLP;
        private boolean syntheticBackup;
    }

    @lombok.Data
    @Builder
    public static class BackupCopies {
        private Copies[] copies;
        private int priority;
    }

    @lombok.Data
    @Builder
    public static class Copies {
        private String failStrategy;
        private String mediaOwner;
        private RetentionPeriod retentionPeriod;
        private int retentionLevel;
        private String storage;
        private String volumePool;
    }

    @lombok.Data
    @Builder
    public static class RetentionPeriod {
        private int value;
        private String unit;
    }

    @lombok.Data
    @Builder
    public static class ExcludeDates {
        private boolean lastDayOfMonth;
        private String[] recurringDaysOfMonth;
        private String[] recurringDaysOfWeek;
        private String[] specificDates;
    }

    @lombok.Data
    @Builder
    public static class Clients {
        private String hostName;

        @JsonProperty("OS")
        private String OS;
        private String hardware;
    }

    @lombok.Data
    @Builder
    public static class BackupSelections {
        private String[] selections;
    }

    @Builder
    public CreatePolicyDto(String memberId, String clientName, String[] selections) {
        this.data = Data.builder()
                .id(memberId)
                .type("policy")
                .attributes(Attributes.builder()
                        .policy(Policy.builder()
                                .policyName(memberId)
                                .policyType("MS-Windows")

                                .policyAttributes(
                                        PolicyAttributes.builder()
                                        .active(true)
                                        .alternateClientHostname(null)
                                        .backupHost(null)
                                        .clientCompress(false)
                                        .clientEncrypt(false)
                                        .collectBareMetalRestoreInfo(false)
                                        .collectTrueImageRestoreInfo("Off")
                                        .crossMountPoints(false)
                                        .dataMoverMachine(null)
                                        .disableClientSideDeduplication(false)
                                        .effectiveDateUTC("2023-06-26T09:39:42.273Z")
                                        .followNFSMounts(false)
                                        .mediaOwner("*ANY*")
                                        .offhostBackup(false)
                                        .priority(0)
                                        .retainSnapshot(false)
                                        .snapshotBackup(false)
                                        .snapshotMethod(null)
                                        .snapshotMethodArgs(null)
                                        .storage("TEST")
                                        .storageIsSLP(false)
                                        .useAccelerator(false)
                                        .useMultipleDataStreams(false)
                                        .useReplicationDirector(false)
                                        .volumePool("NetBackup")
                                        .build()
                                )

                                .schedules(new Schedules[]{
                                        Schedules.builder()
                                                .acceleratorForcedRescan(false)
                                                .backupCopies(
                                                        BackupCopies.builder()
                                                        .copies(new Copies[]{
                                                                Copies.builder()
                                                                        .failStrategy(null)
                                                                        .mediaOwner(null)
                                                                        .retentionPeriod(RetentionPeriod.builder()
                                                                                .value(1)
                                                                                .unit("WEEKS")
                                                                                .build())
                                                                        .retentionLevel(0)
                                                                        .storage(null)
                                                                        .volumePool(null)
                                                                        .build()
                                                        })
                                                        .priority(-1)
                                                        .build()
                                                )
                                                .backupType("Full Backup")
                                                .excludeDates(
                                                        ExcludeDates.builder()
                                                        .lastDayOfMonth(false)
                                                        .recurringDaysOfMonth(new String[]{})
                                                        .recurringDaysOfWeek(new String[]{})
                                                        .specificDates(new String[]{})
                                                        .build()
                                                )
                                                .frequencySeconds(604800)
                                                .mediaMultiplexing(1)
                                                .scheduleName("Full")
                                                .scheduleType("Frequency")
                                                .snapshotOnly(false)
                                                .startWindow(new String[]{})
                                                .storageIsSLP(false)
                                                .syntheticBackup(false)
                                                .build()
                                })

                                .clients(new Clients[]{
                                        Clients.builder()
                                                .hardware("Windows-x64")
                                                .hostName(clientName)
                                                .OS("Windows10")
                                                .build()
                                })

                                .backupSelections(
                                        BackupSelections.builder()
                                                // 추가 : UpdatePolicy를 위해
//                                                .selections(new String[]{})
                                                .selections(selections != null ? selections : new String[]{})
                                                .build()
                                )
                                .build()
                        )
                        .build()
                )
                .build();
    }
}
