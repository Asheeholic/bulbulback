package com.jaeho.bulbul.entity;

import com.jaeho.bulbul.entity.baseEntity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FileInfo extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "file_key")
    private Long key;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "backup_key")
    private BackupInfo backupInfo;

    private String filename;

    private String fileType;

    private Long fileSize;

    @Builder
    public FileInfo(BackupInfo backupInfo, String filename, String fileType, Long fileSize) {
        this.backupInfo = backupInfo;
        this.filename = filename;
        this.fileType = fileType;
        this.fileSize = fileSize;
    }
}
