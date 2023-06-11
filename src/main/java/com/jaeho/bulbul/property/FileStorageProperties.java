package com.jaeho.bulbul.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 파일 저장 경로를 설정하는 클래스
 * application.yml에서 설정한 file.upload.location의 값을 가져온다.
 */
@ConfigurationProperties(prefix = "file.upload")
public class FileStorageProperties {

    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
