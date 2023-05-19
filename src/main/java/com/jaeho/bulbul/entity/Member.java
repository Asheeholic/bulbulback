package com.jaeho.bulbul.entity;

import com.jaeho.bulbul.entity.baseEntity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "member_key")
    private Long key;

    @Column(unique = true)
    private String id;

    private String password;

    @Column(unique = true)
    private String nickname;

    private String phoneNumber;

    private String email;

    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;

    private Timestamp loginDate;

    @OneToMany(mappedBy = "member")
    private List<BackupInfo> backupInfos = new ArrayList<>();

    @Builder
    public Member(String id, String password, String nickname, String phoneNumber, String email) {
        this.id = id;
        this.password = password;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.email = email;

        // Default : USER
        this.memberRole = MemberRole.USER;
    }

}
