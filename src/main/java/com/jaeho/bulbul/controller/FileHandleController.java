package com.jaeho.bulbul.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jaeho.bulbul.dto.UploadFileResponse;
import com.jaeho.bulbul.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.net.ssl.SSLException;
import java.util.Arrays;
import java.util.List;

/**
 * 파일 업로드, 다운로드를 처리하는 컨트롤러
 * - @RequiredArgsConstructor 는 final 이나 @NonNull 인 필드 값만 파라미터로 받는 생성자를 만들어준다.
 * - @Autowired 는 생성자가 하나만 있으면 생략할 수 있지만, 생성자가 2개 이상이면 @Autowired 를 붙여줘야 한다.
 *
 */
@RestController
@Slf4j
@RequestMapping("/backup-files")
@RequiredArgsConstructor
public class FileHandleController {

    private final Logger logger = LoggerFactory.getLogger(FileHandleController.class);
    private final FileService fileService;

    /**
     * Username 테스트
     * @return username
     */
    @PostMapping("/test")
    public String test() {

        // JWT 토큰을 이용해서 사용자의 아이디를 가져오게 한다.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        log.info("username: " + username);

        return username;
    }

    /**
     * 파일 업로드 => 다중으로 전환
     * @param files -> MultipartFile[]
     * @return List<UploadFileResponse>
     */
    @PostMapping("/upload")
    public String uploadFile(MultipartFile[] files) throws SSLException, JsonProcessingException {
        if (Arrays.stream(files).findAny().isEmpty()) {
            logger.error("File is empty");
            return null;
        }

        // JWT 토큰을 이용해서 Authentication 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // TODO: 2023-05-31 업로드시 디렉토리를 생성하고 (UUID) 그 파일을 가지고 장난 칠 수 있게 만들기
        List<UploadFileResponse> responses = fileService.storeFile(files, username);

        if (responses == null) {
            logger.error("File upload or backup Request failed");
            return "fail";
        }

        return "success";
    }


    /**
     * 파일 다운로드
     * @return String
     */
    @PostMapping("/download")
    public String downloadFile() {
        // file download logic here
        // 1. get file info from database
        // 2. get file from storage
        // 3. send file to client

        return "Download success";
    }


}
