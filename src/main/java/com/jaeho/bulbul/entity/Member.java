package com.jaeho.bulbul.entity;

import com.jaeho.bulbul.entity.baseEntity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity implements UserDetails {

    @Id @GeneratedValue
    @Column(name = "member_key")
    private Long key;

    @Column(unique = true, updatable = false, nullable = false)
    private String memberId;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String nickname;

    private String phoneNumber;

    private String email;

    private Timestamp loginDate;

    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<BackupInfo> backupInfos = new ArrayList<>();

//    @Enumerated(EnumType.STRING)
//    private MemberRole memberRole;
    // 아래처럼 바꿈.
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "member_roles",
            joinColumns = @JoinColumn(name = "member_key")
    )
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    // 아래부턴 바꾸기
    // Implements from UserDetails from Spring boot Security

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return memberId;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // false -> true
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // false -> true
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // false -> true
    }

    @Override
    public boolean isEnabled() {
        return true; // false -> true
    }

}
