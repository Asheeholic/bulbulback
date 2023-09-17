package com.jaeho.bulbul.controller;

import com.jaeho.bulbul.netbackup.dto.JobFileListDto;
import com.jaeho.bulbul.netbackup.dto.JobTryLogsDto;
import com.jaeho.bulbul.netbackup.dto.JobsDto;
import com.jaeho.bulbul.service.BackupReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.net.ssl.SSLException;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/backup-reports")
public class BackupReportController {

    private final BackupReportService backupReportService;

    @GetMapping("/test")
    public String test() throws SSLException {
        return backupReportService.connectTest();
    }

    @GetMapping("/jobs")
    public JobsDto getJobDetail() throws SSLException {
        return backupReportService.getJobDetail();
    }

    @GetMapping("/job-file-list/{jobId}")
    public JobFileListDto getJobFileList(@PathVariable int jobId) throws SSLException {
        return backupReportService.getJobFileList(jobId);
    }

    @GetMapping("/job-try-logs/{jobId}")
    public JobTryLogsDto getJobTryLogs(@PathVariable int jobId) throws SSLException {
        return backupReportService.getJobTryLogs(jobId);
    }

}
