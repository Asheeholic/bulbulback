package com.jaeho.bulbul.service;

import com.jaeho.bulbul.netbackup.admin.GetJobDetailAPI;
import com.jaeho.bulbul.netbackup.dto.JobFileListDto;
import com.jaeho.bulbul.netbackup.dto.JobTryLogsDto;
import com.jaeho.bulbul.netbackup.dto.JobsDto;
import com.jaeho.bulbul.netbackup.gateway.BackupLoginAPI;
import com.jaeho.bulbul.netbackup.gateway.BackupLogoutAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.net.ssl.SSLException;

@Service
@RequiredArgsConstructor
public class BackupReportService {

    private final BackupLoginAPI loginAPI;
    private final BackupLogoutAPI logoutAPI;
    private final GetJobDetailAPI getJobDetailAPI;

    public String connectTest() throws SSLException {
        String token = login();
        logout(token);
        return "success";
    }

    public JobsDto getJobDetail() throws SSLException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String memberId = authentication.getName();
        String token = login();
        JobsDto jobsDto = getJobDetailAPI.getJobDetail(token, memberId);
        logout(token);

        return jobsDto;
    }

    public JobFileListDto getJobFileList(int jobId) throws SSLException {
        String token = login();
        JobFileListDto jobFileListDto = getJobDetailAPI.getJobFileList(token, jobId);
        logout(token);

        return jobFileListDto;
    }

    public JobTryLogsDto getJobTryLogs(int jobId) throws SSLException {
        String token = login();
        JobTryLogsDto jobTryLogsDto = getJobDetailAPI.getJobTryLogs(token, jobId);
        logout(token);

        return jobTryLogsDto;
    }


    private String login() throws SSLException { return loginAPI.login(); }

    private void logout(String token) throws SSLException { logoutAPI.logout(token); }

}
