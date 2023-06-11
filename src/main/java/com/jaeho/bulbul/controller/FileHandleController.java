package com.jaeho.bulbul.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 파일 업로드, 다운로드를 처리하는 컨트롤러
 * @RequiredArgsConstructor
 * - final이나 @NonNull인 필드 값만 파라미터로 받는 생성자를 만들어준다.
 * - @Autowired는 생성자가 하나만 있으면 생략할 수 있지만, 생성자가 2개 이상이면 @Autowired를 붙여줘야 한다.
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
     * @return
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
     * @return
     */
    @PostMapping("/upload")
    public List<UploadFileResponse> uploadFile(MultipartFile[] files) {
        if (Arrays.stream(files).findAny().isEmpty()) {
            logger.error("File is empty");
            return null;
        }

        // JWT 토큰을 이용해서 Authentication 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // TODO: 2023-05-31 업로드시 디렉토리를 생성하고 (UUID) 그 파일을 가지고 장난 칠 수 있게 만들기
        return fileService.storeFile(files, username);
    }

    /**
     * 파일 다중 업로드
     * @param files
     * @return
     */
//    @PostMapping("/upload-multiple")
//    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
////        return Arrays.asList(files)
////                .stream()
////                .map(file -> uploadFile(file))
////                .collect(Collectors.toList());
//        // 위의 코드와 같은 코드
//        // 위의 코드는 람다식을 이용한 코드
//        // 아래의 코드는 메소드 레퍼런스를 이용한 코드
//        // 람다식을 더 간결하게 표현하는 방법
//        // 소드의 매개변수로 전달되는 것만 가능하다.
//        // 클래스::메소드 형태로 사용한다.
//        // 메소드의 매개변수와 리턴 타입이 같아야 한다.
//        return Arrays.stream(files)
//                .map(this::uploadFile)
//                .collect(Collectors.toList());
//
//    }


    /**
     * 파일 다운로드
     * @return
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
