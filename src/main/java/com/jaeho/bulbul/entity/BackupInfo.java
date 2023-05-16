package com.jaeho.bulbul.entity;

import com.jaeho.bulbul.entity.baseEntiry.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BackupInfo extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "backup_key")
    private Long key;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_key")
    private Member member;

    @OneToMany(mappedBy = "backupInfo")
    private List<FileInfo> fileInfos = new ArrayList<>();


}
