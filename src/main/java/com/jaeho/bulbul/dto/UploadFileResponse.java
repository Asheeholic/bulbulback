package com.jaeho.bulbul.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class UploadFileResponse {

    private String filename;
    private String fileType;
    private long size;

    @QueryProjection
    public UploadFileResponse(String filename, String fileType, long size) {
        this.filename = filename;
        this.fileType = fileType;
        this.size = size;
    }

}
