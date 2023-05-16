package com.jaeho.bulbul.entity;

import com.jaeho.bulbul.entity.baseEntiry.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "member_key")
    private Long key;

    private String id;

    private String password;

    private String nickname;

    private String phoneNumber;

    private String email;

    private Timestamp loginDate;

    @OneToMany(mappedBy = "member")
    private List<BackupInfo> backupInfos = new ArrayList<>();

}
