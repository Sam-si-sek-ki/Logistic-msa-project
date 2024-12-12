package com.sparta.logistics.auth.domain.model;

import com.sparta.logistics.auth.libs.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "p_user")
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @NotNull
    @Size(max = 100)
    @Column(nullable = false, unique = true, length = 100)
    private String username;

    @NotNull
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String nickname;

    @NotNull
    @Email
    @Size(max = 255)
    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @NotNull
    @Size(min = 8, max = 255) // 비밀번호 길이 제한 (보안 강화 목적).
    @Column(nullable = false, length = 255)
    private String password;

    @Size(max = 255)
    @Column(length = 255)
    private String slackId;

    @NotNull
    @Enumerated(EnumType.STRING) // 데이터베이스에 문자열로 저장하여 가독성을 향상.
    @Column(nullable = false)
    private UserRole role;

    @Column
    private UUID companyId;

    @Column
    private UUID hubId;

}
