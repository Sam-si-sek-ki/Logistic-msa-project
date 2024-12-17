package com.sparta.logistics.auth.domain.model;

import com.sparta.logistics.auth.libs.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "p_user")
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class User extends BaseEntity {

    @Id
    @Column(nullable = false, unique = true, length = 10, updatable = false)
    private String username;

    @Column(nullable = false, length = 100)
    private String nickname;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(length = 255)
    private String slackId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @Column
    private UUID companyId;

    @Column
    private UUID hubId;

    // 유저 생성 메서드
     public static User create(
             String username,
             String nickname,
             String email,
             String encryptedPassword,
             UserRole role,
             UUID companyId,
             UUID hubId
     ) {
         return User.builder()
                 .username(username)
                 .nickname(nickname)
                 .email(email)
                 .password(encryptedPassword)
                 .role(role)
                 .companyId(companyId)
                 .hubId(hubId)
                 .build();
    }

    // 유저 정보 수정 메서드
    public void update(
            String nickname,
            String email,
            String password,
            String slackId,
            UserRole role,
            UUID companyId,
            UUID hubId
    ) {
        if (nickname != null) this.nickname = nickname;
        if (email != null) this.email = email;
        if (password != null) this.password = password;
        if (slackId != null) this.slackId = slackId;
        if (role != null) this.role = role;
        if (companyId != null) this.companyId = companyId;
        if (hubId != null) this.hubId = hubId;
    }
}
