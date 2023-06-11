package com.jaeho.bulbul.dto;

import lombok.Data;

@Data
public class FileInfoDto {

    private String filename;
    private String fileType;

    public FileInfoDto(String filename, String fileType) {
        this.filename = filename;
        this.fileType = fileType;
    }
}
