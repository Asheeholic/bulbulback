package com.jaeho.bulbul.service;

import com.jaeho.bulbul.dto.UploadFileResponse;
import com.jaeho.bulbul.entity.BackupInfo;
import com.jaeho.bulbul.entity.FileInfo;
import com.jaeho.bulbul.property.FileStorageProperties;
import com.jaeho.bulbul.repository.BackupInfoRepository;
import com.jaeho.bulbul.repository.FileInfoRepository;
import com.jaeho.bulbul.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Service
@Slf4j
public class FileService {

    // Properties
    private final Path dirLocation;

    // Repository
    private final MemberRepository memberRepository;
    private final BackupInfoRepository backupInfoRepository;
    private final FileInfoRepository fileInfoRepository;

    // Constructor
    // private 메소드 확인 (makeDir)
    @Autowired
    public FileService(FileStorageProperties fileStorageProperties,
                       MemberRepository memberRepository,
                       BackupInfoRepository backupInfoRepository,
                       FileInfoRepository fileInfoRepository) {
        this.dirLocation = Paths.get(fileStorageProperties.getLocation())
                .toAbsolutePath()
                .normalize();
        makeDir(this.dirLocation);

        this.memberRepository = memberRepository;
        this.backupInfoRepository = backupInfoRepository;
        this.fileInfoRepository = fileInfoRepository;
    }

    /**
     * 디렉토리 생성 후에 파일을 저장하는 대표 저장 메소드
     * 백업은 후에 5분씩 맞춰서 백업이 자동 실행 되게 만듬. => 스케줄러
     * 백업쪽으로 API 던져서 실행하게 할 예정 => 커맨드로 하려니 시스템적으로 개입이 너무 심함.
     * @param files is MultipartFile[]
     * @param username is String
     * @return List<UploadFileResponse>
     */
    public List<UploadFileResponse> storeFile(MultipartFile[] files, String username) {
        // 업로드 되는 폴더에 username 폴더를 만든다.
        Path targetLocation = this.dirLocation.resolve(username);
        makeDir(targetLocation);

        // 그 아래에 UUID 폴더를 만든다.
        UUID uuid = UUID.randomUUID();
        Path uuidPath = targetLocation.resolve(uuid.toString());
        makeDir(uuidPath);

        // DB 저장
        makeBackupInfo(files, username);

        // 로컬 메소드로 진행해서 저장
        List<UploadFileResponse> responses =
                Arrays.stream(files)
                .map(file -> fileSave(file, uuidPath)).toList();

        // NetBackup API 전달


        return responses;
    }

    // 파일 다운로드
    public String downloadFile() {
        return "success";
    }

    // 파일 삭제
    public String deleteFile() {
        return "success";
    }

    // 파일 다중 삭제
    public String deleteFiles() {
        return "success";
    }


    /**
     * 이하는 모두 Private
     * 위의 메소드를 위해서 만든 모든 로컬 메소드
     */

    /**
     * 폴더 생성 메소드
     * @param targetLocation Path
     */
    private void makeDir(Path targetLocation) {
        // 디렉토리가 없으면 생성한다.
        // Files.createDirectories()는 디렉토리를 생성하고, 이미 디렉토리가 있으면 그냥 넘어간다.
        try {
            Files.createDirectories(targetLocation);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 파일 저장 메소드
     * @param file -> MultipartFile
     * @param dirPath -> Path
     * @return UploadFileResponse
     */
    private UploadFileResponse fileSave(MultipartFile file, Path dirPath) {
        try {
            // Normalize file name
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

            // 파일명에 부적합 문자가 있는지 확인한다.
            if (fileName.contains("..")) {
                throw new Exception("파일명에 부적합한 문자가 포함되어 있습니다. " + fileName);
            }

            // 파일을 저장한다.
            // 파일을 복사해서 저장하면 되는데, 같은 이름의 파일이 있으면 덮어쓰기를 한다.
            Path filePath = dirPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // UploadFileResponse 를 리턴한다.
            return new UploadFileResponse(
                    fileName,
                    file.getContentType(),
                    file.getSize()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 실패시 null 리턴
        return new UploadFileResponse(null, null, 0);
    }

    /**
     * BackupInfo 엔티티 생성 및 저장 메소드 (private)
     * @param files MultipartFile[]
     * @param username String
     */
    private void makeBackupInfo(MultipartFile[] files, String username) {
        // 회원이 존재하지 않으면 예외처리
        if (memberRepository.findByMemberId(username).isEmpty()) {
            throw new RuntimeException("존재하지 않는 회원입니다.");
        }

        // backupInfo 엔티티 생성
        BackupInfo backupInfo = BackupInfo.builder()
                .member(memberRepository.findByMemberId(username).get())
                .build();

        // 저장
        backupInfoRepository.save(backupInfo);

        // FileInfo 엔티티 생성
        List<FileInfo> fileInfos = Arrays
                        .stream(files)
                        .map(file -> makefileInfo(file, backupInfo))
                        .toList();

        // 저장
        fileInfoRepository.saveAll(fileInfos);

        log.info(backupInfo.toString());
    }

    /**
     * FileInfo 엔티티 생성 메소드 (private)
     * @param file MultipartFile
     * @param backupInfo BackupInfo
     * @return
     */
    private FileInfo makefileInfo(MultipartFile file, BackupInfo backupInfo) {
        return FileInfo.builder()
                .backupInfo(backupInfo)
                .filename(file.getOriginalFilename())
                .fileType(file.getContentType())
                .fileSize(file.getSize())
                .build();
    }
}
