package com.jaeho.bulbul.netbackup.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class JobsDto {

    private Data[] data;

    @lombok.Data
    public static class Data {
        private String type;
        private String id;
        private Attributes attributes;
        private Meta meta;
        private Links links;
    }

    @lombok.Data
    public static class Attributes {
        private int jobId;
        private int parentJobId;
        private int activeProcessId;
        private String jobType;
        private String jobSubType;
        private String policyType;
        private String policyName;
        private String scheduleType;
        private String scheduleName;
        private String clientName;
        private String jobOwner;
        private String jobGroup;
        private String backupId;
        private String destinationStorageUnitName;
        private String destinationMediaServerName;
        private String dataMovement;
        private int streamNumber;
        private int copyNumber;
        private int priority;
        private int compression;
        private int status;
        private String state;
        private int numberOfFiles;
        private int estimatedFiles;
        private int kilobytesTransferred;
        private int kilobytesToTransfer;
        private int transferRate;
        private int percentComplete;
        private int restartable;
        private int suspendable;
        private int resumable;
        private int frozenImage;
        private String transportType;
        private int currentOperation;
        private int sessionId;
        private int numberOfTapeToEject;
        private int submissionType;
        private int auditDomainType;
        private String startTime;
        private String endTime;
        private String activeTryStartTime;
        private String lastUpdateTime;
        private String activeElapsedTime;
        private int childCount;
        private String jobPath;
        private int retentionLevel;

        @JsonProperty("try")
        private int try_;

        private int cancellable;
        private int jobQueueReason;
        private int kilobytesDataTransferred;
        private String elapsedTime;
        private String dteMode;
        private String workloadDisplayName;
        private String offHostType;
    }

    @lombok.Data
    private static class Meta {
        private Pageination pagination;
    }

    @lombok.Data
    private static class Pageination {
        private String next;
        private String prev;
        private String last;
        private int limit;
    }

    @lombok.Data
    public static class Links {
        private Self self;
        private FileLists fileLists;
        private TryLogs tryLogs;
    }

    @lombok.Data
    public static class Self {
        private String href;
    }

    @lombok.Data
    public static class FileLists {
        private String href;
    }

    @lombok.Data
    public static class TryLogs {
        private String href;
    }
}
