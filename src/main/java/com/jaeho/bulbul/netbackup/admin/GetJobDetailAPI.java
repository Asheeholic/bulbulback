package com.jaeho.bulbul.netbackup.admin;

import com.jaeho.bulbul.netbackup.apiCallPreset.ReturnWebClient;
import com.jaeho.bulbul.netbackup.dto.JobTryLogsDto;
import com.jaeho.bulbul.netbackup.dto.JobsDto;
import com.jaeho.bulbul.netbackup.dto.JobFileListDto;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import javax.net.ssl.SSLException;

@Component
public class GetJobDetailAPI {

    /**
     * 토큰과 job id를 받아서 job detail 리턴한다.
     * @param token (String)
     * @param memberId (String)
     * @return jobDetail (JobsDto)
     */
    public JobsDto getJobDetail(String token, String memberId, int pageLimit, int pageAfter, int pageBefore) throws SSLException {
        WebClient webClient = ReturnWebClient.preset();
        return webClient.get()
//                .uri("/admin/jobs/")
                // 'https://nbu10/netbackup/admin/jobs?filter=policyName%20eq%20%27test%27&page%5Blimit%5D=10&page%5Bafter%5D=9&page%5Bbefore%5D=7&sort=-jobId'
                .uri(uriBuilder -> uriBuilder
                        .path("/admin/jobs/")
                        .queryParam("filter", "policyName eq '" + memberId + "'")
                        .queryParam("page[limit]", pageLimit)
                        .queryParam("page[after]", pageAfter)
                        .queryParam("page[before]", pageBefore)
                        .build())
                .header("Authorization", token)
                .retrieve()
                .bodyToMono(JobsDto.class)
                .block();
    }
    public JobsDto getJobDetail(String token, String memberId, int pageLimit, int pageAfter) throws SSLException {
        WebClient webClient = ReturnWebClient.preset();
        return webClient.get()
//                .uri("/admin/jobs/")
                // 'https://nbu10/netbackup/admin/jobs?filter=policyName%20eq%20%27test%27&page%5Blimit%5D=10&page%5Bafter%5D=9&page%5Bbefore%5D=7&sort=-jobId'
                .uri(uriBuilder -> uriBuilder
                        .path("/admin/jobs/")
                        .queryParam("filter", "policyName eq '" + memberId + "'")
                        .queryParam("page[limit]", pageLimit)
                        .queryParam("page[after]", pageAfter)
                        .build())
                .header("Authorization", token)
                .retrieve()
                .bodyToMono(JobsDto.class)
                .block();
    }
    public JobsDto getJobDetail(String token, String memberId, int pageLimit) throws SSLException {
        WebClient webClient = ReturnWebClient.preset();
        return webClient.get()
//                .uri("/admin/jobs/")
                // 'https://nbu10/netbackup/admin/jobs?filter=policyName%20eq%20%27test%27&page%5Blimit%5D=10&page%5Bafter%5D=9&page%5Bbefore%5D=7&sort=-jobId'
                .uri(uriBuilder -> uriBuilder
                        .path("/admin/jobs/")
                        .queryParam("filter", "policyName eq '" + memberId + "'")
                        .queryParam("page[limit]", pageLimit)
                        .build())
                .header("Authorization", token)
                .retrieve()
                .bodyToMono(JobsDto.class)
                .block();
    }
    public JobsDto getJobDetail(String token, String memberId) throws SSLException {
        WebClient webClient = ReturnWebClient.preset();
        return webClient.get()
//                .uri("/admin/jobs/")
                .uri(uriBuilder -> uriBuilder
                        .path("/admin/jobs/")
                        .queryParam("filter", "policyName eq '" + memberId + "'")
                        .build())
                .header("Authorization", token)
                .retrieve()
                .bodyToMono(JobsDto.class)
                .block();
    }



    /**
     * 토큰과 job id를 받아서 job file-list를 리턴한다.
     * @param token (String)
     * @param jobId (String)
     * @return JobFileListDto
     */
    public JobFileListDto getJobFileList(String token, int jobId) throws SSLException {
        WebClient webClient = ReturnWebClient.preset();
        String jobIdString = Integer.toString(jobId);
        return webClient.get()
                .uri("/admin/jobs/" + jobIdString + "/file-lists")
                .header("Authorization", token)
                .retrieve()
                .bodyToMono(JobFileListDto.class)
                .block();
    }

    public JobTryLogsDto getJobTryLogs(String token, int jobId) throws SSLException {
        WebClient webClient = ReturnWebClient.preset();
        String jobIdString = Integer.toString(jobId);
        return webClient.get()
                .uri("/admin/jobs/" + jobIdString + "/try-logs")
                .header("Authorization", token)
                .retrieve()
                .bodyToMono(JobTryLogsDto.class)
                .block();
    }

}
